package banking.api.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.api.constants.Constants.Messages;
import banking.api.constants.Constants.TransactionTypes;
import banking.api.db.models.Customer;
import banking.api.db.models.CustomerAccount;
import banking.api.db.models.CustomerTransaction;
import banking.api.db.repository.CustomerAccountMybatisRepository;
import banking.api.db.repository.CustomerMybatisRepository;
import banking.api.db.repository.CustomerTransactionMybatisRepository;
import banking.api.exceptions.InvalidResourceException;
import banking.api.exceptions.MissingResourceException;
import banking.api.exceptions.UnknownException;

@Service
public class CustomerOperationsServiceImpl implements CustomerOperationsService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerMybatisRepository customerRepository;

	@Autowired
	CustomerAccountMybatisRepository customerAccountRepository;

	@Autowired
	CustomerTransactionMybatisRepository customerTransactionRepository;

	@Override
	public Customer findCustomerById(long customerId) {
		Customer customer = null;
		try {
			customer = customerRepository.findById(customerId);
		} catch (Exception e) {
			logger.debug("Error occured when fetching customer by id " + customerId, e);
			throw new MissingResourceException("Could not find customer with id " + customerId);
		}
		return customer;
	}

	@Override
	public BigDecimal findAccountBalanceByCustomerNationalId(Long nationalIdNum) {
		BigDecimal accountBalance = null;

		try {

			validateNationalId(nationalIdNum);

			Customer customer = findCustomerByNationalId(nationalIdNum);

			CustomerAccount customerAccount = findCustomerAccountByCustomerId(customer.getId());

			accountBalance = customerAccount.getBalance() != null
					? customerAccount.getBalance().setScale(2, RoundingMode.CEILING)
					: null;

		} catch (InvalidResourceException e) {
			throw e;
		} catch (MissingResourceException e) {
			throw e;
		} catch (Exception e) {
			logger.debug("Error occured when fetching account balance by customer nationa id" + nationalIdNum, e);
			throw new UnknownException(Messages.GENERIC_FAILURE_MSG);
		}
		return accountBalance;
	}

	@Override
	public Boolean makeCustomerDeposit(Long nationalIdNum, BigDecimal depositAmount) {
		boolean depositSucceeded = false;

		try {

			validateNationalId(nationalIdNum);

			validateDepositAmount(depositAmount);

			Customer customer = findCustomerByNationalId(nationalIdNum);

			CustomerAccount customerAccount = findCustomerAccountByCustomerId(customer.getId());

			BigDecimal currentBalance = customerAccount.getBalance() != null ? customerAccount.getBalance()
					: new BigDecimal(0);
			BigDecimal updatedBalance = currentBalance.add(depositAmount);

			customerAccount.setBalance(updatedBalance);
			customerAccount.setUpdatedAt(new Date());
			customerAccountRepository.update(customerAccount);

			CustomerTransaction customerTransaction = new CustomerTransaction();
			customerTransaction.setCustomerId(customer.getId());
			customerTransaction.setCustomerAccountId(customerAccount.getId());
			customerTransaction.setAmount(depositAmount);
			customerTransaction.setTransactionType(TransactionTypes.DEPOSIT);
			customerTransactionRepository.save(customerTransaction);

			depositSucceeded = true;
		} catch (InvalidResourceException e) {
			throw e;
		} catch (MissingResourceException e) {
			throw e;
		} catch (Exception e) {
			logger.debug("Error occured when making a deposit for customer with national id" + nationalIdNum, e);
			throw new UnknownException(Messages.GENERIC_FAILURE_MSG);
		}
		return depositSucceeded;
	}

	@Override
	public Boolean makeCustomerWithdrawal(Long nationalIdNum, BigDecimal withdrawalAmount) {
		boolean withdrawalSucceeded = false;

		try {
			validateNationalId(nationalIdNum);

			Customer customer = findCustomerByNationalId(nationalIdNum);

			CustomerAccount customerAccount = findCustomerAccountByCustomerId(customer.getId());

			BigDecimal currentBalance = customerAccount.getBalance() != null ? customerAccount.getBalance()
					: new BigDecimal(0);
			BigDecimal updatedBalance = currentBalance.subtract(withdrawalAmount);

			validateSufficientFundsForWithdrawal(currentBalance, withdrawalAmount);

			customerAccount.setBalance(updatedBalance);
			customerAccount.setUpdatedAt(new Date());
			customerAccountRepository.update(customerAccount);

			CustomerTransaction customerTransaction = new CustomerTransaction();
			customerTransaction.setCustomerId(customer.getId());
			customerTransaction.setCustomerAccountId(customerAccount.getId());
			customerTransaction.setAmount(withdrawalAmount);
			customerTransaction.setTransactionType(TransactionTypes.WITHDRAWAL);
			customerTransactionRepository.save(customerTransaction);

			withdrawalSucceeded = true;
		} catch (InvalidResourceException e) {
			throw e;
		} catch (MissingResourceException e) {
			throw e;
		} catch (Exception e) {
			logger.debug("Error occured when making a withdrawal for customer with national id" + nationalIdNum, e);
			throw new UnknownException(Messages.GENERIC_FAILURE_MSG);
		}

		return withdrawalSucceeded;
	}

	@Override
	public Customer findCustomerByNationalId(Long nationalIdNum) {
		Customer customer = customerRepository.findByNationalIdNum(nationalIdNum);
		if (customer == null) {
			throw new MissingResourceException("Could not find customer with national id " + nationalIdNum);
		}
		return customer;
	}

	@Override
	public CustomerAccount findCustomerAccountByCustomerId(Long customerId) {
		CustomerAccount customerAccount = customerAccountRepository.findBycustomerId(customerId);
		if (customerAccount == null) {
			throw new MissingResourceException(
					"Could not find an account for customer with national id " + customerId);
		}
		return customerAccount;
	}

	@Override
	public void validateSufficientFundsForWithdrawal(BigDecimal currentBalance, BigDecimal withdrawalAmount) {

		if (withdrawalAmount == null || (withdrawalAmount.compareTo(new BigDecimal(1)) == -1)) {
			throw new InvalidResourceException(Messages.PROVIDE_AMOUNT_GREATER_THAN_ZERO);
		}

		if (withdrawalAmount.compareTo(currentBalance) == 1) {
			throw new InvalidResourceException(Messages.INSUFFICIENT_FUNDS_FOR_WITHDRAWAL);
		}

	}

	@Override
	public void validateNationalId(Long nationalIdNum) {
		if (nationalIdNum == null) {
			throw new InvalidResourceException(Messages.PROVIDE_VALID_NATIONAL_ID);
		}
	}

	@Override
	public void validateDepositAmount(BigDecimal depositAmount) {
		if (depositAmount == null || (depositAmount.compareTo(new BigDecimal(1)) == -1)) {
			throw new InvalidResourceException(Messages.PROVIDE_AMOUNT_GREATER_THAN_ZERO);
		}
	}

}

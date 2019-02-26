package banking.api.services;

import java.math.BigDecimal;

import banking.api.db.models.Customer;
import banking.api.db.models.CustomerAccount;

public interface CustomerOperationsService {
  
	/**
	 * 
	 * @param customerId Id of the customer record in the db
	 * @return customer Customer object
	 */
	public Customer findCustomerById(long customerId);
	
	/**
	 * 
	 * @param nationalIdNum  National Id number for the customer
	 * @return customer Customer object
	 */
	public Customer findCustomerByNationalId(Long nationalIdNum);
	
	/**
	 * 
	 * @param customerId Id of the customer record in the db
	 * @return customerAccount CustomerAccount object
	 */
	public CustomerAccount findCustomerAccountByCustomerId(Long customerId);
	
	/**
	 * 
	 * @param nationalIdNum Customer's national id number
	 * @return accountBalance Customer's account balance
	 */
	public BigDecimal findAccountBalanceByCustomerNationalId(Long nationalIdNum);
	
	/**
	 * 
	 * @param nationalIdNum  Customer's national id number
	 * @param depositAmount Amount customer is depositing
	 * @return depositSucceeded Whether the deposit transaction succeeded
	 */
	public Boolean makeCustomerDeposit(Long nationalIdNum, BigDecimal depositAmount);
	
	/**
	 * 
	 * @param nationalIdNum Customer's national id number
	 * @param withdrawalAmount Amount customer is withdrawing
	 * @return withdrawalSucceeded Whether the withdrawal transaction succeeded
	 */
	public Boolean makeCustomerWithdrawal(Long nationalIdNum, BigDecimal withdrawalAmount);
	
    /**
     * 
     * @param currentBalance Balance in the customer's account
     * @param withdrawalAmount Amount to withdraw
     */
	public void validateSufficientFundsForWithdrawal(BigDecimal currentBalance, BigDecimal withdrawalAmount);
	
	/**
	 * 
	 * @param nationalIdNum Customer's national id number
	 */
	public void validateNationalId(Long nationalIdNum);
	
	/**
	 * 
	 * @param depositAmount Amount to deposit
	 */
	public void validateDepositAmount(BigDecimal depositAmount);
	
}

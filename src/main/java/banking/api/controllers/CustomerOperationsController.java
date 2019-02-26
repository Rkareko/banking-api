package banking.api.controllers;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import banking.api.constants.Constants.Messages;
import banking.api.db.models.Customer;
import banking.api.db.repository.CustomerMybatisRepository;
import banking.api.exceptions.InvalidResourceException;
import banking.api.exceptions.UnknownException;
import banking.api.response.models.GenericResponse;
import banking.api.services.CustomerOperationsService;

@RestController
public class CustomerOperationsController {
		
	@Autowired
	CustomerOperationsService customerTransactionsService;
	
	@Autowired
	CustomerMybatisRepository customerRepository;
	
	@GetMapping("/")
	public String welcomeMessage() {
		return Messages.WELCOME_MSG;		
	}
	
	
	@GetMapping("/customer/balance/{nationalIdNum}")
	public GenericResponse checkBalance(@PathVariable("nationalIdNum") Long nationalIdNum ) {
		BigDecimal accountBalance = null;
		GenericResponse genericResponse = null; 
		accountBalance = customerTransactionsService.findAccountBalanceByCustomerNationalId(nationalIdNum);
		if (accountBalance != null) {
			genericResponse = new GenericResponse(new Date().toString(), HttpStatus.OK.toString(), null,
					accountBalance.toString());
			
			return genericResponse;
		} else {
			throw new UnknownException(Messages.UNABLE_TO_PROCESS_BALANCE);
		}		
	}
	
	@PostMapping
	@RequestMapping("/customer/deposit")
	public GenericResponse makeDeposit(@RequestBody Customer customer) {	
		boolean customerDepositSucceeded = false;
		GenericResponse genericResponse = null;
		
		if (customer == null || customer.getAmount() == null || customer.getNationalId() == null) {
			throw new InvalidResourceException(Messages.PROVIDE_ID_NUMBER_AND_AMOUNT);
		}
		customerDepositSucceeded = customerTransactionsService.makeCustomerDeposit(customer.getNationalId(), customer.getAmount());
		
		if (customerDepositSucceeded) {
			genericResponse = new GenericResponse(new Date().toString(), HttpStatus.OK.toString(), null,
					Messages.DEPOSIT_SUCCESS);
			return genericResponse;
		} else {
			throw new UnknownException(Messages.UNABLE_TO_PROCESS_DEPOSIT);
		}
		
	}
	
	@PostMapping
	@RequestMapping("/customer/withdraw")
	public GenericResponse makeWithdrawal(@RequestBody Customer customer) {	
		boolean customerWithdrawalSucceeded = false;
		GenericResponse genericResponse = null;
		
		if (customer == null || customer.getAmount() == null || customer.getNationalId() == null) {
			throw new InvalidResourceException(Messages.PROVIDE_ID_NUMBER_AND_AMOUNT);
		}
		customerWithdrawalSucceeded = customerTransactionsService.makeCustomerWithdrawal(customer.getNationalId(), customer.getAmount());
		
		if (customerWithdrawalSucceeded) {
			genericResponse = new GenericResponse(new Date().toString(), HttpStatus.OK.toString(), null,
					Messages.WITHDRAWAL_SUCCESS);
			return genericResponse;
		} else {
			throw new UnknownException(Messages.UNABLE_TO_PROCESS_WITHDRAWAL);
		}
		
	}

}

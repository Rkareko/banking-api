package banking.api.constants;

public class Constants {
	
	public static class Messages {
		public static String UNABLE_TO_PROCESS_DEPOSIT = "We are unable to process the deposit request at the moment";
		public static String UNABLE_TO_PROCESS_WITHDRAWAL = "We are unable to process the withdrawal request at the moment";
		public static String UNABLE_TO_PROCESS_BALANCE = "We are unable to process the check balance request at the moment";
		public static String PROVIDE_ID_NUMBER_AND_AMOUNT = "Please provide customer's national id number and amount to process";
		public static String  PROVIDE_VALID_NATIONAL_ID = "Please provide a valid national id";
		public static String GENERIC_FAILURE_MSG = "Sorry we are unable to process your request at the moment";
		public static String INSUFFICIENT_FUNDS_FOR_WITHDRAWAL = "Insufficent funds to perform a withdrawal";
		public static String PROVIDE_AMOUNT_GREATER_THAN_ZERO = "Please provide an amount greater than zero";
		public static String DEPOSIT_SUCCESS = "Deposit successfully made";
		public static String WITHDRAWAL_SUCCESS = "Withdrawal successfully made";
		public static String REQUEST_SUCCESS = "Request processed successfully";
		public static String REQUEST_FAILURE = "Request processing failed";	
		public static String WELCOME_MSG = "Welcome to online banking";
	}
	
	public static class AccountTypes { // 1 = current account
		public static short CURRENT_ACCOUNT = 1; //only type supported for the time being
	}
	
	public static class TransactionTypes { // 1 = deposit, 2 = withdrawal. Only types supported for the time being
		public static short DEPOSIT = 1; 
		public static short WITHDRAWAL = 2; 		
	}
	
	public static class EndPoints {
		public static String CHECK_BALANCE_ENDPOINT = "/customer/balance/";
		public static String MAKE_DEPOSIT_ENDPOINT = "/customer/deposit/";
		public static String MAKE_WITHDRAWAL_ENDPOINT = "/customer/withdraw/";
	}
	
}

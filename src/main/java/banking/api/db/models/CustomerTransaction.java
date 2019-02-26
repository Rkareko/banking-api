package banking.api.db.models;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerTransaction {

	Long id;
	Long customerId;
	Long customerAccountId;
	Short transactionType; // 1 = deposit, 2 = withdrawal 
	BigDecimal amount;
	Date transactionDate;
	
	public CustomerTransaction() {		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Long getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(Long customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public Short getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Short transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
		
}

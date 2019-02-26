package banking.api.db.models;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerAccount {
	
	private Long id;
	private Long customerId;
	private short accountType;
	private BigDecimal balance;
	private Date createdAt;
	private Date updatedAt;
	
	public CustomerAccount() {
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

	public short getAccountType() {
		return accountType;
	}

	public void setAccountType(short accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
		
}

package banking.api.db.models;

import java.math.BigDecimal;

public class Customer {
	private Long id;
	private String email;
	private String name;
	private Long nationalId;
	private String passportNumber;
	private BigDecimal amount;
	
	public Customer() {
	}
	
	public Customer(Long id, String name,
			String email, Long nationalId, String passportNumber) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.nationalId = nationalId;
		this.passportNumber = passportNumber;		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNationalId() {
		return nationalId;
	}

	public void setNationalId(Long nationalId) {
		this.nationalId = nationalId;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
		
}

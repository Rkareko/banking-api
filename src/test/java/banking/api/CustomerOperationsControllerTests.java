package banking.api;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Verify;

import banking.api.constants.Constants.EndPoints;
import banking.api.constants.Constants.Messages;
import banking.api.controllers.CustomerOperationsController;
import banking.api.db.models.Customer;
import banking.api.db.models.CustomerAccount;
import banking.api.db.repository.CustomerAccountMybatisRepository;
import banking.api.db.repository.CustomerMybatisRepository;
import banking.api.services.CustomerOperationsService;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerOperationsController.class)
public class CustomerOperationsControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerOperationsService customerOperationsService;

	@MockBean
	private CustomerAccountMybatisRepository customerAccountRepository;

	@MockBean
	CustomerMybatisRepository customerMybatisRepository;

	Long testNationalIdNUm = 12345678l;
	Long inValidtestNationalIdNUm = 1l;
	BigDecimal testBalance = new BigDecimal(3400);
	Long testCustomerId = 1l;
	BigDecimal amount = new BigDecimal(2300);

	@Test
	public void checkBalanceEnpointPointShouldReturnBalanceWithValidInput() throws Exception {

		when(customerOperationsService.findAccountBalanceByCustomerNationalId(testNationalIdNUm))
				.thenReturn(testBalance);
		
		this.mockMvc.perform(get(EndPoints.CHECK_BALANCE_ENDPOINT + testNationalIdNUm)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(testBalance.toString())));

	}

	@Test
	public void makeDepositEndPointShouldReturnSuccessWithValidInPut() throws Exception {

		boolean customerDepositSucceeded = true;
		Customer testCustomer = initTestCustomer();

		when(customerOperationsService.makeCustomerDeposit(testCustomer.getNationalId(), testCustomer.getAmount()))
				.thenReturn(customerDepositSucceeded);

		this.mockMvc
				.perform(post(EndPoints.MAKE_DEPOSIT_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
						.content(convertObjectToJson(testCustomer)))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(Messages.DEPOSIT_SUCCESS)));
	}
	
	@Test
	public void makeWithDrawalEndPointShouldReturnSuccessWithValidInPut() throws Exception {

		boolean customerWithDrawalSucceeded = true;
		Customer testCustomer = initTestCustomer();

		when(customerOperationsService.makeCustomerWithdrawal(testCustomer.getNationalId(), testCustomer.getAmount()))
				.thenReturn(customerWithDrawalSucceeded);

		this.mockMvc
				.perform(post(EndPoints.MAKE_WITHDRAWAL_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
						.content(convertObjectToJson(testCustomer)))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(Messages.WITHDRAWAL_SUCCESS)));
	}

	private Customer initTestCustomer() {

		Customer testCustomer = new Customer();
		testCustomer.setId(testCustomerId);
		testCustomer.setName("Jane Doe");
		testCustomer.setEmail("jane@onlineBanking.com");
		testCustomer.setNationalId(testNationalIdNUm);
		testCustomer.setAmount(amount);
		return testCustomer;

	}

	private String convertObjectToJson(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

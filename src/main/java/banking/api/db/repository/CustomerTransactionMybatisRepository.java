package banking.api.db.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import banking.api.db.models.CustomerTransaction;

@Mapper
public interface CustomerTransactionMybatisRepository {
	
	@Select("SELECT * FROM customer_transactions WHERE id = #{id}")
	public CustomerTransaction findById(long id);

	@Select("SELECT * FROM customer_transactions WHERE customer_id = #{customerId}")
	public CustomerTransaction findBycustomerId(Long customerId);

	@Insert("INSERT INTO customer_transactions(customer_id, customer_account_id, transaction_type, amount) VALUES (#{customerId}, #{customerAccountId}, #{transactionType}, #{amount})")
	@SelectKey(statement = "call identity()", keyProperty = "id", before = false, resultType = Long.class)
	public int save(CustomerTransaction customerTransaction);

	@Update("Update customer_transactions set customer_id=#{customerId}, transaction_type=#{transactionType}, amount=#{amount} where id=#{id}")
	public int update(CustomerTransaction customerTransaction);

}

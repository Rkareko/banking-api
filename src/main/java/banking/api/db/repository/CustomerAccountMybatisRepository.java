package banking.api.db.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import banking.api.db.models.CustomerAccount;

@Mapper
public interface CustomerAccountMybatisRepository {

	@Select("SELECT * FROM customer_accounts WHERE id = #{id}")
	public CustomerAccount findById(long id);

	@Select("SELECT * FROM customer_accounts WHERE customer_id = #{customerId}")
	public CustomerAccount findBycustomerId(Long customerId);

	@Insert("INSERT INTO customer_accounts(customer_id, account_type, balance) VALUES (#{customerId}, #{accountType}, #{balance})")
	@SelectKey(statement = "call identity()", keyProperty = "id", before = false, resultType = Long.class)
	public int save(CustomerAccount customerAccount);

	@Update("Update customer_accounts set customer_id=#{customerId}, account_type=#{accountType}, balance=#{balance} where id=#{id}")
	public int update(CustomerAccount customerAccount);

}

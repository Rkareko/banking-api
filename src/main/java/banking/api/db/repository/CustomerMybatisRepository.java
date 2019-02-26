package banking.api.db.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import banking.api.db.models.Customer;

@Mapper
public interface CustomerMybatisRepository {
	
	@Select("SELECT * FROM customers WHERE id = #{id}")
	public Customer findById(long id);
	
	@Select("SELECT * FROM customers WHERE national_id = #{nationalId}")
	public Customer findByNationalIdNum(Long nationalIdNum);
	
	@Insert("INSERT INTO customers (email, name, national_id, passport_number) VALUES (#{email}, #{name}, #{nationalId}, #{passportNumber})")
	@SelectKey(statement="call identity()", keyProperty="id", before=false, resultType=Long.class)
	public int save(Customer customer);
	
	@Update("Update customers set name=#{name}, passport_number=#{passportNumber} where id=#{id}")
	public int update(Customer customer);
		
}

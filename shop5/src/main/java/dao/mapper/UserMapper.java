package dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.User;

public interface UserMapper {
	
	@Insert("insert into useraccount (userId,password,userName,phoneNo,postcode,address,email,birthDay)"
				+ "value (#{userId},#{password},#{userName},#{phoneNo},#{postcode},#{address},#{email},#{birthDay})")
	void insert(User user);

	@Update("update useraccount set username= #{userName}, "
				+ " birthday= #{birthDay}, phoneno= #{phoneNo}, "
				+ " postcode= #{postcode}, address= #{address}, "
				+ " email= #{email} where userid= #{userId}")
	void update(User user);

	@Delete("delete from useraccount where userid = #{userId}")
	void delete(User user);

}

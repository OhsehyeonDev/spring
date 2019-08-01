package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import logic.User;
@Repository
public class UserDao {
	private NamedParameterJdbcTemplate template;
	private RowMapper<User> mapper = 
			      new BeanPropertyRowMapper<User>(User.class);
	private Map<String,Object> paramMap = new HashMap<>();
	private SqlParameterSource paramClass; 
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
	}
	public void insert(User user) {
		paramClass = new BeanPropertySqlParameterSource(user);
		String sql = "insert into useraccount "
	+ "(userid, username, password, birthday,phoneno, postcode,"
	+ " address, email)  values (:userId, :userName,:password,"
	+ " :birthDay, :phoneNo, :postcode,:address, :email )";
		template.update(sql, paramClass);
	}
	public User selectOne(String userId) {
		String sql="select * from useraccount where userid = :userId";
		paramMap.clear();
		paramMap.put("userId", userId);
		return template.queryForObject(sql, paramMap, mapper);
	}
	public void update(User user) {
		String sql="update useraccount set username= :userName, "
				+ " birthday= :birthDay, phoneno= :phoneNo, "
				+ " postcode= :postcode, address= :address, "
				+ " email= :email where userid= :userId";
		paramClass = new BeanPropertySqlParameterSource(user);
		template.update(sql, paramClass);
		
	}
	public void delete(User user) {
		String sql="delete from useraccount where userid = :userId";
		paramClass = new BeanPropertySqlParameterSource(user);
		template.update(sql, paramClass);
	}
	public List<User> list() {
		return template.query("select * from useraccount",mapper);
	}
	public List<User> list(String[] idchks) {
		String ids = "";
		for(int i=0;i<idchks.length;i++) {
			ids += "'" + idchks[i] + ((i==idchks.length-1)?"'":"',");
		}
		String sql = "select * from useraccount where userid in ("
				+ ids + ")";
		return template.query(sql, mapper);
	}
}

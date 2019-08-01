package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import dao.mapper.ItemMapper;
import dao.mapper.UserMapper;
import logic.User;
@Repository
public class UserDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS ="dao.mapper.UserMapper.";
	private Map<String,Object> param = new HashMap<String,Object>(); 
	
	public User selectOne(String userId) {
		param.clear();
		param.put("userId", userId);
		return sqlSession.selectOne(NS + "list", param);
	}
	
	public void insert(User user) {
		param.clear();
		sqlSession.getMapper(UserMapper.class).insert(user);
	}

	public void update(User user) {
		sqlSession.getMapper(UserMapper.class).update(user);
		
	}
	public void delete(User user) {
		sqlSession.getMapper(UserMapper.class).delete(user);
	}
	public List<User> list() {
		return sqlSession.selectList(NS + "list");
	}
	
	public List<User> list(String[] idchks) {
		param.clear();
		param.put("ids", idchks);
		return sqlSession.selectList(NS + "list", param);
	}
}

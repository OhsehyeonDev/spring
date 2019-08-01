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
import logic.Item;

@Repository
public class ItemDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS ="dao.mapper.ItemMapper.";
	private Map<String, Object> param = new HashMap<>();
	
	public List<Item> list() {
		return sqlSession.selectList(NS + "list");
	}
	public Item selectOne(Integer id) {
		param.clear();
		param.put("id", id);
		return sqlSession.selectOne(NS + "list", param);
	}
	public void insert(Item item) {
		param.clear();
		int id = sqlSession.getMapper(ItemMapper.class).maxid();
		item.setId(++id); //새로운 상품 ID 설정
		sqlSession.getMapper(ItemMapper.class).insert(item);
	}
	public void update(Item item) {
		sqlSession.getMapper(ItemMapper.class).update(item);
	}
	public void delete(Integer id) {
		sqlSession.getMapper(ItemMapper.class).delete(id);
	}
}
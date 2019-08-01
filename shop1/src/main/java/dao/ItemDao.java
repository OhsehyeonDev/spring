package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import logic.Item;

public class ItemDao {
	private NamedParameterJdbcTemplate template;
	public void setDataSource(DataSource dataSource) {
		this.template = new NamedParameterJdbcTemplate(dataSource);
	}
	public List<Item> list() {
		String sql = "select * from item";
		/*
		 Item item = new Item();
		 item.setId()
		 item.setName()
		 ....
		- mapper : Item 클래스의 프로퍼티와 컬럼명을 비교해서 setter를 통해 값을 저장.
		*/
		RowMapper<Item> mapper = new BeanPropertyRowMapper<Item>(Item.class);
		return template.query(sql, mapper);
	}
	public Item selectOne(Integer id) {
		String sql = "select * from item where id=:id";
		RowMapper<Item> mapper = new BeanPropertyRowMapper<Item>(Item.class);
		Map<String, Integer> param = new HashMap<>();
		param.put("id",id);
		// queryForObject : 조회 결과 레코드가 한 개인 경우
		return template.queryForObject(sql, param, mapper);
	}
}

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

import logic.Item;

@Repository
public class ItemDao {
	private NamedParameterJdbcTemplate template;
	private RowMapper<Item> mapper = 
			      new BeanPropertyRowMapper<Item>(Item.class);
	private Map<String,Object> param = new HashMap<>();
	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
	}
	public List<Item> list() {
		return template.query("select * from item order by id", mapper);
	}
	public Item selectOne(Integer id) {
		param.clear();
		param.put("id", id);
		return template.queryForObject
			("select * from item where id=:id",param, mapper);
	}
	public void insert(Item item) {
		param.clear();
		int id = template.queryForObject
		("select ifnull(max(id),0) from item",param,Integer.class);
		item.setId(++id); //새로운 상품 ID 설정
		String sql = "insert into item (id, name,price,description,"
		+ " pictureUrl)"
		+ " values (:id, :name, :price, :description, :pictureUrl)";
		SqlParameterSource proparam = 
				         new BeanPropertySqlParameterSource(item);
		template.update(sql, proparam);
	}
	public void update(Item item) {
		String sql = "update item set name=:name, price=:price,"
				+ "	description=:description, "
				+ " pictureUrl=:pictureUrl"
				+ " where id=:id";
				SqlParameterSource proparam = 
						         new BeanPropertySqlParameterSource(item);
				template.update(sql, proparam);
	}
	public void delete(Integer id) {
		param.clear();
		param.put("id", id);
		template.update("delete from item where id=:id",param);
	}
}
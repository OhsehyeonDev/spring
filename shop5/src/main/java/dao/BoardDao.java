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

import dao.mapper.BoardMapper;
import dao.mapper.ItemMapper;
import dao.mapper.SaleMapper;
import logic.Board;

@Repository
public class BoardDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS = "dao.mapper.BoardMapper.";
	private Map<String, Object> param = new HashMap<>();

	public int count(String searchtype, String searchcontent) {
		if (searchtype != null && searchtype.length() != 0 && searchcontent != null && searchcontent.length() != 0) {
			param.clear();
			param.put("searchtype", searchtype);
			param.put("searchcontent", searchcontent);
		}
		return sqlSession.selectOne(NS + "count");
	}

	public List<Board> list(int pageNum, int limit, String searchtype, String searchcontent) {
		param.clear();
		param.put("startrow", (pageNum - 1) * limit);
		param.put("limit", limit);

		if (searchtype != null && searchtype.length() != 0 && searchcontent != null && searchcontent.length() != 0) {
			param.put("searchtype", searchtype);
			param.put("searchcontent", searchcontent);
		}
		return sqlSession.selectList(NS + "list", param);
	}

	public void readcntadd(Integer num) {
		param.clear();
		param.put("num", num);
		sqlSession.getMapper(BoardMapper.class).readcntadd(num);
	}

	public Board selectone(Integer num) {
		param.clear();
		param.put("num", num);
		param.put("startrow", 0);
		param.put("limit", 1);
		return sqlSession.selectOne(NS + "list", param);
	}

	public int maxNum() {
		Integer max = sqlSession.getMapper(BoardMapper.class).maxNum();
		return max;
	}

	public void insert(Board board) {
		param.clear();
		sqlSession.getMapper(BoardMapper.class).insert(board);
	}

	public void updateRefStep(Board b1) {
		param.clear();
		param.put("ref", b1.getRef());
		param.put("refstep", b1.getRefstep());
		sqlSession.getMapper(BoardMapper.class).updateRefStep(b1);
	}

	public void update(Board board) {
		sqlSession.getMapper(BoardMapper.class).update(board);
	}

	public void delete(Board board) {
		param.clear();
		param.put("num", board.getNum());
		sqlSession.getMapper(BoardMapper.class).delete(board);
	}

	public List<Map<String, Object>> graph() {
		return sqlSession.getMapper(BoardMapper.class).graph();
	}
}
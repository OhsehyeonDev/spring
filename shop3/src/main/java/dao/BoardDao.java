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

import logic.Board;

@Repository
public class BoardDao {
	private NamedParameterJdbcTemplate template;
	private RowMapper<Board> mapper = new BeanPropertyRowMapper<Board>(Board.class);
	private String boardcol = "select num,name,pass,subject,content," + " file1 fileurl, regdate, readcnt,ref,reflevel,"
			+ " refstep from board ";

	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
	}

	private Map<String, Object> param = new HashMap<>();

	public int count() {
		Integer ret = template.queryForObject("select count(*) from board", new HashMap(), Integer.class);
		return ret;
	}

	public List<Board> list(int pageNum, int limit) {
		String sql = boardcol + " order by ref desc, refstep asc " + " limit :startrow,:limit";
		param.clear();
		param.put("startrow", (pageNum - 1) * limit);
		param.put("limit", limit);
		return template.query(sql, param, mapper);
	}

	public void readcntadd(Integer num) {
		param.clear();
		param.put("num", num);
		String sql = "update board set readcnt = readcnt + 1 " + " where num=:num";
		template.update(sql, param);
	}

	public Board selectone(Integer num) {
		String sql = boardcol + " where num = :num";
		param.clear();
		param.put("num", num);
		return template.queryForObject(sql, param, mapper);
	}

	public int maxNum() {
		Integer max = template.queryForObject("select ifnull(max(num),0) from board", param, Integer.class);
		return max;
	}

	public void insert(Board board) {
		SqlParameterSource paramClass = new BeanPropertySqlParameterSource(board);
		String sql = "insert into board " + " (num, name, pass, subject, content, regdate, file1,"
				+ " readcnt,ref,reflevel,refstep )" + " values (:num,:name,:pass,:subject,:content,now(),:fileurl,"
				+ "0,:ref,:reflevel,:refstep )";
		template.update(sql, paramClass);
	}

	public void updateRefStep(Board b1) {
		String sql = "update board set refstep=refstep + 1" + " where ref = :ref and refstep > :refstep";
		param.clear();
		param.put("ref", b1.getRef());
		param.put("refstep", b1.getRefstep());
		template.update(sql, param);
	}

	public void update(Board board) {
		String sql = "update board set name=:name, subject=:subject, content=:content, "
				+ "file1=:fileurl where num=:num";
		SqlParameterSource paramClass = new BeanPropertySqlParameterSource(board);
		template.update(sql, paramClass);
	}

	public void delete(Board board) {
		String sql = "delete from board where num=:num";
		param.clear();
		param.put("num", board.getNum());
		template.update(sql, param);

	}
}
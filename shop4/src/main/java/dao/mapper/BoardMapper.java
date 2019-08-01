package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Board;

public interface BoardMapper {
	@Insert("insert into board " + " (num, name, pass, subject, content, regdate, file1,"
			+ " readcnt,ref,reflevel,refstep )" + " values (#{num},#{name},#{pass},#{subject},#{content},now(),#{fileurl},"
			+ "0,#{ref},#{reflevel},#{refstep} )")
	void insert(Board board);

	@Update("update board set name=#{name}, subject=#{subject}, content=#{content}, "
				+ "file1=#{fileurl} where num=#{num}")
	void update(Board board);

	@Delete("delete from board where num=#{num}")
	void delete(Board board);

	@Select("select ifnull(max(num),0) from board")
	Integer maxNum();

	@Update("update board set refstep=refstep + 1" + " where ref = #{ref} and refstep > #{refstep}")
	void updateRefStep(Board b1);

	@Update("update board set readcnt = readcnt + 1 " + " where num=#{num}")
	void readcntadd(Integer num);

	// key1 : ex. 홍길동 , value1 : ex. 15
	// map.put("key1", 홍길동)
	// map.put("value1", 15) // 한 번에 맵 객체 2개씩 들어감.
	
	@Select("select name key1, count(*) value1 from board "
			+ " group by name having count(*) > 0")
	List<Map<String, Object>> graph(); // map 객체가 list 형태로 저장
	
	
}


package spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component
@Repository // @Component + model 관련 객체
public class MemberDao {
	private static long nextId = 0; // 공통변수, 기본값 설정 X
	// a@aaa.bbb 이름을 가진 map 객체 : 1, a@aaa.aaa, 홍길동, 1234, 현재일자
	private Map<String, Member> map = new HashMap<>();
	public Member selectByEmail(String email) {
		return map.get(email);
	}
	public void insert(Member member) {
		member.setId(++nextId);
		map.put(member.getEmail(), member);
	}
	public Collection<Member> selectAll() {
		return map.values(); // 맵 객체 중  value 들만 리턴
		// keySet() : 맵 객체의 key 들만 리턴
		// entrySet() : 맵 객체의 (key, value) 들만 리턴
	}
	public void passchg(String[] arg) {
		Member member = map.get(arg[1]);
		member.setPassword(arg[3]); // 변경 후 비밀번호
		
	}
	public void delete(String email) {
		map.remove(email);
	}
}

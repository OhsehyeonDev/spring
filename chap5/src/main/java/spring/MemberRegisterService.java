package spring;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service //@Component + 서비스(Controller와 dao의 중간 역할 기능)
public class MemberRegisterService {
	@Autowired
	private MemberDao memberdao;
	
	public MemberRegisterService() {
		
	}
	public MemberRegisterService(MemberDao memberDao) {
		this.memberdao = memberDao;
	}
	public Long regist(RegisterRequest req) {
		Member member = memberdao.selectByEmail(req.getEmail());
		if(member !=null) {
			throw new DuplicateMemberException("due email" + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		memberdao.insert(newMember);
		return newMember.getId();
	}
}

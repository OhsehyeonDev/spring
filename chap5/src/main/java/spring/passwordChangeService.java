package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class passwordChangeService {
	@Autowired
	private MemberDao memberDao;
	public void change(String [] arg) {
		Member member = memberDao.selectByEmail(arg[1]);
		if(member == null) {
			throw new MemberNotFoundException ("Not Found Email :" + arg[1]);
		}
		if(!member.getPassword().equals(arg[2])) {
			throw new RuntimeException("Wrong Password" + arg[2]);
		}
		memberDao.passchg(arg);
	}
}

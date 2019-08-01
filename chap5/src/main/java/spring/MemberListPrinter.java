package spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("listPrinter")
public class MemberListPrinter {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberPrinter printer;
	public MemberListPrinter() {}
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	public void printAll() {
		// members : MemberDao 의 map 객체에 저장된 전체 value들.
		Collection<Member> members = memberDao.selectAll();
		// m : members 객체의 하나의 요소. Member 객체
		members.forEach(m->printer.print(m));	
	}
}

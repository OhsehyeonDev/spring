package main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xml.Article;
import xml.Member;
import xml.MemberService;
import xml.ReadArticleService;
import xml.UpdateInfo;

public class Main2 {
	public static void main(String[] args) {
	String[] config = {"di.xml","aop2.xml"};
	ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
	// readArticleService (인터페이스) : ReadArticleServiceImple 객체 
	// ReadArticleServiceImple.class 는 반드시 ReadArticleService 인터페이스의 구현 클래스여야 한다.
	ReadArticleService service = ctx.getBean("readArticleService", ReadArticleService.class);
	try {
		Article a1 = service.getArticleAndReadCnt(1);
		Article a2 = service.getArticleAndReadCnt(1);
		System.out.println("[main] a1==a2 :" + (a1==a2)); 
		// new Article(id) : 실행 할 때마다 새로운 객체로 리턴값을 받았기 때문에 같은 id 값을 받더라도 항상 false가 나온다.
		service.getArticleAndReadCnt(0);
	} catch (Exception e) {
		System.out.println("[main] "+ e.getMessage()); // e.getMessage() : LoggingAdvice 클래스의 afterThrowing 메서드의 ex.getMessage() 와 같다.
	}
	System.out.println("\n UpdateMemberInfoTrace Aspect 연습");
	MemberService msvc = ctx.getBean("memberService",MemberService.class);
	msvc.regist(new Member());
	msvc.update("hong", new UpdateInfo());
	msvc.delete("hong2","test");
	}

}

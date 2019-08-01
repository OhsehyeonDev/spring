package chap3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main1 {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		Project pro = ctx.getBean("project",Project.class); // Project.class 에서 project 실행
		pro.build();
		WriteImpl w = ctx.getBean("write",WriteImpl.class);
		w.write();
	}

}

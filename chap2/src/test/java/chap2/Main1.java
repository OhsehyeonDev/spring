package chap2;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main1 {
	public static void main(String[] args) {
		// 컨테이너 : 객체들의 집합
		// BeanFactory : 가장 간단한 컨테이너(DI 기능만 처리 가능, AOP 기능 사용 불가). 컨테이너의 최상단 인터페이스 * 4.0 버전 이후 deprecated 됨.(잘 사용 안함)
		// ApplicationContext : BeanFactory 의 하위 인터페이스 (DI, AOP 기능 모두 사용 가능)
		// WebApplicationContext : ApplicationContext 의 하위 인터페이스 (Web 환경에서 사용되는 컨테이너)
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		// applicationContext.xml 을 읽어 ctx 컨테이너에 저장
		Greeter g = ctx.getBean("greeter",Greeter.class); // getBean : 생성된 Bean 에 접근하기 위한 메서드
		// "greeter" 라는 이름의 Greeter.class 생성.
		System.out.println(g.greet("스프링"));
		Greeter g2 = ctx.getBean("greeter",Greeter.class);
		System.out.println(g == g2); // true (g, g2가 참조하는 객체는 같은 객체이기 때문에 true임.)
	}
}

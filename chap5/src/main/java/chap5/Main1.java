package chap5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main1 {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		Executor exec = ctx.getBean("executor",Executor.class); // 이름이 executor 인 Executor.class 가져오기
		exec.addUnit(new WorkUnit());
		exec.addUnit(new WorkUnit());
		
		HomeController home = ctx.getBean("homeController",HomeController.class);
		home.checkSensorAndAlarm();
		System.out.println("침입 없음 ========");
		InfraredRaySensor sensor = ctx.getBean("windowSensor",InfraredRaySensor.class);
		sensor.foundObject();
		home.checkSensorAndAlarm();
		
		System.out.println("=============");
		// sensor = new InfraredRaySensor("현관 센서"); // 새로운 객체를 만들었기 때문에 적용 안됨. (기존 설정 적용 안됨.)
		sensor = ctx.getBean("doorSensor",InfraredRaySensor.class);
		sensor.foundObject();
		home.checkSensorAndAlarm();
	}

}

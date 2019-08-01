package chap5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration // 환경 설정 클래스 (xml 기능을 대체할 수 있는 클래스), 3.0 버전 이후 사용가능
public class SpringConfig {
	// xml로 표현 : <bean id ="executor" class="chap5.Executor" p:worker-ref="worker" />
	// 			 <bean id ="worker" class="chap5.Worker" .... />
	@Bean // 리턴 객체(executor)를 컨테이너에 저장.메서드 이름이 Bean의 이름.
	public Executor executor() {
		Executor exec = new Executor();
		exec.setWorker(worker());
		return exec;
	}
	@Bean
	@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS) 
	// Scope : Bean 의 범위 지정
	// prototype : 모든 요청에서 새로운 객체를 생성하는 것
	public Worker worker() {
		return new Worker();
	}
	@Bean
	public Camera camera1() {
		Camera c = new Camera();
		c.setNumber(1);
		return c;
	}
	@Bean
	public Camera camera2() {
		Camera c = new Camera();
		c.setNumber(2);
		return c;
	}
	@Bean
	public Camera camera3() {
		Camera c = new Camera();
		c.setNumber(3);
		return c;
	}
	@Bean
	public Camera camera4() {
		Camera c = new Camera();
		c.setNumber(4);
		return c;
	}
	@Bean
	public InfraredRaySensor windowSensor() {
		InfraredRaySensor s = new InfraredRaySensor("창센서");
		return s;
	}
	@Bean
	public InfraredRaySensor doorSensor() {
		InfraredRaySensor s = new InfraredRaySensor("현관센서");
		return s;
	}
	@Bean
	public InfraredRaySensor lampSensor() {
		InfraredRaySensor s = new InfraredRaySensor("전등센서");
		return s;
	}
	@Bean
	public AlarmDevice alarmDevice() {
		return new SmsAlarmDevice();
	}
	@Bean
	public Viewer viewer() {
		MonitorViewer v = new MonitorViewer();
		v.setDisplayMode(displayMode());
		return v;
	}
	@Bean
	public DisplayMode displayMode() {
		DisplayMode d = new DisplayMode();
		d.setType("GRID");
		return d;
	}
	@Bean(initMethod="init") // 객체 초기화 (Bean 생성) 이후에 HomeController 의 init 메서드 호출
	public HomeController homeController() {
		HomeController h = new HomeController();
		List<InfraredRaySensor> sensors = new ArrayList<InfraredRaySensor>();
		sensors.add(windowSensor());
		sensors.add(doorSensor());
		sensors.add(lampSensor());
		h.setSensors(sensors);
		h.prepare(alarmDevice(), viewer());
		h.setCamera1(camera1());
		h.setCamera2(camera2());
		h.setCamera3(camera3());
		h.setCamera4(camera4());
		return h;
	}
}

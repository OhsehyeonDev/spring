package chap4;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
// @Component : 객체화 되서 컨테이너에 저장될 클래스 (xml 의 설정에 의해, 반드시 필요)
// 이름은 homeController 형태로 저장
// xml 구조로 표현하면 : <bean id="homeController" class="chap4.HomeController"> ... </bean>

@Component
public class HomeController {
	private AlarmDevice alarmDevice;
	private Viewer viewer;
	
	// @Resource : 컨테이너 이름이 camera1인 객체를 camera1 참조변수에 저장.
	@Resource(name="camera1")
	private Camera camera1;
	@Resource(name="camera2")
	private Camera camera2;
	@Resource(name="camera3")
	private Camera camera3;
	@Resource(name="camera4")
	private Camera camera4;
	private List<InfraredRaySensor> sensors;
	
	@Autowired(required=false) // required=false : 객체가 없어도 가능함. (없는 경우 null 값)
	private Recorder recorder;
	
	
	// alarmDevice : SmsAlarmDevice 객체 주입.
	// viewer : MonitorViewer 객체 주입. (MonitorViewer 가 Viewer 인터페이스를 구현) 
	@Autowired
	public void prepare(AlarmDevice alarmDevice, Viewer viewer ) {
		this.alarmDevice = alarmDevice;
		this.viewer = viewer;
	}
	// @PostConstruct : HomeController 객체가 생성 완료 후 호출되는 메서드
	@PostConstruct
	public void init() {
		viewer.add(camera1);
		viewer.add(camera2);
		viewer.add(camera3);
		viewer.add(camera4);
		viewer.draw();
	}
	// @Autowired : 자료형에 맞는 객체를 찾아서 주입.(가장 많이 사용) 자료형에 맞는 객체가 없으면 오류 발생
	@Autowired // InfraredRaySensor 객체들이 주입
	@Qualifier("intrusionDetection") // 그 중 별명이 intrusionDetection 인 객체 들만 주입
	public void setSensors(List<InfraredRaySensor> sensors) {
		this.sensors = sensors;
		for(InfraredRaySensor s : sensors) {
			System.out.println("센서 등록:" + s);
		}
	}
	public void checkSensorAndAlarm() {
		for(InfraredRaySensor s : sensors) {
			if(s.isObjectFounded()) {
				alarmDevice.alarm(s.getName());
			}
		}
	}
}

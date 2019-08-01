package chap5;

import java.util.List;

public class HomeController {
	private AlarmDevice alarmDevice;
	private Viewer viewer;
	private Camera camera1;
	private Camera camera2;
	private Camera camera3;
	private Camera camera4;
	private List<InfraredRaySensor> sensors;
	
	private Recorder recorder;
	
	public void prepare(AlarmDevice alarmDevice, Viewer viewer ) {
		this.alarmDevice = alarmDevice;
		this.viewer = viewer;
	}

	public void init() {
		viewer.add(camera1);
		viewer.add(camera2);
		viewer.add(camera3);
		viewer.add(camera4);
		viewer.draw();
	}
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
	public void setCamera1(Camera camera1) {
		this.camera1 = camera1;
	}
	public void setCamera2(Camera camera2) {
		this.camera2 = camera2;
	}
	public void setCamera3(Camera camera3) {
		this.camera3 = camera3;
	}
	public void setCamera4(Camera camera4) {
		this.camera4 = camera4;
	}
	
}

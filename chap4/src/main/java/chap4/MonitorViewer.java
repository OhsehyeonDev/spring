package chap4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonitorViewer implements Viewer {
	@Autowired
	private DisplayMode displayMode; // 객체 주입 완료. xml 의 <bean id="displayMode" class="chap4.DisplayMode" p:type="GRID" />
	public void add (Camera camera) {
		System.out.println("모니터에" + camera + "영상 추가");
	}
	public void draw() {
		System.out.println(displayMode.getType() + "모드로 카메라 이미지 출력됨");
	}
}

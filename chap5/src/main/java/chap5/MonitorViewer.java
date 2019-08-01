package chap5;

public class MonitorViewer implements Viewer {

	private DisplayMode displayMode;
	public void add (Camera camera) {
		System.out.println("모니터에" + camera + "영상 추가");
	}
	public void draw() {
		System.out.println(displayMode.getType() + "모드로 카메라 이미지 출력됨");
	}
	public void setDisplayMode(DisplayMode displayMode) {
		this.displayMode = displayMode;
	}
}

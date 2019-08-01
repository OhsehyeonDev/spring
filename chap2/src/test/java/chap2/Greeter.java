package chap2;

public class Greeter {
	private String format;
	public String greet(String guest) {
		return String.format(format, guest); // String.format : 지정된 위치에 값을 대입해서 문자열을 만들어 내는 용도 (format, guest) : 안녕하세요, %s
	}
	public void setFormat(String format) { // setFormat : format 의 값을 설정  ("안녕하세요, %s") 문자열 값
		this.format = format;
	}
}

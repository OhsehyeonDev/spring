package xml;

public class LoggingAdvice {
	public void before() {
		System.out.println("[LA]메서드 실행 전 전처리 수행 기능");
	}
	public void afterReturning(Object ret) { // ret : new Article(1) 객체
		System.out.println("[LA]메서드 정상 처리 후 수행함. ret="+ret); // 핵심 메서드 결과
	}
	public void afterThrowing(Throwable ex) {
		System.out.println("[LA]메서드 예외 발생 후 수행함. 발생 예외:" + ex.getMessage());
	}
	public void afterFinally() { // 정상 or 비정상 실행시 호출
		System.out.println("[LA]메서드 실행 후 수행함.");
	}
}

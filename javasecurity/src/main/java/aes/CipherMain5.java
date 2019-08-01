package aes;

// 파일을 암호화 하기
public class CipherMain5 {
	public static void main(String[] args) {
		String key = "abc1234567";
		CipherUtil.encryptFile("p1.txt", "c.ser", key); // 원본 텍스트, 암호화된 결과값
	}
}

package aes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

class CipherUtil {
	private static byte[] randomKey;
	// 초기화 백터 : iv
	private final static byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, (byte) 0x5A,
			(byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, (byte) 0x5A };
	static Cipher cipher;
	static { // 초기화 부분
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // AES 알고리즘, CBC 모드, PKCS5Padding 패딩방식
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] getRandomKey(String algo) throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance(algo); // 알고리즘 별로 가능한 키의 크기가 미리 지정되어 있음. (형식 제대로 맞춰줘야 함)
		keyGen.init(128); // 128bit로 키를 생성
		SecretKey key = keyGen.generateKey();
		return key.getEncoded();
	}

	public static String encrypt(String plain) {
		byte[] cipherMsg = new byte[1024];
		try {
			// AES 암호 알고리즘용 128bit 짜리 Key
			randomKey = getRandomKey("AES");
			Key key = new SecretKeySpec(randomKey, "AES");
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv); // CBC 방식의 시작 점에 초기화 벡터 (iv) 값을 넣어줘야 한다.
			// AES 알고리즘으로 암호화를 하기 위한 암호 객체 초기화
			cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			// cipherMsg : 암호문 생성 (평문을 암호화)
			cipherMsg = cipher.doFinal(paddingString(plain).getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteToHex(cipherMsg).trim(); // cipherMsg(바이트형 배열) 을 헥사값 (16진수 2자리수 형태) 으로 변환
	}

	public static String encrypt(String plain, String key) {
		byte[] cipherMsg = new byte[1024];
		try {
			Key genKey = new SecretKeySpec(makeKey(key), "AES");
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, genKey, paramSpec);
			cipherMsg = cipher.doFinal(plain.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteToHex(cipherMsg);
	}

	private static byte[] makeKey(String key) { // 16Byte 단위로 키를 고정
		int len = key.length();
		char ch = 'A';
		for (int i = len; i < 16; i++) {
			key += ch++;
		}
		return key.substring(0, 16).getBytes();
	}

	private static String paddingString(String plain) {
		char paddingChar = ' ';
		int size = 16;
		int x = plain.length() % size;
		int len = size - x;
		for (int i = 0; i < len; i++) {
			plain += paddingChar;
		}
		return plain;
	}

	private static String byteToHex(byte[] cipherMsg) {
		if (cipherMsg == null)
			return null;
		String str = "";
		for (byte b : cipherMsg) {
			str += String.format("%02X", b);
		}
		return str;
	}

	public static String decrypt(String cipherMsg) {
		byte[] plainMsg = new byte[1024];
		try {
			Key key = new SecretKeySpec(randomKey, "AES");
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
			// 복호화 모드로 암호화 객체 초기화
			cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			plainMsg = cipher.doFinal(hexToByte(cipherMsg.trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(plainMsg).trim();
	}

	public static String decrypt(String cipher1, String key) {
		byte[] plainMsg = new byte[1024];
		try {
			Key genkey = new SecretKeySpec(makeKey(key), "AES");
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, genkey, paramSpec);
			plainMsg = cipher.doFinal(hexToByte(cipher1.trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(plainMsg).trim();
	}

	private static byte[] hexToByte(String str) {
		if (str == null)
			return null;
		if (str.length() < 2)
			return null;
		int len = str.length() / 2;
		byte[] buf = new byte[len];
		for (int i = 0; i < len; i++) {
			buf[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
		}

		return buf;
	}

	public static void encryptFile(String plainFile, String cipherFile, String strkey) {
		try {
			getKey(strkey);
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("key.ser"));
			Key key = (Key)ois.readObject();
			ois.close();
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			FileInputStream fis = new FileInputStream(plainFile);
			FileOutputStream fos = new FileOutputStream(cipherFile);
			CipherOutputStream cos = new CipherOutputStream(fos, cipher);
			byte[] buf = new byte[1024];
			int len;
			while ((len = fis.read(buf)) != -1) {
				cos.write(buf, 0, len);
			}
			fis.close();
			cos.flush();
			fos.flush();
			cos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getKey(String key) throws Exception {
		Key genkey = new SecretKeySpec(makeKey(key), "AES");
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("key.ser"));
		out.writeObject(genkey);
		out.flush();
		out.close();
	}

	public static void decryptFile(String plainFile, String cipherFile) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("key.ser"));
			Key key = (Key)ois.readObject();
			ois.close();
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			FileInputStream fis = new FileInputStream(plainFile);
			FileOutputStream fos = new FileOutputStream(cipherFile);
			CipherOutputStream cos = new CipherOutputStream(fos, cipher);
			byte[] buf = new byte[1024];
			int len;
			while ((len = fis.read(buf)) != -1) {
				cos.write(buf, 0, len);
			}
			fis.close();
			cos.flush();
			fos.flush();
			cos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
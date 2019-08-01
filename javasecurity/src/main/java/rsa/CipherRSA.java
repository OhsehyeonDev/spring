package rsa;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/*
 * RSA : 공개키 암호 알고리즘 (비대칭 키, 키페어)
 * 공개키로 암호화 -> 개인키로 복호화 가능
 * 개인키로 암호화 -> 공개키로 복호화 가능
 */
public class CipherRSA {
	static Cipher cipher;
	static PrivateKey priKey;
	static PublicKey pubKey;
	static {
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); // [RSA 암호화 알고리즘 / ECB모드 / 패딩]
			KeyPairGenerator key = KeyPairGenerator.getInstance("RSA"); // KeyPair : 공개키 암호화 방식
			key.initialize(2048); // Key 크기 지정
			KeyPair keyPair = key.generateKeyPair();
			priKey = keyPair.getPrivate(); // 1개씩만 존재 (static)
			pubKey = keyPair.getPublic(); // 1개씩만 존재 (static)
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
// 개인키, 공개키 모두 암호화, 복호화에 각각 사용 가능 
// 개인키로 암호화시 부인방지 기능 (거래 사실을 부인 못하게 하는 공증(Notarization)과 같은 보안 기능)

	public static String encrypt(String plain) {
		byte[] cipherMsg = new byte[1024];
		try {
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			cipherMsg = cipher.doFinal(plain.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteToHex(cipherMsg);
	}

	private static String byteToHex(byte[] cipherMsg) {
		if (cipherMsg == null)
			return null;
		int len = cipherMsg.length;
		String str = "";
		for (byte b : cipherMsg) {
			str += String.format("%02X", b);
		}
		return str;
	}

	public static String decrypt(String cipherMsg) {
		byte[] plainMsg = new byte[1024];
		try {
			cipher.init(Cipher.DECRYPT_MODE, priKey);
			plainMsg = cipher.doFinal(hexToByte(cipherMsg.trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(plainMsg).trim();
	}

	private static byte[] hexToByte(String str) {
		if (str == null || str.length() < 2)
			return null;
		byte[] buf = new byte[str.length() / 2];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
		}
		return buf;
	}

	public static void getKey() {
		try {
			KeyPairGenerator key = KeyPairGenerator.getInstance("RSA");
			key.initialize(2048); // 키의 크기 2048비트
			KeyPair keyPair = key.generateKeyPair(); // 키의 쌍으로 설정
			PrivateKey priKey = keyPair.getPrivate(); // 개인키
			PublicKey pubKey = keyPair.getPublic(); // 공개키
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("privatekey.ser"));
			out.writeObject(priKey); // 개인키(Object 객체 형태로) 를 privatekey.ser 파일로 저장
			out.flush();
			out.close();
			out = new ObjectOutputStream(new FileOutputStream("publickey.ser"));
			out.writeObject(pubKey); // 공개키를 publickey.ser 파일로 저장
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void encryptFile(String plainFile, String cipherFile) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("privatekey.ser"));
			PrivateKey priKey = (PrivateKey) ois.readObject(); // Object -> PrivateKey 형변환 필요
			ois.close();
			cipher.init(Cipher.ENCRYPT_MODE, priKey);
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

	public static void decryptFile(String cipherFile, String plainFile) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("publickey.ser"));
			PublicKey pubKey = (PublicKey) ois.readObject();
			ois.close();
			cipher.init(Cipher.DECRYPT_MODE, pubKey);
			FileInputStream fis = new FileInputStream(cipherFile);
			FileOutputStream fos = new FileOutputStream(plainFile);
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
package hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/* 
 * 화면에서 아이디와 비밀번호를 입력 받아서
 * 해당 아이디가 userbackup 테이블이 없으면 "아이디확인" 출력
 * 해당 아이디의 비밀번호를 비교해서 맞으면 "반갑습니다. 아이디님" 출력
 * 해당 아이디의 비밀번호를 비교해서 틀리면 "비밀번호 확인" 출력
 */

// 비밀번호 입력 후 DB 해쉬 비밀번호와 비교
public class DigestMain3 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("아이디를 입력하세요.");
			String id = br.readLine();
			System.out.println("비밀번호를 입력하세요.");
			String pass = br.readLine();
			Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/model1", "model1", "1234");
			PreparedStatement pstmt = conn.prepareStatement("select userid, password from userbackup where userid=?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (!rs.next()) {
				System.out.println("아이디 확인");
			}

			MessageDigest md = MessageDigest.getInstance("SHA-256");

			while (true) {
				String hashpass = "";
				byte[] plain = null;
				byte[] hash = null;
				plain = pass.getBytes();
				hash = md.digest(plain);
				for (byte b : hash) {
					hashpass += String.format("%02X", b);
				}

				if (rs.getString(1).equals(hashpass)) {
					System.out.println("반갑습니다." + id + "님");
					break;
				} else {
					System.out.println("비밀번호가 틀렸습니다. 다시 입력해주세요");
					pass = br.readLine();

				}
			}
			System.out.println("계속하시겠습니까? Y/N");
			if (br.readLine().equalsIgnoreCase("Y")) {
				continue;
			} else
				break;
		}
	}
}

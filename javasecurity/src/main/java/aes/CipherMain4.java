package aes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *  userbackup2 테이블의 암호화된 email 값을 복호화하여 화면에 출력하기.
 *  key는 비밀번호 해쉬값의 앞의 16자리로 정한다. 
 */
public class CipherMain4 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/model1", "model1", "1234");
		PreparedStatement pstmt = conn.prepareStatement("select userid,password,email from userbackup2");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String id = rs.getString(1);
			String pass = rs.getString(2).substring(0, 16);
			String email = rs.getString(3);
			String palinemail = CipherUtil.decrypt(email, pass);
			System.out.println(id + " 이메일 =" + palinemail);

		}
	}
}

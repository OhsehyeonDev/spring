package aes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *  userbackup2 테이블의 email 값을 암호화하기.
 *  key는 비밀번호 해쉬값의 앞의 16자리로 정한다. 
 */
public class CipherMain3 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/model1", "model1", "1234");
		PreparedStatement pstmt = conn.prepareStatement("select userid,password,email from useraccount");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String id = rs.getString(1);
			String pass = rs.getString(2).substring(0, 16);
			String email = rs.getString(3);
			String newemail = CipherUtil.encrypt(email, pass);
			pstmt = conn.prepareStatement("update useraccount set email=? where userid=?");
			pstmt.setString(1, newemail);
			pstmt.setString(2, id);
			pstmt.execute();

		}
	}
}

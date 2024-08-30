
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport implements SessionAware {

	private String username;
	private String password;
	private String email;
	private String logOff;

	private SessionMap session;

	//Getters & Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogOff() {
		return logOff;
	}

	public void setLogOff(String logOff) {
		this.logOff = logOff;
	}

	// Set my session
	@Override
	public void setSession(Map map) {
		session = (SessionMap) map;
	}

	// Login method
	public String login() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC", "username", "password")) {
				try (PreparedStatement checkAccount = connection
						.prepareStatement("SELECT * FROM accounts WHERE username = ? AND password = ?")) {
					checkAccount.setString(1, username);
					checkAccount.setString(2, password);
					ResultSet resultSet = checkAccount.executeQuery();

					if (resultSet.next()) {
						email = resultSet.getString("email");

						session.put("currentUser", username);
						session.put("currentPassword", password);
						session.put("currentEmail", email);
						System.out.println("Seller from session: " + session.get("currentUser"));
						return "SUCCESS";
					} else {
						// Clear session data in case of unsuccessful login
						session.clear();
						return "ERROR";
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}

	}

//Logoff method
	public String logoff() {
		try {

			// Check if logOff is set to logOffUser
			if (logOff != null && logOff.equals("logOffUser")) {
				session.invalidate();
				return "SUCCESS";
			} else {
				return "ERROR";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Register {
    private String username;
    private String password;
    private String passwordRe;
    private String email;

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

    public String getPasswordRe() {
        return passwordRe;
    }

    public void setPasswordRe(String passwordRe) {
        this.passwordRe = passwordRe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String register() {
        try {
        	//Loads my database before attempting to make the connection
        	//as im using an older version of mySQL connector
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	
            if (username == null || username.trim().isEmpty() ||
                    password == null || password.trim().isEmpty() ||
                    passwordRe == null || passwordRe.trim().isEmpty() ||
                    email == null || email.trim().isEmpty()) {
                    return "ERROR";
                }
        	        
            if ( !password.equals(passwordRe)) {
                return "ERROR";
            }
            
            if (isUsernameTaken(username) ) {
                return "ERROR";
            }
            
            if (isEmailTaken(email) ) {
                return "ERROR";
            }
            
            //Register user into accounts
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC", "username", "password")) {
                try (PreparedStatement createAccount = connection.prepareStatement("INSERT INTO accounts (username, password, email) VALUES (?, ?, ?)")) {
                    createAccount.setString(1, username);
                    createAccount.setString(2, password);
                    createAccount.setString(3, email);
                    int rowsUpdated = createAccount.executeUpdate();
                    return "SUCCESS";
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
   //Check if email is in use 
    public boolean isEmailTaken(String email) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC", "username", "password")) {
            try (PreparedStatement checkEmail = connection.prepareStatement("SELECT * FROM accounts WHERE email = ?")) {
                checkEmail.setString(1, email);
                try (ResultSet resultSet = checkEmail.executeQuery()) {
                    return resultSet.next(); 
                }
            }
        }
    }
//Check if username is in use
    public boolean isUsernameTaken(String username) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC", "username", "password")) {
            try (PreparedStatement checkUsername = connection.prepareStatement("SELECT * FROM accounts WHERE username = ?")) {
                checkUsername.setString(1, username);
                try (ResultSet resultSet = checkUsername.executeQuery()) {
                    return resultSet.next(); 
                }
            }
        }
    }

}

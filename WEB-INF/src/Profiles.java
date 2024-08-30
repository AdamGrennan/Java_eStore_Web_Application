import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

public class Profiles extends ActionSupport {
    private ArrayList<User> userList;
    private String searchUser;
    private String searchEmail;

    //Method for when btn view profile is clicked 
    public String viewProfile() {
        return "SUCCESS";
    }
    //Method for when btn search profile is clicked
    public String searchProfile() {
        return "SUCCESS";
    }

    //Returns the userList to display names of all users
    public ArrayList<User> getUserList() {
        return userList;
    }
    
    // Getters & Setters
    public String getSearchUser() {
        return searchUser;
    }

    public String getSearchEmail() {
        return searchEmail;
    }

    public void setSearchUser(String searchUser) {
        this.searchUser = searchUser;
    }
    
    //Method when user clicks onto view all profiles button
    public String viewAllUsers() {
        try {
         
            userList = fetchAllUsers();

            return "SUCCESS";
        } catch (Exception e) {
            
            e.printStackTrace();
            return "ERROR";
        }
    }

    // Method to fetch all users from the database
    public ArrayList<User> fetchAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC", "username", "password")) {
            try (PreparedStatement viewAll = connection.prepareStatement("SELECT * FROM accounts")) {
                ResultSet resultSet = viewAll.executeQuery();

                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String email = resultSet.getString("email");

                    User user = new User();
                    user.setUsername(username);
                    user.setEmail(email);
                    users.add(user);
                }
            }
        }

        return users;
    }
    
    public String searchForUser() {
        try {
            if (searchUser == null) {
                return "ERROR";
            }

            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC", "username", "password")) {
                try (PreparedStatement searchAccount = connection.prepareStatement("SELECT * FROM accounts WHERE username = ?")) {
                    searchAccount.setString(1, searchUser);

                    System.out.println("Executing SQL Query: " + searchAccount);

                    ResultSet resultSet = searchAccount.executeQuery();

                    if (resultSet.next()) {
                        searchEmail = resultSet.getString("email");
                        return "SUCCESS";
                    } else {
                        return "ERROR";
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }


}

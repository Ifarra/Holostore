package Controller;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Login;

public class RegController extends Login {
	
    @FXML
    private Label regalert;
	
    @FXML
    private Button regbtnfr;

    @FXML
    private TextField regemail;

    @FXML
    private TextField regpass;

    @FXML
    private TextField reguser;

	    
		private static final String DB_URL = "jdbc:mysql://localhost:3306/login_system";
	    private static final String DB_USER = "root";
	    private static final String DB_PASSWORD = "rafiind1324";
	    
//	    private static final String SELECT_USER_QUERY = "SELECT * FROM users WHERE username = ? AND password = ?";
	    private static final String INSERT_USER_QUERY = "INSERT INTO users (userid, username, password) VALUES (0, ?, ?)";
	    private static final String INSERT_REG_QUERY = "INSERT INTO account (idaccount, usernameaccount, passwordaccount, emailaccount) VALUES (0, ?, ?, ?)";
	    private static final String INSERT_MYUSR_QUERY = "INSERT INTO myusr VALUES (0, ?, ?, ?, ?, ?)";
	    
	    
	    public void Regvalfr (ActionEvent e) throws ClassNotFoundException {
	    	String usernameaccount = reguser.getText();
	        String passwordaccount = regpass.getText();
	        String emailaccount = regemail.getText();
	        String genderaccount = "kosong";
	        String addressaccount = "kosong";
	        boolean isValid = registerUser(usernameaccount,passwordaccount,emailaccount,genderaccount,addressaccount);
	        if (isValid) {
	        	regalert.setStyle("-fx-text-fill: green");
	        	regalert.setText("Registration succsess");
	        } else {
	        	regalert.setStyle("-fx-text-fill: red");
	        	regalert.setText("Error registration");
	        }
	    }
	    
	    
	    private static boolean registerUser(String usernameaccount, String passwordaccount, String emailaccount, String genderaccount, String addressaccount) throws ClassNotFoundException {
	        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        		PreparedStatement statement = connection.prepareStatement(INSERT_REG_QUERY);
		            PreparedStatement statement1 = connection.prepareStatement(INSERT_USER_QUERY);
		            PreparedStatement statement2 = connection.prepareStatement(INSERT_MYUSR_QUERY);
		            statement.setString(1, usernameaccount);
		            statement.setString(2, passwordaccount);
		            statement.setString(3, emailaccount);
		            statement1.setString(1, usernameaccount);
		            statement1.setString(2, passwordaccount);
		            statement2.setString(1, usernameaccount);
		            statement2.setString(2, passwordaccount);
		            statement2.setString(3, emailaccount);
		            statement2.setString(4, genderaccount);
		            statement2.setString(5, addressaccount);
		            int rowsAffected = statement.executeUpdate();
		            statement1.executeUpdate();
		            statement2.executeUpdate();
		            return rowsAffected > 0; // If at least one row is affected, user is registered
//	        	}
	        } catch (SQLException e) {
	            System.out.println("Error registering user: " + e.getMessage());
	            return false;
	        }
	    }
}

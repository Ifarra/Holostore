package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

// inheritance
public class Login extends abstractUwU{

    private static final String DB_URL = "jdbc:mysql://localhost:3306/login_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "rafiind1324";
    
    private static final String SELECT_USER_QUERY = "SELECT * FROM users WHERE username = ? AND password = ?";
    private static final String INSERT_USER_QUERY = "INSERT INTO log VALUES (0, ?, ?)";
    //private static final String INSERT_REG_QUERY = "INSERT INTO users (idaccount, useraccount, passwordaccount, emailaccount) VALUES (0, ?, ?, ?)";

    
    @FXML
    private Label alerttxt;

    @FXML
    private Button loginbtn;

    @FXML
    private PasswordField passwordtxt;

    @FXML
    private Button regbtn;

    @FXML
    private TextField usernametxt;
    
    
    private Stage stage;
	private Scene scene;
	private Parent root;
	
	// absraction 2
	private String log() {
		String userdata = usernametxt.getText();
		String passdata = usernametxt.getText();
		String data = userdata + passdata;
		return data;
	}
	
    public void Loginval (ActionEvent e) throws ClassNotFoundException, IOException {
    	log();
    	String username = usernametxt.getText();
        String password = passwordtxt.getText();
        boolean isValid = validateUserAccount(username,password);
        if (isValid)  {
        	fetchUser(usernametxt.getText(),passwordtxt.getText());
        	alerttxt.setStyle("-fx-text-fill: green");
        	alerttxt.setText("Succsess");
        	root  = FXMLLoader.load(getClass().getResource("../views/market.fxml"));
    		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
    		stage.setTitle("Holostore");
    		scene = new Scene(root);
    		stage.setFullScreen(false);
    		stage.setScene(scene);
    		stage.setFullScreen(true);
    		stage.show();
        } else {
        	alerttxt.setStyle("-fx-text-fill: red");
        	alerttxt.setText("Invalid username or password");
        }
    }
    
    public void Regval (ActionEvent e) throws ClassNotFoundException, IOException {
    	FXMLLoader fxmll = new FXMLLoader(getClass().getResource("../views/reg.fxml"));
    	Parent root1 = (Parent) fxmll.load();
    	Stage stage = new Stage();
    	stage.setTitle("Holostore");
		stage.setScene(new Scene(root1));
		stage.show();
		
    }
    
    private static boolean validateUserAccount(String username, String password) throws ClassNotFoundException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_QUERY);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // If result set has at least one row, user account is valid
        } catch (SQLException e) {
            System.out.println("Error validating user account: " + e.getMessage());
            return false;
        }
    }
    
    private static void fetchUser(String Usernamelog, String Passwordlog) throws ClassNotFoundException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        		PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY);
	            statement.setString(1, Usernamelog);
	            statement.setString(2, Passwordlog);
	            statement.executeUpdate();
//        	}
        } catch (SQLException e) {
            System.out.println("Error registering user login: " + e.getMessage());
        }
    }

}

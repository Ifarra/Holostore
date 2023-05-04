package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Controller.MarketController;

public class MyaccController implements Initializable{

    @FXML
    private TextField accuser;

    @FXML
    private Label addresslbl;

    @FXML
    private Label emaillbl;

    @FXML
    private Label genderlbl;

    @FXML
    private TextField myaccaddress;

    @FXML
    private Button myaccdelete;

    @FXML
    private TextField myaccemail;

    @FXML
    private ChoiceBox<String> myaccgender;

    @FXML
    private TextField myaccpass;

    @FXML
    private Button myaccupdate;

    @FXML
    private Button myaccupdate1;

    @FXML
    private Label passlbl;

    @FXML
    private Label userlbl;
    
    
    //encapsulation
    private static String[] cbx = {"Male","Female","Unidentified"};
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/login_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "rafiind1324";
    
    private static final String SELECT_USER_QUERY = "SELECT * FROM users WHERE username = ? AND password = ?";
//    private static final String SELECT_LOGuser_QUERY = "SELECT Usernamelog FROM log";
//    private static final String SELECT_LOGpass_QUERY = "SELECT Passwordlog FROM log";
    //private static final String INSERT_USER_QUERY = "INSERT INTO users (userid, username, password) VALUES (0, ?, ?)";
    private static final String INSERT_REG_QUERY = "INSERT INTO myusr VALUES (0, ?, ?, ?, ?, ?)";
    private static final String DELETE_REG_QUERY = "DELETE FROM users WHERE (username = ? AND password = ?)";
    private static final String DELETE2_REG_QUERY = "DELETE FROM log";
    private static final String DELETE3_REG_QUERY = "DELETE FROM account WHERE (usernameaccount = ? AND passwordaccount = ?)";
    private static final String UPDATE_REG_QUERY = "UPDATE myusr SET usernameaccount = ?, passwordaccount = ?, emailaccount = ?, genderaccount = ?, addressaccount = ? WHERE (usernameaccount = ?)";;
    

    String user = "kosong";
    String password = "kosong";
    String email = "kosong";
    String address = "kosong";
   
    
    public void Updateuser (ActionEvent e) throws ClassNotFoundException {
    	String usernameaccount = accuser.getText();
        String passwordaccount = myaccpass.getText();
        String emailaccount = myaccemail.getText();
        String genderaccount = myaccgender.getValue();
        String addressaccount = myaccaddress.getText();
        boolean isValid = registerUser(usernameaccount,passwordaccount,emailaccount,genderaccount,addressaccount);
        if (isValid) {
//        	regalert.setStyle("-fx-text-fill: green");
//        	regalert.setText("Registration succsess");
        } else {
//        	regalert.setStyle("-fx-text-fill: red");
//        	regalert.setText("Error registration");
        }
    }
    
    public void Deleteuser (ActionEvent e) throws ClassNotFoundException, IOException, SQLException {
    	String usernameaccount = accuser.getText();
        String passwordaccount = myaccpass.getText();
        boolean isValid = deleteUser(usernameaccount,passwordaccount);
        MarketController mc = new MarketController();
        if (isValid) {
        	mc.exit();
//        	regalert.setStyle("-fx-text-fill: green");
//        	regalert.setText("Registration succsess");
        } else {
//        	regalert.setStyle("-fx-text-fill: red");
//        	regalert.setText("Error registration");
        }
    }
    
    
    public void Refresh (ActionEvent ev) throws ClassNotFoundException, SQLException {
    	Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    	String query = "SELECT * FROM myusr"; // replace with your table name
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        if (resultSet.next()) {
            int index = resultSet.getInt("index"); // replace with your column names
            String name = resultSet.getString("usernameaccount");
            String pass = resultSet.getString("passwordaccount");
            String email = resultSet.getString("emailaccount");
            String gender = resultSet.getString("genderaccount");
            String address = resultSet.getString("addressaccount");
            
            userlbl.setText(name);
        	passlbl.setText(pass);
        	emaillbl.setText(email);
        	addresslbl.setText(address);
        	genderlbl.setText(gender);
        	
        	accuser.setText(name);
        	myaccpass.setText(pass);
        	myaccemail.setText(email);
        	myaccaddress.setText(address);
        }
            
    	
    }
    
    public void Updateuserreal (ActionEvent ev) throws ClassNotFoundException {
    	String usernameaccount = accuser.getText();
        String passwordaccount = myaccpass.getText();
        String emailaccount = myaccemail.getText();
        String genderaccount = myaccgender.getValue();
        String addressaccount = myaccaddress.getText();
        String userscp = accuser.getText();
        boolean isValid = updateUserReal(usernameaccount,passwordaccount,emailaccount,genderaccount,addressaccount,userscp);
        if (isValid) {
//        	regalert.setStyle("-fx-text-fill: green");
//        	regalert.setText("Registration succsess");
        } else {
//        	regalert.setStyle("-fx-text-fill: red");
//        	regalert.setText("Error registration");
        }
    }
    
    private static boolean updateUserReal(String useraccount, String passwordaccount, String emailaccount, String genderaccount, String addressaccount, String userscp) throws ClassNotFoundException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        		PreparedStatement statement = connection.prepareStatement(UPDATE_REG_QUERY);
        		
        		statement.setString(1, passwordaccount);
        		statement.setString(2, useraccount);
        		statement.setString(3, emailaccount);
        		statement.setString(4, genderaccount);
        		statement.setString(5, addressaccount);
        		statement.setString(6, userscp);
        		
	            int rowsAffected = statement.executeUpdate();
	            return rowsAffected > 0; // If at least one row is affected, user is registered
//        	}
        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }
    
    private static boolean registerUser(String usernameaccount, String passwordaccount, String emailaccount, String genderaccount, String addressaccount) throws ClassNotFoundException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        		PreparedStatement statement = connection.prepareStatement(INSERT_REG_QUERY);
	            statement.setString(1, usernameaccount);
	            statement.setString(2, passwordaccount);
	            statement.setString(3, emailaccount); 
	            statement.setString(4, genderaccount); 
	            statement.setString(5, addressaccount); 
	            
	            int rowsAffected = statement.executeUpdate();
	            return rowsAffected > 0; // If at least one row is affected, user is registered
//        	}
        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }
    
    private static boolean deleteUser(String usernameaccount, String passwordaccount) throws ClassNotFoundException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        		PreparedStatement statement = connection.prepareStatement(DELETE_REG_QUERY);
        		PreparedStatement statement2 = connection.prepareStatement(DELETE2_REG_QUERY);
        		PreparedStatement statement3 = connection.prepareStatement(DELETE3_REG_QUERY);
        		statement.setString(1, usernameaccount);
        		statement.setString(2, passwordaccount);
        		statement3.setString(1, usernameaccount);
        		statement3.setString(2, passwordaccount);
	            
	            int rowsAffected = statement.executeUpdate();
	            statement2.executeUpdate();
	            statement3.executeUpdate();
	            return rowsAffected > 0; // If at least one row is affected, user is registered
//        	}
        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }
    
    // inner classes
    class loguser {
    	// polymorph
    public static void log(String usernameaccount, String passwordaccount) throws ClassNotFoundException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_QUERY);
            statement.setString(1, usernameaccount);
            statement.setString(2, passwordaccount);
            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error validating user account: " + e.getMessage());
        }
    }
    
    
    private static void log() throws ClassNotFoundException {
    	try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
    		String query = "SELECT * FROM myusr"; // replace with your table name
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
    		MyaccController control = new MyaccController();
    		
    		if (resultSet.next()) {
                int index = resultSet.getInt("index"); 
                String name = resultSet.getString("usernameaccount");
                String pass = resultSet.getString("passwordaccount");
                String email = resultSet.getString("emailaccount");
                String gender = resultSet.getString("genderaccount");
                String address = resultSet.getString("addressaccount");

                control.user = name;
                control.password = pass;
                control.email = email;
                control.address = address;
    		}
            
        } catch (SQLException e) {
            System.out.println("Error validating user account awal: " + e.getMessage());
        }
    }
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myaccgender.getItems().addAll(cbx);
		
		try {
			loguser.log();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
}

package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
import main.MyListener;
import model.Merch;

public class MarketController implements Initializable {
    
	 @FXML
	    private Button buybtn;

	    @FXML
	    private VBox chosenFruitCard;

	    @FXML
	    private Button exittxt;

	    @FXML
	    private ImageView fruitImg;

	    @FXML
	    private Label fruitNameLable;

	    @FXML
	    private Label fruitPriceLabel;

	    @FXML
	    private GridPane grid;

	    @FXML
	    private Label itemName;

	    @FXML
	    private Button qtymin;

	    @FXML
	    private Button qtyplus;

	    @FXML
	    private TextField qtytxt;

	    @FXML
	    private ScrollPane scroll;

	    @FXML
	    private Label username;

	  

	    private static final String DB_URL = "jdbc:mysql://localhost:3306/login_system";
	    private static final String DB_USER = "root";
	    private static final String DB_PASSWORD = "rafiind1324";

	    private List<Merch> merchs = new ArrayList<>();
	    private Image image;
	    private MyListener myListener;

    private List<Merch> getData() {
        List<Merch> merchs = new ArrayList<>();
        Merch merch;

        merch = new Merch();
        merch.setName("Mumei");
        merch.setPrice(2.25);
        merch.setImgSrc("/img/Abu2.png");
        merch.setItemName("Nanashi Mumei New Outfit Celebration 2023");
        merch.setColor("bf2c3b");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("Tempus");
        merch.setPrice(1.15);
        merch.setImgSrc("/img/Biru_1.png");
        merch.setItemName("TEMPANTSU April Fools' Day 2023");
        merch.setColor("742399");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("HoloID");
        merch.setPrice(1.69);
        merch.setImgSrc("/img/Biru_2.png");
        merch.setItemName("Hololive Indonesia 3rd Anniversary Celebration");
        merch.setColor("722ebf");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("Korone");
        merch.setPrice(3.35);
        merch.setImgSrc("/img/Coklat_1.png");
        merch.setItemName("Inugami Korone 4th Anniversary Celebration");
        merch.setColor("dbb235");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("Korone");
        merch.setPrice(3.95);
        merch.setImgSrc("/img/Coklat_2.png");
        merch.setItemName("Inugami Korone 4th Anniversary Celebration");
        merch.setColor("dbb235");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("Mumei");
        merch.setPrice(2.22);
        merch.setImgSrc("/img/Cream.png");
        merch.setItemName("Nanashi Mumei New Outfit Celebration 2023");
        merch.setColor("7d0e17");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("Moona");
        merch.setPrice(0.67);
        merch.setImgSrc("/img/Hitam.png");
        merch.setItemName("Moona Hoshinova 3rd Anniversary Celebration");
        merch.setColor("2e0663");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("Iofi");
        merch.setPrice(9.99);
        merch.setImgSrc("/img/Merah_1.png");
        merch.setItemName("Airani Iofifteen 3rd Anniversary Celebration");
        merch.setColor("750202");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("Iofi");
        merch.setPrice(9.99);
        merch.setImgSrc("/img/Merah_2.png");
        merch.setItemName("Airani Iofifteen 3rd Anniversary Celebration");
        merch.setColor("750202");
        merchs.add(merch);
        
        merch = new Merch();
        merch.setName("Risu");
        merch.setPrice(5.55);
        merch.setImgSrc("/img/MerahMuda_1.png");
        merch.setItemName("Ayunda Risu 3rd Anniversary Celebration");
        merch.setColor("bd7777");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("Risu");
        merch.setPrice(5.55);
        merch.setImgSrc("/img/MerahMuda_2.png");
        merch.setItemName("Ayunda Risu 3rd Anniversary Celebration");
        merch.setColor("bd7777");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("Uproar");
        merch.setPrice(3.33);
        merch.setImgSrc("/img/MerahPutih_1.png");
        merch.setItemName("UPROAR!! 1st Anniversary Celebration");
        merch.setColor("b01515");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("Uproar");
        merch.setPrice(3.95);
        merch.setImgSrc("/img/MerahPutih_2.png");
        merch.setItemName("UPROAR!! 1st Album \"Prologue\"  Limited Edition Bonus Included");
        merch.setColor("0e1f8f");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("Uproar");
        merch.setPrice(2.75);
        merch.setImgSrc("/img/MerahPutih_3.png");
        merch.setItemName("UPROAR!! 1st Album \"Prologue\"");
        merch.setColor("b01515");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("Uproar");
        merch.setPrice(3.55);
        merch.setImgSrc("/img/Putih.png");
        merch.setItemName("UPROAR!! 1st Album \"Prologue\"  Limited Edition Bonus Included ");
        merch.setColor("b01515");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("Moona");
        merch.setPrice(0.67);
        merch.setImgSrc("/img/Ungu_1.png");
        merch.setItemName("Moona Hoshinova 3rd Anniversary Celebration");
        merch.setColor("4e2e78");
        merchs.add(merch);

        merch = new Merch();
        merch.setName("Okayu");
        merch.setPrice(1.99);
        merch.setImgSrc("/img/Ungu_2.png");
        merch.setItemName("Nekomata Okayu 4th Anniversary Celebration");
        merch.setColor("8a62bf");
        merchs.add(merch);
        
        merch = new Merch();
        merch.setName("Okayu");
        merch.setPrice(1.99);
        merch.setImgSrc("/img/Ungu_3.png");
        merch.setItemName("Nekomata Okayu 4th Anniversary Celebration");
        merch.setColor("8a62bf");
        merchs.add(merch);

        return merchs;
    }

    public void qtyM (ActionEvent e) {
    	int qty = Integer.parseInt(qtytxt.getText());
    	if (qty > 1) {
    		qty--;
    		qtytxt.setText(String.valueOf(qty));
    	}
    }
    
    public void qtyP (ActionEvent e) {
    	int qtyy = Integer.parseInt(qtytxt.getText());
    	if (qtyy <= 19 && qtyy >= 1) {
    		qtyy++;
    		qtytxt.setText(String.valueOf(qtyy));
    	}
    }
    
    public void userclick (MouseEvent e) throws IOException, ClassNotFoundException {
    	FXMLLoader fxmll = new FXMLLoader(getClass().getResource("../views/myacc.fxml"));
    	Parent root1 = (Parent) fxmll.load();
    	Stage stage = new Stage();
    	stage.setTitle("Holostore");
		stage.setScene(new Scene(root1));
		stage.show();
    }
    
    private Stage stage;
	private Scene scene;
	private Parent root;
    
    public void userexit (MouseEvent e) throws IOException, ClassNotFoundException, SQLException {
    	exit();
    	root  = FXMLLoader.load(getClass().getResource("../views/sample.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setTitle("Holostore");
		scene = new Scene(root);
		stage.setFullScreen(false);
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.show();
    }
    
    public void exit() throws SQLException {
    	Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    	String query = "DELETE FROM log"; // replace with your table name
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
    
    private void setChosenFruit(Merch merch) {
        fruitNameLable.setText(merch.getName());
        fruitPriceLabel.setText(Main.CURRENCY + merch.getPrice());
        image = new Image(getClass().getResourceAsStream(merch.getImgSrc()));
        fruitImg.setImage(image);
        itemName.setText(merch.getItemName());
        chosenFruitCard.setStyle("-fx-background-color: #" + merch.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
        qtytxt.setText("1");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        merchs.addAll(getData());
        if (merchs.size() > 0) {
            setChosenFruit(merchs.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Merch merch) {
                    setChosenFruit(merch);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < merchs.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(merchs.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

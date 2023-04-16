package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.MyListener;
import model.Merch;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(merch);
    }

    private Merch merch;
    private MyListener myListener;

    public void setData(Merch merch, MyListener myListener) {
        this.merch = merch;
        this.myListener = myListener;
        nameLabel.setText(merch.getName());
        priceLable.setText(Main.CURRENCY + merch.getPrice());
        Image image = new Image(getClass().getResourceAsStream(merch.getImgSrc()));
        img.setImage(image);
    }
}

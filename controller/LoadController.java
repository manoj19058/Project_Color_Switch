package controller;

import Main.Data;
import Main.LoadGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadController implements Initializable {
    private Data d = new Data();
    @FXML
    private AnchorPane LoadScene;
    @FXML
    private Button slot1;
    @FXML
    private Button slot2;
    @FXML
    private Button slot3;
    @FXML
    private Label sl1;
    @FXML
    private Label sl2;
    @FXML
    private Label sl3;
    @FXML
    private ImageView city;
    @FXML
    private Label heading;
    @FXML
    private Button back;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadGame[] a = Data.getA();
        Label[] b = {sl1, sl2, sl3};
        heading.setAlignment(Pos.BASELINE_CENTER);
        for(int i = 0; i < 3; i++){
            b[i].setAlignment(Pos.BASELINE_CENTER);
            if(a[i] == null){
                b[i].setText("Slot - " + (i+1) +"\nEMPTY SLOT");
            }
            else{
                b[i].setText("Slot - " + (i+1) +"\nYour Score : " + a[i].getScore());
            }
        }
        slot1.setOnAction(e ->{
            if(b[0] == null)
            PlayGame.SlotLoad(-1);           
            else
                PlayGame.SlotLoad(0);
            try {
                loadSlot();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        slot2.setOnAction(e->{
            if(b[1] == null)
            PlayGame.SlotLoad(-1);
            else
                PlayGame.SlotLoad(1);
            try {
                loadSlot();
            } catch (IOException ioException) {
               ioException.printStackTrace();
            }
        });
        slot3.setOnAction(e->{
            if(b[2] == null)
            PlayGame.SlotLoad(-1);
            else
                PlayGame.SlotLoad(2);
            try {
                loadSlot();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        back.setOnAction(e -> {
            try {
                loadHome();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }
    public void loadSlot() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PlayGame.fxml"));
        Scene PlayScene = new Scene(loader.load());
        Stage curStage = (Stage) LoadScene.getScene().getWindow();
        PlayGame py = loader.getController();
        py.initKeyActions(PlayScene, py);
        curStage.setScene(PlayScene);
        curStage.show();
    }
    public void loadHome() throws IOException {
        Parent settingView = (AnchorPane) FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
        Scene settingScene = new Scene(settingView);
        Stage curStage = (Stage) LoadScene.getScene().getWindow();
        curStage.setScene(settingScene);
        curStage.show();
    }
}

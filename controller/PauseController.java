package controller;

import Main.Data;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PauseController implements Initializable, Serializable {
    private static PlayGame py;
    private boolean saved = false;
    private boolean alert = false; // if true then proceed else cancel..
    private Timer t1;
    @FXML
    private Label saveStatus;
    @FXML
    private AnchorPane PauseScene;
    @FXML
    private Button resume;
    @FXML
    private Button restart;
    @FXML
    private Button save;
    @FXML
    private Button quit;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.t1 = py.getT1();

        resume.setOnAction(e -> {
            py.getPane().setEffect(null);
            py.getT1().start();
            PauseScene.getScene().getWindow().hide();
            this.saveStatus.setText("");
        });

        restart.setOnAction(e -> {
            if(!saved)
            {
                this.alert();
                if(alert ){
                    Data.reset();
                    alert = false;
                    try {
                        loadPlayGame();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                this.saveStatus.setText("");
            }
            else{
                Data.reset();
                Stage pane = (Stage) PauseScene.getScene().getWindow();
                pane.close();
                try {
                    loadPlayGame();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            this.saveStatus.setText("");
        });

        save.setOnAction(e ->{
            try {
                t1.saveGame();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            saveStatus.setAlignment(Pos.BASELINE_CENTER);
            saveStatus.setText("Game has been Saved!");
            this.saved = true;
        });

        quit.setOnAction(e ->{
            if(!saved)
            {
                this.alert();
                if(alert){
                    Data.reset();
                    alert = false;
                    try {
                        this.loadHome();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                this.saveStatus.setText("");
            }
            else{
                Data.reset();
                Stage pane = (Stage) PauseScene.getScene().getWindow();
                pane.close();
                try {
                    this.loadHome();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });



    }
    public static void setPy(PlayGame py) {
        PauseController.py = py;
    }
    public void loadHome() throws IOException {
        Parent settingView = (AnchorPane) FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
        Scene settingScene = new Scene(settingView);
        Stage curStage = (Stage) py.getPane().getScene().getWindow();
        curStage.setScene(settingScene);
        curStage.show();
    }
    public void loadPlayGame() throws  IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PlayGame.fxml"));
        Scene PlayScene = new Scene(loader.load());
        Stage curStage = (Stage) py.getPane().getScene().getWindow();
        PlayGame py = loader.getController();
        py.initKeyActions(PlayScene, py);
        curStage.setScene(PlayScene);
        curStage.show();
    }
    public void alert(){
        Stage pane = (Stage) PauseScene.getScene().getWindow();
        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert al = new Alert(type, "");
        al.initModality(Modality.APPLICATION_MODAL);
        al.initOwner(pane);
        al.getDialogPane().setContentText("Are you sure ? Your score will be lost !!");
        al.getDialogPane().setHeaderText("!! ALERT !!");
        Optional<ButtonType> input = al.showAndWait();
        if(input.get() == ButtonType.OK){
            this.alert = true;
            al.close();
            pane.close();
        }
        else if(input.get() == ButtonType.CANCEL){
            this.alert = false;
            al.close();
        }
    }
}
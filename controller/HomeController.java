package controller;

import Main.startGame;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable
{
    private startGame st = new startGame();
    private  boolean isSetting = false;
    @FXML
    private Button play;
    @FXML
    private Button setting;
    @FXML
    private Button load;
    @FXML
    private Button exit;
    @FXML
    private AnchorPane home;


    public HomeController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startShow();
        load.setOnAction(e -> {
            try {
                loadLoadGame();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        setting.setOnAction(e -> {
            try {
                loadSetting();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        play.setOnAction(e -> {
            try {
                loadPlayGame();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    public void exit() {
        Stage pane = (Stage) home.getScene().getWindow();
        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert al = new Alert(type, "");
        al.initModality(Modality.APPLICATION_MODAL);
        al.initOwner(pane);
        al.getDialogPane().setContentText("Are you Sure you want to EXIT ?");
        al.getDialogPane().setHeaderText("EXIT");
        Optional<ButtonType> input = al.showAndWait();
        if(input.get() == ButtonType.OK){
            al.close();
            pane.close();
        }
        else if(input.get() == ButtonType.CANCEL){
            al.close();
        }
    }


    private  void startShow(){
        st.music();
        FadeTransition Setting = new FadeTransition();
        FadeTransition Load = new FadeTransition();
        FadeTransition Exit = new FadeTransition();
        TranslateTransition transSetting = new TranslateTransition();
        TranslateTransition transLoad = new TranslateTransition();
        TranslateTransition transExit = new TranslateTransition();
        Setting.setDuration(Duration.seconds(2));
        Load.setDuration(Duration.seconds(2));
        Exit.setDuration(Duration.seconds(2));
        transSetting.setDuration(Duration.seconds(2));
        transLoad.setDuration(Duration.seconds(2));
        transExit.setDuration(Duration.seconds(2));
        Setting.setNode(setting);
        Load.setNode(load);
        Exit.setNode(exit);
        transSetting.setNode(setting);
        transLoad.setNode(load);
        transExit.setNode(exit);
        Setting.setFromValue(0);
        Setting.setToValue(1);
        transSetting.setByY(-150);
        Load.setFromValue(0);
        Load.setToValue(1);
        transLoad.setByY(-125);
        Exit.setFromValue(0);
        Exit.setToValue(1);
        transExit.setByY(-150);
        Setting.play();
        transSetting.play();
        Load.play();
        transLoad.play();
        Exit.play();
        transExit.play();

    }

    public void loadSetting() throws IOException{
        Parent settingView = (AnchorPane) FXMLLoader.load(getClass().getResource("/FXML/Settings.fxml"));
        Scene settingScene = new Scene(settingView);
        Stage curStage = (Stage) home.getScene().getWindow();
        curStage.setScene(settingScene);
        curStage.show();
    }

    public void loadPlayGame() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PlayGame.fxml"));
        Scene PlayScene = new Scene(loader.load());
        Stage curStage = (Stage) home.getScene().getWindow();
        PlayGame py = loader.getController();
        py.initKeyActions(PlayScene, py);
        curStage.setScene(PlayScene);
        curStage.show();
    }
    public void loadLoadGame() throws IOException{
        Parent LoadView = (AnchorPane) FXMLLoader.load(getClass().getResource("/FXML/Load.fxml"));
        Scene LoadScene = new Scene(LoadView);
        Stage curStage = (Stage) home.getScene().getWindow();
        curStage.setScene(LoadScene);
        curStage.show();
    }

}


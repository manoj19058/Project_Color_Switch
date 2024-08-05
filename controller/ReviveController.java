package controller;

import Main.Data;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReviveController implements Initializable {
    private static PlayGame py;
    @FXML
    private Label heading;
    @FXML
    private Button revive;
    @FXML
    private Button cancel;
    @FXML
    private Pane pane;

    public static void setPy(PlayGame py) {
        ReviveController.py = py;
    }

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Stage st = (Stage) py.getPane().getScene().getWindow();
        heading.setAlignment(Pos.BASELINE_CENTER);
        heading.setText("Would you like to be Revived for 10 points ?");
        revive.setFocusTraversable(false);
        cancel.setFocusTraversable(false);
        cancel.setOnAction(e ->{
            heading.setText("! GAME OVER !");
            Data.over();
//            st.close();
            try {
                Stage st = (Stage) pane.getScene().getWindow();
                loadHome();
                st.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        revive.setOnAction(e -> {
            Data.setMoney(10);
            py.reviveCalled();
            try {
                Stage st = (Stage) pane.getScene().getWindow();
                st.close();
                loadPlayGame();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
//            st.close();
        });
    }
    public void loadHome() throws IOException {
        Parent settingView = (AnchorPane) FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
        Scene settingScene = new Scene(settingView);
        Stage curStage = (Stage) py.getPane().getScene().getWindow();
        curStage.setScene(settingScene);
        curStage.show();
    }
    public void loadPlayGame() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PlayGame.fxml"));
        Scene PlayScene = new Scene(loader.load());
        Stage curStage = (Stage) py.getPane().getScene().getWindow();
        PlayGame py = loader.getController();
        py.initKeyActions(PlayScene, py);
        curStage.setScene(PlayScene);
        curStage.show();
    }
}

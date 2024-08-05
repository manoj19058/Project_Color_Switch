package controller;

import Main.Data;
import Main.startGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SettingController implements Initializable {
    private startGame st = new startGame();
    private Data d = new Data();
    @FXML
    private Label highScore;
    @FXML
    private Label totalMoney;
    @FXML
    private RadioButton on;
    @FXML
    private RadioButton off;
    @FXML
    private Button back;
    @FXML
    private AnchorPane Pane;
    @FXML
    private RadioButton red;
    @FXML
    private RadioButton blue;
    @FXML
    private RadioButton purple;
    @FXML
    private RadioButton yellow;
    @FXML
    private Button eraseHighScore;

    public SettingController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.highScore.setText("High Score : " + d.getHighScore());
        this.totalMoney.setText("Total Money : " + Data.getMoney());
        this.totalMoney.setAlignment(Pos.BASELINE_RIGHT);
        if(st.getIsplaying()){
            on.setSelected(true);
            off.setSelected(false);
        }
        else if(!st.getIsplaying()){
            off.setSelected(true);
            on.setSelected(false);
        }
        back.setOnAction(e -> {
            try {
                loadHome();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        eraseHighScore.setOnAction(e -> {
            Alert.AlertType type = Alert.AlertType.CONFIRMATION;
            Alert al = new Alert(type, "");
            al.initModality(Modality.APPLICATION_MODAL);
            al.getDialogPane().setContentText("Are you Sure you want to ERASE HIGH SCORE ?");
            al.getDialogPane().setHeaderText("ERASE HIGH SCORE");
            Optional<ButtonType> input = al.showAndWait();
            if(input.get() == ButtonType.OK){
                d.setHighScore();
                al.close();
            }
            else if(input.get() == ButtonType.CANCEL){
                al.close();
            }
            this.highScore.setText("High Score : " + d.getHighScore());
        });
        switch (d.getInitialColor()){
            case 0:
                red.setSelected(true);
                blue.setSelected(false);
                purple.setSelected(false);
                yellow.setSelected(false);
                break;
            case 1:
                red.setSelected(false);
                blue.setSelected(false);
                purple.setSelected(false);
                yellow.setSelected(true);
                break;
            case 2:
                red.setSelected(false);
                blue.setSelected(false);
                purple.setSelected(true);
                yellow.setSelected(false);
                break;
            case 3:
                red.setSelected(false);
                blue.setSelected(true);
                purple.setSelected(false);
                yellow.setSelected(false);
                break;
        }
    }
    public void loadHome() throws IOException {
        Parent settingView = (AnchorPane) FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
        Scene settingScene = new Scene(settingView);
        Stage curStage = (Stage) Pane.getScene().getWindow();
        curStage.setScene(settingScene);
        curStage.show();
    }

    public void radioSelected(ActionEvent actionEvent) {
        if(off.isSelected()){
            st.setMusic(false);
            st.music();
        }
        if(on.isSelected()){
            st.setMusic(true);
            st.music();
        }
    }

    public void colorSelection(ActionEvent actionEvent) {
        if(red.isSelected()){
            d.setInitialColor(0);
        }
        if(blue.isSelected()){
            d.setInitialColor(3);
        }
        if(yellow.isSelected()){
            d.setInitialColor(1);
        }
        if(purple.isSelected()){
            d.setInitialColor(2);
        }
    }
}

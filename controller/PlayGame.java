package controller;

import Main.Data;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayGame implements Initializable, Serializable {
    private static PlayGame py;
    private static int slot = -1;
    private static HomeController hc;
    @FXML
    private Group lineObs;
    @FXML
    private Group circleObs;
    @FXML
    private Arc arc1;
    @FXML
    private Arc arc2;
    @FXML
    private Arc arc3;
    @FXML
    private Arc arc4;
    @FXML
    private Line line1;
    @FXML
    private Line line2;
    @FXML
    private Line line3;
    @FXML
    private Line line4;
    @FXML
    private Rectangle rec1;
    @FXML
    private Circle st1;
    @FXML
    private Circle st2;
    @FXML
    private Circle st3;
    @FXML
    private Label score;
    @FXML
    private Button pause, power;
    @FXML
    private Group squareObs;
    @FXML
    private Circle ball;
    @FXML
    private Pane playGame;
    public int counter = 0;

    private final Data d = new Data();
    private boolean alert = false;
//    private PauseController pc = new PauseController();
    private Timer t1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        obs2.setY(-8600);obs3.setY(-8600);obs4.setY(-8600); obs1.setY(-6);
        //creating obstacles
        Obstacles ob1 = new Obstacles();
        Obstacles ob2 = new Obstacles();
        Obstacles ob3 = new Obstacles();
        ob1.setArea(-221);  ob1.setPerimeter(230);  ob1.setShape(353);  ob1.setGp(circleObs);
        ob2.setArea(0);  ob2.setPerimeter(452);  ob2.setShape(577);  ob2.setGp(squareObs);
        ob3.setArea(-32);  ob3.setPerimeter(414);  ob3.setShape(590);  ob3.setGp(lineObs);
        Obstacles [] obs = {ob1,ob2,ob3};
        ob1.getGp().setLayoutY(-221);   ob2.getGp().setLayoutY(-8600);      ob3.getGp().setLayoutY(-8600);
        Arc [] arcs = {arc1,arc2,arc3,arc4};
        Line[] lines = {line1,line2,line3,line4};
        Circle [] stars = {st1,st2,st3};
//        Rectangle[] rect = {obs1, obs2, obs3, obs4};

        this.setupTimeline();
        t1 = new Timer();
        t1.setData(d);
        t1.setBall(this.ball);
        t1.setScore(this.score);
        t1.setStars(stars);
        t1.setArcs(arcs);
        t1.setLines(lines);
        t1.setRec(rec1);
//        t1.setRect(rect);
        t1.setObs(obs);
        t1.start();
        pause.setFocusTraversable(false);
        power.setFocusTraversable(false);
        score.setFocusTraversable(false);
        pause.setOnAction(e -> {
            try {
                loadPause(t1);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        power.setOnAction(e-> {
            if(Data.getMoney() >= 3 && !t1.isPowerUp()){
                Data.setMoney(3);
                t1.setPowerUp(true);
                t1.setInit((Color)this.ball.getFill());
            }
        });
    }

    public void reviveCalled() {
        t1.setRevive(true);
    }

    public void initKeyActions(Scene scene, PlayGame py){
        t1.setPy(py);
        ReviveController.setPy(py);
        PauseController.setPy(py);
        scene.setOnKeyPressed(keyAction ->{
            if(keyAction.getCode()== KeyCode.SPACE)
            {

                this.ball.setCenterY(this.ball.getCenterY()-20);
                this.circleObs.setLayoutY(this.circleObs.getLayoutY() + 10);
                this.squareObs.setLayoutY(this.squareObs.getLayoutY() + 10);
                this.lineObs.setLayoutY(this.lineObs.getLayoutY() + 10);
//                this.obs1.setY(this.obs1.getY()+10);
//                this.obs2.setY(this.obs2.getY()+10);
//                this.obs3.setY(this.obs3.getY()+10);
//                this.obs4.setY(this.obs4.getY()+10);
            }
        });
    }


    public void stopGame() throws IOException {
        if(Data.getMoney() > 10){
            //reviving amount 10
            this.alertOption();
            if(this.alert){
                this.alert = false;
                System.out.println("revived!");
            }
            return;
        }
        else{
            this.alert();
        }
        Data.over();
        Parent settingView = (AnchorPane) FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
        Scene settingScene = new Scene(settingView);
        Stage curStage = (Stage) playGame.getScene().getWindow();
        curStage.setScene(settingScene);
        curStage.show();
    }


    public static void SlotLoad(int sl){
        PlayGame.slot = sl;
    }
    public static int GetSlot(){
        return  PlayGame.slot;
    }
    private void loadPause(Timer t1) throws IOException {
        t1.stop();
        this.playGame.setEffect(new GaussianBlur());
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(playGame.getScene().getWindow());
        popupStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader pauseRoot = new FXMLLoader(getClass().getResource("/FXML/pause.fxml"));
        Scene pauseScene = new Scene(pauseRoot.load(), Color.TRANSPARENT);
        popupStage.setScene(pauseScene);
        popupStage.show();
    }
    public Pane getPane(){return this.playGame; }
    private void alert(){
        t1.stop();
        Stage pane = (Stage) playGame.getScene().getWindow();
        Alert.AlertType type = Alert.AlertType.INFORMATION;
        Alert al = new Alert(type, "");
        al.initModality(Modality.APPLICATION_MODAL);
        al.initOwner(pane);
        al.getDialogPane().setContentText("!! GAME OVER !!");
        al.getDialogPane().setHeaderText("Your Score : "+ d.getMyScore() + "\nHigh Score : " + d.getHighScore() + "\nTotal Money :" + Data.getMoney());
        al.show();
    }


    private void alertOption() throws IOException {
        t1.stop();
        this.playGame.setEffect(new GaussianBlur());
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(playGame.getScene().getWindow());
        popupStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader ReviveRoot = new FXMLLoader(getClass().getResource("/FXML/Revive.fxml"));
        Scene ReviveScene = new Scene(ReviveRoot.load(), Color.TRANSPARENT);
        popupStage.setScene(ReviveScene);
        popupStage.show();
    }
    public Timer getT1(){return  this.t1; }
    public static void setHc(HomeController hc){PlayGame.hc = hc; }
    public void setupTimeline(){
        //KeyFrame kf = new KeyFrame(Duration.seconds(0.1), new TimeHandler());
        double time = 0.5;
        if(d.getMyScore() * 0.01 < 0.1){
            time = (d.getMyScore() * 0.01);
        }
        KeyFrame kf = new KeyFrame(Duration.minutes(0.1-time), e -> {
            this.changeobscolor();
        });
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public  void changeobscolor() {
        Color t=(Color)this.rec1.getFill();
        int cout = 0;
        Paint[] p = {Color.RED, Color.BLUE, Color.PURPLE, Color.YELLOW};
       if(t != ball.getFill() && this.ball.getFill() != Color.WHITE){
           this.rec1.setFill(this.ball.getFill());
       }
       if(t == ball.getFill()){
           this.rec1.setFill(p[cout%4]);
           cout++;
       }
    }
}




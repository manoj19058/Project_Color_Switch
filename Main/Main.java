package Main;

import controller.HomeController;
import controller.PlayGame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public Main() throws IOException {}

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Home.fxml"));
        Scene home = new Scene(loader.load());
        stage.setTitle("Color_Switch");
        HomeController hc = loader.getController();
        PlayGame.setHc(hc);

        stage.setScene(home);
        stage.show();
    }

}

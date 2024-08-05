package Main;

import javafx.animation.Animation;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.nio.file.Paths;

public class startGame {
    private static final String s = "C:\\Users\\Manoj\\IdeaProjects\\colorSwitch\\src\\resources\\music.mp3";
    private static final Media musicPlayer = new Media(Paths.get(s).toUri().toString());
    private static final MediaPlayer m = new MediaPlayer(musicPlayer);
    private final double vol = m.getVolume();
    private static boolean isplaying = false;
    private Main main = new Main();
    private static boolean music = true;

    public static boolean getMusic() {
//        System.out.println("Using getMusic()");
//
//        //"isPlaying = "+m.isPlaying() +
//        System.out.println("isPlaying = "+startGame.isplaying + " startGame.music = " + startGame.music);
        return music;
    }

    public static void setMusic(boolean music) {
//        System.out.println("using setMusic()");
        startGame.music = music;
//        System.out.println("isPlaying = "+startGame.isplaying +" startGame.music = " + startGame.music);
    }

    public startGame() throws IOException {
    }

    public void home() throws IOException {
        //the name in the uml is playGame

    }
    public void setting(Data obj){

    }
    public void loadGame(Data obj){

    }
    public  void exit(){

    }
    public void music(){

//        System.out.println("using music()");

        if(!startGame.music){
            m.stop();
            isplaying = false;
        }
        else{
            if(!isplaying) {
                m.play();
                m.setCycleCount(Animation.INDEFINITE);
                isplaying = true;
            }
        }
//        System.out.println("isPlaying = "+startGame.isplaying +" startGame.music = " + startGame.music);
    }
    public boolean getIsplaying(){return startGame.isplaying; }

}

package controller;

import Main.Data;
import Main.LoadGame;
import Main.Loading;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.io.IOException;

public class Timer extends AnimationTimer {
    private boolean powerUp = false;
    private boolean[] checkStar = {true, true, true};
    private static int Lastcollision = 0;
    private PlayGame py;
    private Circle ball;
    private boolean revive = false;
    private int firstCounter = 0;
    private Obstacles[] obs;
    private Label score;
    private int collision = 0;
    private Color init = Color.RED;
    private Data d;
    private ImageView img;
    private int lastCounter = 0;
    private Arc[] arcs;
    private Line[] lines;
    private Circle[] stars;
    private Rectangle rec;
    private boolean checkB = true;
    private Loading loading = new Loading(py);

    private boolean[] collide = new boolean[9];
    //    private final int firstPos = -6;
//    private final int secondPos = 174;
//    private Rectangle[] rect;


    @Override
    public void handle(long l) {
        if (PlayGame.GetSlot() != -1) {
            LoadGame a = Data.getA()[PlayGame.GetSlot()];
            PlayGame.SlotLoad(-1);
            if (a != null) {
                Data.load(a);
                for (int i = 0; i < 3; i++) {
                    obs[i].getGp().setLayoutY(a.positions()[i]);
//                    rect[i].setY(a.positions()[i]);
                }
                this.ball.setCenterY(a.getBallPos());
                this.firstCounter = a.getFirstCounter();
                this.lastCounter = a.getLastCounter();
            }
        }
//        Data.setMoney(-11);
        obs[0].getGp().setRotate(obs[0].getGp().getRotate() + 0.5 + (d.getMyScore() * 0.01));
        obs[1].getGp().setRotate(obs[1].getGp().getRotate() + 0.5 + (d.getMyScore() * 0.01));
        switch (d.getInitialColor()) {
            case 0:
                this.ball.setFill(Color.RED);
                break;
            case 1:
                this.ball.setFill(Color.YELLOW);
                break;
            case 2:
                this.ball.setFill(Color.PURPLE);
                break;
            case 3:
                this.ball.setFill(Color.BLUE);
                break;
        }
        //add load parameters
        this.score.setText(d.getMyScore() + "");
        if (obs[firstCounter % 3].getGp().getLayoutY() >= (obs[firstCounter % 3].getPerimeter() - 60)) {
            checkStar[firstCounter % 3] = true;
            firstCounter++;
            stars[firstCounter % 3].setVisible(true);            //System.out.print(firstCounter  + " ");
            obs[firstCounter % 3].getGp().setLayoutY(obs[firstCounter % 3].getArea()); // getting this obstacle
            //rect[firstCounter%4].setY(firstPos);
//            System.out.println(obs[firstCounter%3].getGp().getLayoutY());
        }
        if (obs[lastCounter % 3].getGp().getLayoutY() >= (obs[lastCounter % 3].getShape() + 30)) {
            if(powerUp){
                this.powerUp = false;
                this.ball.setFill(this.init);
            }
            obs[lastCounter % 3].getGp().setLayoutY(-8600);
            //rect[lastCounter%4].setY(-8600); // removing this obstacle
            lastCounter++;
        }
        if(this.revive){
            this.revive = false;
            Data.load(Data.getLastCollision());
        }
        if(powerUp){
            this.ball.setFill(Color.WHITE);
        }
        //Ball Motion starts here
        double ball_y = this.ball.getCenterY();
        if (this.ball.getCenterY() >= -385.0 && this.ball.getCenterY() <= 246.0) {
            this.ball.setCenterY(this.ball.getCenterY() + 1 + (d.getMyScore() * 0.01));
        } else {
            Lastcollision = 0;
            this.collisionOccur();
            stop();
            try {
                this.py.stopGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Ball Motion ends here

        //collision detection starts here.
        for (int i = 0; i < 4; i++) {
            if (((Path) Shape.intersect(this.ball, arcs[i])).getElements().size() > 0) {
                if (this.ball.getFill() != Color.WHITE && !arcs[i].getStroke().equals(this.ball.getFill())) {
                    Lastcollision = 1;
                    this.collisionOccur();
                   try {
                       py.stopGame();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
//                    System.out.println("wrong collision");
                }

//                System.out.println("collision with  arc" + i);
            }
        }

        for (int i = 0; i < 4; i++) {
            if (((Path) Shape.intersect(this.ball, lines[i])).getElements().size() > 0) {
                if (this.ball.getFill() != Color.WHITE && !lines[i].getFill().equals(this.ball.getFill())) {
                    Lastcollision = 2;
                    this.collisionOccur();
                    try {
                        py.stopGame();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    System.out.println("wrong collision");
                }
//                System.out.println("collision with lines" + i);
            }
        }
        if (((Path) Shape.intersect(this.ball, this.rec)).getElements().size() > 0) {
            if (this.ball.getFill() != Color.WHITE && !rec.getFill().equals(this.ball.getFill())) {
//                System.out.println("Wrong Collision");
                Lastcollision = 3;
                this.collisionOccur();
                try {
                    py.stopGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

//        for(int i = 0; i < 3; i++){
        if (((Path) Shape.intersect(this.ball, stars[firstCounter % 3])).getElements().size() > 0 && checkStar[firstCounter % 3]) {
            d.setMyScore();
            d.setInitialColor((d.getInitialColor() + 1)%4);
            checkStar[firstCounter % 3] = false;
            stars[firstCounter % 3].setVisible(false);
        }
//        }
        //star collision


//      if(((Path) Shape.intersect(this.ball, rect[collision%4])).getElements().size() > 0){
//          d.setInitialColor((d.getInitialColor() + 1)%4);
//          d.setMyScore();
//          collision++;
//      }

//      if(this.img.getBoundsInParent().intersects(this.ball.getBoundsInParent())){
//          d.setMyScore();
//      }
        //collision detection ends here.


    }

    public void saveGame() throws IOException {

        String s = ("Data" + Integer.toString(py.counter));
        loading.savegame(s);
//        this.file = s;
//        Timer.sa = loading.sa;
        double a[] = {obs[0].getGp().getLayoutY(),obs[1].getGp().getLayoutY(),obs[2].getGp().getLayoutY()};
        LoadGame ll = new LoadGame(d.getMyScore(), a, this.ball.getCenterY(), this.firstCounter, this. lastCounter, d.getInitialColor());
        Data.add(ll);
    }

    public void setBall(Circle ball) {
        this.ball = ball;
    }

    public void setScore(Label score) {
        this.score = score;
    }

    //    public void setRect(Rectangle[] rect) {
//        this.rect = rect;
//    }
    public void setData(Data d) {
        this.d = d;
    }

    public void setPy(PlayGame py) {
        this.py = py;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public void setObs(Obstacles[] obs) {
        this.obs = obs;
    }

    public void setArcs(Arc[] arcs) {
        this.arcs = arcs;
    }

    public void setLines(Line[] lines) {
        this.lines = lines;
    }

    public void setStars(Circle[] stars) {
        this.stars = stars;
    }

    public void setRec(Rectangle rec) {
        this.rec = rec;
    }

    public static int getLastcollision() {
        return Lastcollision;
    }

    public void collisionOccur(){
        double a[] = {obs[0].getGp().getLayoutY(),obs[1].getGp().getLayoutY(),obs[2].getGp().getLayoutY()};
        LoadGame ll = new LoadGame(d.getMyScore(), a, this.ball.getCenterY(), this.firstCounter, this.lastCounter, d.getInitialColor());
        Data.setLastCollision(ll);
    }


    public void setPowerUp(boolean powerUp) {
        this.powerUp = powerUp;
    }

    public void setInit(Color init) {
        this.init = init;
    }

    public boolean isPowerUp() {
        return powerUp;
    }

    public void setRevive(boolean revive) {
        this.revive = revive;
    }
}

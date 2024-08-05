package Main;

import java.util.ArrayList;

public class Data {
    private static int myScore = 0;
    private static int highScore = 0;
    private static int money = 0;
    private static int initialColor = 0;
    private static LoadGame a [] = {null,null,null};
    private static int counter = 0;
    private static ArrayList<LoadGame> games = new ArrayList<>();
    private static LoadGame lastCollision = null;

    public static int getMoney() {
        return money;
    }
    public static void add(LoadGame ll ){
        Data.a[counter%3] = ll;
        counter++;
    }
    public static void setLastCollision(LoadGame ll){
        Data.lastCollision = ll;
    }
    public static LoadGame getLastCollision(){
        return Data.lastCollision;
    }
    public static LoadGame[] getA(){
        return Data.a;
    }
    public static void setMoney(int x) {
        if(Data.money >= x)
        Data.money-=x;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getMyScore() {
        return myScore;
    }

    public void setHighScore() {
        Data.highScore = 0;
    }

    public int getInitialColor() {
        return initialColor;
    }

    public void setInitialColor(int initialColor) {
        this.initialColor = initialColor;
    }

    public void setMyScore() {
        Data.myScore++;
        Data.money++;
        if(Data.highScore < Data.myScore) {
            Data.highScore = this.myScore;
        }

    }
    public static void reset() {
        Data.initialColor = 0;
        if (Data.highScore > Data.myScore) {
            Data.highScore -= Data.myScore;
        } else {
            Data.highScore = 0;
        }
        if (Data.money > Data.myScore) {
            Data.money -= Data.myScore;
        } else {
            Data.money = 0;
        }
        Data.myScore = 0;
    }
    public static void over(){
        Data.initialColor = 0;
        Data.myScore = 0;
    }
    public static void load(LoadGame l){
        if(l != null)
        {
            Data.myScore = l.getScore();
            Data.money += l.getScore();
            Data.initialColor = l.getInitialColor();
            if(Data.highScore < l.getScore()){
                Data.highScore = l.getScore();
            }
        }
    }

}

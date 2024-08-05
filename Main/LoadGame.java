package Main;

public class LoadGame {
    private int score = 0;
    private double ballPos = 0.0;
    private double a[] = {-221, -8600, -8600};
    private int lastCounter = -1;
    private int firstCounter = -1;
    private int initialColor = 0;
    public LoadGame(int score, double[] arr, double ballPos, int firPos, int lastPos, int initialColor){
        this.score = score;
        this.a = arr;
        this.ballPos = ballPos;
        this.firstCounter = firPos;
        this.lastCounter = lastPos;
        this.initialColor = initialColor;
    }

    public int getScore() {
        return score;
    }
    public double[] positions(){return this.a; }

    public double getBallPos() {
        return ballPos;
    }
    public int getLastCounter(){
        return this.lastCounter;
    }

    public int getFirstCounter() {
        return firstCounter;
    }

    public int getInitialColor() {
        return initialColor;
    }
}

package Main;

import controller.PlayGame;

import java.io.IOException;
import java.io.Serializable;

public class Loading implements Serializable {
    public PlayGame ps;
    public Saving sa;
    public Loading(PlayGame ps){
        this.ps = ps;
    }
    public void savegame(String s) throws IOException {
        sa = new Saving(this, s);
        sa.serialize();
    }
    public Loading loadgame() throws IOException, ClassNotFoundException {
        return sa.deserialize();
    }

}
package Main;

import java.io.*;

public class Saving implements Serializable{
    public Loading game;
    public String file;

    public Saving(Loading mygame, String s1) {
        game=mygame;
        file=s1;
    }

    public void serialize() throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream (new FileOutputStream(file) );
            out.writeObject(game);
        }
        finally {
            out.close();
        }

    }

    public Loading deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        Loading mygame = null;
        try {
            in = new ObjectInputStream ( new FileInputStream("out.txt"));
            mygame = (Loading) in.readObject();
            System.out.println("ENcountered");
        }
        catch (NullPointerException e){
            System.out.println("Not encounterd !!");
        }
        finally {
            in.close();
        }
        return mygame;
    }


}
package game;

import java.awt.*;

public class Utilz {

    public static boolean isIn(Rectangle box, Rectangle box2){
        boolean inside = false;
        if(box.intersects(box2)){
            inside =true;
        }
        return inside;
    }

}

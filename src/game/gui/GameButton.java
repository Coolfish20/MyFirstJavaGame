package game.gui;

import game.GameInit;

import java.awt.*;

public class GameButton {


    public int screenX;
    public int screenY;

    private int width;
    private int height;

    public Rectangle box;


    public GameButton(int w, int h, int screenX, int screenY){
        this.width=w;
        this.height=h;
        this.screenX=screenX;//Basicaly the bb's x coordinate
        this.screenY= screenY;//The same as above
    }

    //Bounding box
    public void updateRect(){
        Rectangle rect = new Rectangle(screenX,screenY, this.getWidth(),this.getHeight());
        box = rect;
    }


    public Integer getWidth(){
        return width;
    }
    public Integer getHeight(){
        return height;
    }


}

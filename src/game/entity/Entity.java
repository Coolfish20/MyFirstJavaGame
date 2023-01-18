package game.entity;

import game.Utilz;

import java.awt.*;

import static game.GameInit.walls;

public class Entity {

    public int entity_type;
    public int width;
    public int height;
    public int xMove;
    public int yMove;
    public int screenX;
    public int screenY;
    public int direction;

    public int health;

    public Rectangle box;

    public Entity(int width, int height,int entity_type){
        this.entity_type=entity_type;
        this.width = width;
        this.height = height;
        this.xMove=0;
        this.yMove=0;
        this.direction=0;
    }

    public void updatePos( int key){
            int x2 = 0;
            int y2 = 0;
            boolean canMove = true;
        Rectangle toCollide = new Rectangle(screenX,screenY, width,height);

        if(key == 0) {
            x2 = screenX + xMove;
            toCollide.x=x2;
        }
        if(key== 1) {
            x2 = screenX - xMove;
            toCollide.x= x2;
        }
        if(key==2) {
            y2 = screenY - yMove;
            toCollide.y= y2;
        }
        if(key == 3) {
            y2 = screenY + yMove;
            toCollide.y=y2;
        }
        for(Rectangle rect: walls){
            if(Utilz.isIn(toCollide, rect)){
                canMove =false;
                if(direction==0|| direction== 1) {
                    this.xMove = 0;
                }else {
                    this.yMove = 0;
                }
            }
        }

            if(canMove == true) {
                if (x2 != 0) {
                    screenX = x2;
                    xMove=0;
                }

                if (y2 != 0) {
                    screenY = y2;
                    yMove=0;
                }
            }
    }

    public void setPos(int x, int y){
        screenX=x;
        screenY=y;
    }

    public void setHitbox(){//Call after entity init
        Rectangle newRect = new Rectangle(screenX,screenY,width,height);
        box = newRect;
    }


    public void setDirection(int direction){//0=right, 1=left, 2=down
        this.direction=direction;
    }

    public void setHealth(int health){
        this.health=health;
    }

}

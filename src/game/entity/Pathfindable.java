package game.entity;

import java.awt.*;
import java.util.*;
import java.util.List;

import static game.GameInit.walls;
import static game.logic.gameloop.GameLoop.player_entity;

public class Pathfindable {

    public Entity e;

    public boolean moving;

   public Point destination;

    public Pathfindable(Entity e){
        this.e=e;
        this.moving=false;
    }

    public void move(){
        Point p= nextNode();
        e.screenX= p.x;
        e.screenY=p.y;
    }


    //Util methods
    public Point nextNode(){

        List<Point> points = new ArrayList<>();
        List<Point> toRemove = new ArrayList<>();

        Point x_plus = new Point((int) (e.screenX + 1), e.screenY);
        Point x_minus = new Point((int) (e.screenX - 1), e.screenY);

        Point y_plus = new Point(e.screenX, (int) (e.screenY + 1));
        Point y_minus = new Point(e.screenX, (int) (e.screenY - 1));

        Point x_plus_y_minus = new Point((int) (e.screenX + 1), e.screenY-1);
        Point y_plus_x_minus= new Point(e.screenX-1, e.screenY+1);

        Point x_plus_y_plus= new Point(e.screenX+1,e.screenY+1);
        Point x_minus_y_minus= new Point(e.screenX-1, e.screenY-1);


        points.add(x_plus);
        points.add(x_minus);
        points.add(y_plus);
        points.add(y_minus);
        points.add(x_plus_y_minus);
        points.add(y_plus_x_minus);
        points.add(x_plus_y_plus);
        points.add(x_minus_y_minus);

        for (Point p : points) {
            Rectangle r = new Rectangle(p.x, p.y, e.width, e.height);
            for (Rectangle rect : walls) {
                if (r.intersects(rect)) {
                    toRemove.add(p);
                }
            }
        }
        for(Point p: toRemove){
            points.remove(p);
        }

        if(points.size()>1){
            int i =0;
            for(Point p: points){
            if(i !=0){//is not the first element(first element is assumed to be the biggest)
            if(p.distance(destination.x, destination.y) < points.get(0).distance(destination.x,destination.y)){
                points.set(0, p);
            }else {
                continue;
            }
            }else {
                i++;
                continue;
            }
            i++;
            }
        }




        return points.get(0);//May return null
    }

    public void updateDest(Point p){
        this.destination=p;
    }

}

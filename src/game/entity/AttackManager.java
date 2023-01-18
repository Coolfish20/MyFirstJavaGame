package game.entity;


import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import static game.GameInit.walls;

public class AttackManager {

   public Entity e;

   public Entity target;

   public Entity bullet;

   public ArrayList<Point> points;

   public int point_index;

   public boolean hasTravellingBullet;


    public AttackManager(Entity e){
        this.e=e;
        this.point_index=0;
        points=new ArrayList<>();
        this.hasTravellingBullet=false;
    }


    public void setTarget(Entity target){
        this.target=target;
    }


    public void prepareAttack(){
            createBullet();
            bullet.setPos(e.screenX,e.screenY);
    }


    //***Bullet methods
    public void createBullet(){
        this.bullet=new Entity(10,10,2);//Creating bullet
        Line2D vector= new Line2D.Float(e.screenX, e.screenY, target.screenX,target.screenY);

        boolean x2Bigger;
        boolean y2Bigger;
        if(vector.getX1() > vector.getX2() || vector.getX1()==vector.getX2()){
            //difX= (int) (vector.getX1()- vector.getX2());
            x2Bigger=false;
        }else {
            //difX=(int)(vector.getX2()- vector.getX1());
            x2Bigger=true;
        }
        if(vector.getY1() > vector.getY2() || vector.getY1()==vector.getY2()){
            //difY= (int) (vector.getY1()- vector.getY2());
            y2Bigger=false;
        }else {
            //difY=(int)(vector.getY2()- vector.getY1());
            y2Bigger=true;
        }

        List<Integer> pointsX= new ArrayList<>();
        List<Integer> pointsY = new ArrayList<>();

        if(x2Bigger){
            for(int i = (int) vector.getX1(); i<=vector.getX2();i++) {
                pointsX.add(i);
            }
        }else {
            for(int i = (int) vector.getX1(); i>= vector.getX2(); i--){
                pointsX.add(i);
            }
        }

        if(y2Bigger){
            for(int i = (int) vector.getY1(); i<=vector.getY2();i++) {
                pointsY.add(i);
            }
        }else {
            for (int i = (int) vector.getY1(); i >= vector.getY2(); i--) {
                pointsY.add(i);
            }
        }





        int lastY = pointsY.get(pointsY.size()-1);
        int lastX = pointsX.get(pointsX.size()-1);
        int lastIndexX = pointsX.size()-1;
        int lastIndexY = pointsY.size()-1;
        for(int i = 0; i<=lastIndexX; i++){//sizeX()== sizeY()
            if(i> lastIndexY){
                points.add(new Point(pointsX.get(i), lastY));
            }else {
                if(i< lastIndexY && i != lastIndexX){
                points.add(new Point(pointsX.get(i),pointsY.get(i)));
                }else {
                    if(i == lastIndexX && i<lastIndexY){
                        for(int i2 = i; i2<=pointsY.size()-pointsX.size()-1;i2++){
                            points.add(new Point(lastX, pointsY.get(i)));
                        }
                    }
                }
            }
        }

    }


    public void clearBullet(){
        bullet=null;
        point_index=0;
        points.clear();
        hasTravellingBullet=false;
    }

    public boolean hasCollided(){
        for(Rectangle rect : walls){
            if(this.bullet.box.intersects(rect)){
                return true;
            }
        }
        if(bullet.box.intersects(target.box)){
            target.setHealth(target.health-1);
            return true;
        }
        return false;
    }

    public void moveBullet(){
        if(point_index<=points.size()-1) {
            bullet.setPos(points.get(point_index).x, points.get(point_index).y);
            point_index++;
        }else {
            clearBullet();
        }
    }
    //*****

    public boolean isPossible(){
        boolean possible = true;
        Line2D attackVector = new Line2D.Float(e.screenX, e.screenY, target.screenX,target.screenY);
        for(Rectangle wall : walls){
        if(attackVector.intersects(wall)){
            possible=false;
            break;
        }
        }
        if(this.hasTravellingBullet == true){
            possible=false;
        }
        return possible;
    }

}

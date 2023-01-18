package game.logic.gameloop;

import game.entity.AttackManager;
import game.entity.Entity;
import game.entity.Pathfindable;
import game.entity.PlayerAttackController;
import game.gui.GameButton;
import game.render.Draw;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static game.GameInit.*;
import static game.render.Draw.*;

public class GameLoop {

    public static GameButton startButton;

    public static GameButton base_level;

    public static GameButton level_2;

    public static Entity player_entity;

    public static Entity bot;

    public static Pathfindable nav;

    public static AttackManager manager;


    public static void startScreen(Graphics g, BufferedImage b){
        startButton = new GameButton(280, 250, 96, 200); //START gameButton
            Point mouse = gameScreen.getMousePosition();
            startButton.updateRect();
            g.drawRect(startButton.box.x, startButton.box.y, startButton.getWidth(),startButton.getHeight());
            g.drawImage(b.getSubimage(96, 274, 1502, 516), 0, 0, null);
        if(mouse != null) {
            Rectangle mouse_hitbox= new Rectangle((int) mouse.getX(), (int) mouse.getY(), 1, 1);

            if (startButton.box.intersects(mouse_hitbox)) {
                g.drawImage(menu_button_imgs.get(1), 400, 100, null);
            }
            if (!startButton.box.intersects(mouse.getX(), mouse.getY(), 1, 1)) {
                g.drawImage(menu_button_imgs.get(0), 400, 100, null);
            }

        } else {
            g.drawImage(menu_button_imgs.get(0), 400, 100, null);
        }
        gameScreen.repaint();

    }

    public static void renderSelectionScreen(Graphics g){
        base_level= new GameButton(416, 72, 96, 100);
        level_2=new GameButton(416, 72, 96,30);
        Point mouse = gameScreen.getMousePosition();
        base_level.updateRect();
        level_2.updateRect();
        g.drawImage(b.getSubimage(96, 274, 1502, 516), 0, 0, null);
        g.drawImage(misc.getSubimage(464,64, 500,70), 100, 20, null);
        if(mouse != null) {
            if (base_level.box.intersects(mouse.getX(), mouse.getY(), 1, 1)) {
                g.drawImage(misc.getSubimage(442, 194, 420, 76), 96, 100, null);
            } else {
                g.drawImage(misc.getSubimage(18, 198, 422, 72), 96, 100, null);
            }
            if (level_2.box.intersects(mouse.getX(), mouse.getY(), 1, 1)) {
                g.drawImage(misc.getSubimage(442, 288, 422, 78), 96, 30, null);
            } else {
                g.drawImage(misc.getSubimage(14, 294, 416, 66), 96, 30, null);
            }
        }else {
            g.drawImage(misc.getSubimage(18, 198, 422, 72), 96, 100, null);
            g.drawImage(misc.getSubimage(14, 294, 416, 66), 96, 30, null);
        }
        gameScreen.repaint();
    }


    //Actual game rendering
    public static void renderGameScreen(Graphics g){//+ update objects
    List<Rectangle> updated = new ArrayList<>();
    for(Rectangle rect : walls){
        updated.add(rect);
    }
        updateWalls(updated);
        if(level== 1) {
            g.drawImage(level_1, 0, 0, null);
        }else {
            g.drawImage(Draw.level_2, 0,0,null);
        }
        //Drawing the player
        g.drawImage(playerAnim.get(0), bot.screenX, bot.screenY, null);
        //TODO: proper rotation updating for the bot
        g.drawImage(playerAnim.get(player_entity.direction), player_entity.screenX,player_entity.screenY,null);
        player_entity.setHitbox();
        bot.setHitbox();
        nav.updateDest(new Point(player_entity.screenX,player_entity.screenY));

        BufferedImage b3 = misc.getSubimage(30,40,54,108);
        BufferedImage b2 = misc.getSubimage(112,60,56,80);
        BufferedImage b1= misc.getSubimage(182, 66, 46,72);

        if (player_entity.health == 2) {
            g.drawImage(b2, player_entity.screenX+8,player_entity.screenY-8,null);
        }
        if(player_entity.health ==1){
            g.drawImage(b1, player_entity.screenX+8,player_entity.screenY-8,null);

        }
        if (bot.health == 3) {
            g.drawImage(b3, bot.screenX+8,bot.screenY-6,null);

        }
        if(bot.health ==2){
            g.drawImage(b2, bot.screenX+8,bot.screenY-8,null);
        }
        if(bot.health ==1){
            g.drawImage(b1, player_entity.screenX+8,player_entity.screenY-8,null);
        }



        if(player_entity.health==0){
        gameRunning=false;
        showSelectionScreen=true;
        player_entity=null;
        bot=null;
        //TODO: add sounds
        }else {
            if(bot.health==0){
                gameRunning=false;
                showSelectionScreen=true;
                player_entity=null;
                bot=null;
            }
        }



        if(update == 40) {
            nav.move();
            update=0;
        }
        if(manager.isPossible()){
            manager.hasTravellingBullet=true;
            manager.prepareAttack();
        }
        if(updateBullets==10){
            if(manager.bullet != null){
                manager.bullet.setHitbox();
                if(manager.hasCollided() == false) {
                    manager.moveBullet();
                }else {
                    manager.clearBullet();
                    manager.hasTravellingBullet = false;
                }
            }
            if(PlayerAttackController.mng.bullet != null){
            PlayerAttackController.mng.bullet.setHitbox();
            if(PlayerAttackController.mng.hasCollided() ==false) {
                PlayerAttackController.mng.moveBullet();
            }else {
                PlayerAttackController.mng.clearBullet();
                PlayerAttackController.mng.hasTravellingBullet=false;
            }
            }
            updateBullets=0;
        }
        if(manager.bullet != null) {
            g.drawImage(misc.getSubimage(266, 48, 50,40), manager.bullet.screenX, manager.bullet.screenY, null);
        }
        if(PlayerAttackController.mng.bullet != null){
            AttackManager m = PlayerAttackController.mng;
            g.drawImage(misc.getSubimage(266, 48, 50, 40), m.bullet.screenX, m.bullet.screenY, null);
        }
        gameScreen.repaint();
        update++;
        updateBullets++;
    }


    public static void updateWalls(List<Rectangle> map){//avoiding concurrent-exception...
        for(Rectangle rect : map){
            updateWallBounds(rect);
        }
    }


    //Setup/update methods
    public static void updateWallBounds(Rectangle oldRect){
        Rectangle newRect = new Rectangle(oldRect.x,oldRect.y,oldRect.width,oldRect.height);
        walls.remove(oldRect);
        walls.add(newRect);
    }

    public static List<Rectangle> generateWallsAndBounds(){

        List<Rectangle> var = new ArrayList<>();
        if(level==1) {
            Rectangle seg_1 = new Rectangle(132, 80, 38, 228);
            Rectangle seg_2 = new Rectangle(170, 82, 250, 42);
            Rectangle seg_3 = new Rectangle(166, 284, 306, 22);
            Rectangle seg_4 = new Rectangle(414, 306, 58, 82);
            Rectangle seg_5 = new Rectangle(412, 298, 62, 96);
            Rectangle seg_6 = new Rectangle(708, 274, 156, 38);
            Rectangle seg_7 = new Rectangle(756, 128, 64, 148);
            Rectangle seg_8 = new Rectangle(582, 128, 172, 32);
            Rectangle seg_9 = new Rectangle(760, 382, 50, 134);

            var.add(seg_1);
            var.add(seg_2);
            var.add(seg_3);
            var.add(seg_4);
            var.add(seg_5);
            var.add(seg_6);
            var.add(seg_7);
            var.add(seg_8);
            var.add(seg_9);
        }else {
            Rectangle seg_10= new Rectangle(218,292, 368, 60);
            Rectangle seg_11= new Rectangle(390, 150, 54, 144);
            Rectangle seg_12= new Rectangle(768, 2, 68, 272);

            var.add(seg_10);
            var.add(seg_11);
            var.add(seg_12);

        }

            return var;
        }

    }




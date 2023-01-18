package game.render;

import game.entity.AttackManager;
import game.entity.Entity;
import game.entity.Pathfindable;
import game.entity.PlayerAttackController;
import game.logic.gameloop.GameLoop;
import game.logic.input.KeyDetector;
import game.logic.input.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static game.GameInit.*;
import static game.logic.gameloop.GameLoop.*;

public class Draw extends JPanel {

    public static BufferedImage player;
    public static BufferedImage b;
    public static BufferedImage level_1;
    public static BufferedImage level_2;
    public static BufferedImage misc;

    public static List<BufferedImage> menu_button_imgs;
    public static List<BufferedImage> playerAnim;

    public static int update;
    public static int updateBullets;


    public Draw(){
    addKeyListener(new KeyDetector());
    addMouseListener( new MouseListener());
    getPlayerModel();
    getMenuAtlas();
    getLevel1();
    getLevel2();
    getMiscAtlas();
    update=0;
    updateBullets=0;
    }

    @Override
    public void paintComponent(Graphics g){
    super.paintComponent(g);
    if(setupImgs == true){
        //Menu button(s)
        menu_button_imgs = new ArrayList<>();
        BufferedImage button_up = b.getSubimage(88,24,250 ,210);//button up state(not hovered)
        BufferedImage button_down = b.getSubimage(346, 24, 300, 235);//button down state(hovered)

        menu_button_imgs.add(button_up);
        menu_button_imgs.add(button_down);

        playerAnim=new ArrayList<>();
        BufferedImage right = player.getSubimage(48,39, 56,61);
        BufferedImage left = player.getSubimage(175,35, 56,61);
        BufferedImage down = player.getSubimage(49,117, 56,61);

        playerAnim.add(right);
        playerAnim.add(left);
        playerAnim.add(down);

        setupImgs= false;
    }
    if(shouldCreateMenu==true) {
        GameLoop.startScreen(g, b);
    }
    if(showSelectionScreen==true){
        GameLoop.renderSelectionScreen(g);
    }
            if (gameStarted == true) {
                super.repaint();
                player_entity = new Entity(50, 60, 0);//0 for player; 1 for the botEnemy; 2 for bullet
                bot = new Entity(50, 60, 1);
                player_entity.setPos(40, 50);//TODO: set proper coords
                player_entity.setHitbox();
                bot.setPos(700, 50);
                bot.setHitbox();
                nav = new Pathfindable(bot);
                walls = GameLoop.generateWallsAndBounds();
                manager = new AttackManager(bot);
                manager.setTarget(player_entity);
                player_entity.setHealth(2);
                bot.setHealth(3);
                PlayerAttackController.initManager();
                gameStarted=false;
            }
            if(gameRunning==true) {
                GameLoop.renderGameScreen(g);
            }

    }






    public BufferedImage getPlayerModel() {
        File sprites = new File("./src/res/player_sprites.png");
        try {
            player = ImageIO.read(sprites);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return player;
    }

    public BufferedImage getMenuAtlas(){
    File b2 = new File("./src/res/menu_atlas.png");
        try {
            b = ImageIO.read(b2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    public BufferedImage getLevel1(){
        File b2 = new File("./src/res/level_1.png");
        try {
            level_1 = ImageIO.read(b2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return level_1;
    }

    public BufferedImage getLevel2(){
        File b2 = new File("./src/res/level_2.png");

        try {
            level_2= ImageIO.read(b2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return level_2;
    }

    public BufferedImage getMiscAtlas(){
        File b2 = new File("./src/res/misc_assets.png");
        try {
            misc= ImageIO.read(b2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return misc;
    }
}

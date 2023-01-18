package game;

import game.entity.PlayerAttackController;
import game.gui.GameButton;
import game.render.Draw;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameInit {

    public static GameScreen gameScreen;
    public static Draw render;

    public static boolean gameStarted;
    public static boolean gameRunning;
    public static boolean showSelectionScreen;
    public static boolean shouldCreateMenu;

    public static List<Rectangle> walls;


    public static boolean setupImgs;
    public static int level;


    public void initScreen() {
        setupImgs=true;
        level=0;
        shouldCreateMenu = true;
        showSelectionScreen=false;
        gameRunning=false;
        walls=new ArrayList<>();
        render = new Draw();
        gameScreen = new GameScreen(render);
        render.requestFocus();
        gameStarted =false;
    }
}

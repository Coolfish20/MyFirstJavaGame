package game;

import game.render.Draw;

import javax.swing.*;

public class GameScreen extends JFrame {

    public GameScreen(Draw draw){
        this.setTitle("Java Game");
        this.setSize(1000,516);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(draw);
        this.setVisible(true);
    }

}

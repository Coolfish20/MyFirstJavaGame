package game.logic.input;

import game.Utilz;
import game.entity.PlayerAttackController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static game.GameInit.*;
import static game.logic.gameloop.GameLoop.player_entity;

public class KeyDetector implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    if(e.getKeyCode()== KeyEvent.VK_D ||
    e.getKeyCode() == KeyEvent.VK_A ||
    e.getKeyCode() == KeyEvent.VK_W ||
    e.getKeyCode() == KeyEvent.VK_S){
    if(gameRunning == true){//changed
        int s = -1;
            if(e.getKeyCode() ==KeyEvent.VK_D) {
                player_entity.xMove+=2;
                player_entity.setDirection(0);
                s = 0;
            }
            if(e.getKeyCode()== KeyEvent.VK_A) {
                player_entity.xMove+=2;
                player_entity.setDirection(1);
                s = 1;
            }
            if(e.getKeyCode()==KeyEvent.VK_W) {
                player_entity.yMove+=2;
                s = 2;
            }
            if(e.getKeyCode() == KeyEvent.VK_S) {
                player_entity.yMove+=2;
                player_entity.setDirection(2);
                s = 3;
            }
        player_entity.updatePos(s);
            System.out.println("Update Pos!");
    }

    }else {
       if(e.getKeyCode() == KeyEvent.VK_SPACE){
           PlayerAttackController.onAttackStart();
       }
    }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP ||
                e.getKeyCode() == KeyEvent.VK_DOWN ||
                e.getKeyCode() == KeyEvent.VK_RIGHT ||
                e.getKeyCode() == KeyEvent.VK_LEFT) {
        //TODO



        }
    }














}

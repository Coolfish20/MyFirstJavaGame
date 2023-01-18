package game.logic.input;


import game.Utilz;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

import static game.GameInit.*;
import static game.logic.gameloop.GameLoop.*;


public class MouseListener implements MouseInputListener {


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    Point p = e.getPoint();
    Rectangle mouse = new Rectangle((int) p.getX(), (int) p.getY(),1,1);
    if(gameRunning == false) {

        if(startButton != null) {
            if (Utilz.isIn(startButton.box, mouse)) {
                showSelectionScreen = true;
                shouldCreateMenu = false;
                startButton = null;
            }
        }

        if(base_level != null) {
            if(Utilz.isIn(base_level.box,mouse)){
                gameStarted=true;
                showSelectionScreen=false;
                base_level=null;
                gameRunning = true;
                level=1;
        }
        if(level_2 != null) {
            if (Utilz.isIn(level_2.box, mouse)) {
                gameStarted = true;
                showSelectionScreen = false;
                level_2 = null;
                gameRunning=true;
                level = 2;
            }
        }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
















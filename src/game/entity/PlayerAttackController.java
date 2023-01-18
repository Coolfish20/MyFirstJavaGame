package game.entity;

import static game.logic.gameloop.GameLoop.bot;
import static game.logic.gameloop.GameLoop.player_entity;

public class PlayerAttackController {

    public static AttackManager mng;

    public static void onAttackStart(){
        if(mng.bullet == null) {
            mng.prepareAttack();
        }
    }

    public static void initManager(){
        mng=new AttackManager(player_entity);
        mng.setTarget(bot);
    }
}

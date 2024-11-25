package puppy.code.blocks;

import puppy.code.objetos.Paddle;
import puppy.code.objetos.PingBall;
import puppy.code.principal.BlockBreakerGame;

import java.util.ArrayList;
import java.util.Random;

public class NormalBlock extends BlockDefinitive {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
	
	private BlockBreakerGame game;

    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public NormalBlock(int x, int y, int whidth, int height) {
        super(x, y, whidth, height, new Random().nextInt(4));
        game = BlockBreakerGame.getInstancia();	
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    @Override
    public void applyEfect(Paddle paddle, ArrayList<PingBall> balls) {
        if (isDestroyed()) {
        	game.setHighScore(game.getHighScore() + 1);
        }
    }

}
package puppy.code.blocks;

import puppy.code.objetos.Paddle;
import puppy.code.objetos.PingBall;

import java.util.ArrayList;
import java.util.Random;

public class NormalBlock extends BlockDefinitive {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */


    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public NormalBlock(int x, int y, int whidth, int height) {
        super(x, y, whidth, height, new Random().nextInt(4));
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    @Override
    public void applyEfect(Paddle paddle, ArrayList<PingBall> balls) {
        if (isDestroyed()) {
            //System.out.println("BLOQUE NORMAL");
        }
    }

}
package puppy.code;

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
    public void applyEfect(Paddle paddle, PingBall ball) {
        if (isDestroyed()) {
            System.out.println("BLOQUE NORMAL");
        }
    }

}


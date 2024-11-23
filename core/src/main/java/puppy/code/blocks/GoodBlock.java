package puppy.code.blocks;

import puppy.code.power.BallSizeIncrease;
import puppy.code.power.PaddleSizeIIncrease;
import puppy.code.power.PowerUp;
import puppy.code.power.SlowDownBall;
import puppy.code.objetos.Paddle;
import puppy.code.objetos.PingBall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GoodBlock extends BlockDefinitive {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    /*private boolean isMultiBalls = false;*/
	private List<PowerUp> powerUps;


    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public GoodBlock(int x, int y, int width, int height) {
        // Genera una resistencia aleatoria entre 2 y 5 para un bloque bueno
        super(x, y, width, height, new Random().nextInt(4));
        this.powerUps = new ArrayList<>();
        this.powerUps.add(new PaddleSizeIIncrease());
        this.powerUps.add(new BallSizeIncrease());
        this.powerUps.add(new SlowDownBall());
    }


    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */



    /* public void multiBalls() {*/


    @Override
    public void applyEfect(Paddle paddle, PingBall ball) {
            PowerUp chosenPowerUp = powerUps.get(new Random().nextInt(powerUps.size()));
            chosenPowerUp.apply(paddle, ball);
        }

}

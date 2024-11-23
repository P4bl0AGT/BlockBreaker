package puppy.code.blocks;

import puppy.code.power.BallSizeDecreases;
import puppy.code.power.FastDownBall;
import puppy.code.power.PaddleSizeDecreases;
import puppy.code.power.PowerUp;
import puppy.code.objetos.Paddle;
import puppy.code.objetos.PingBall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BadBlock extends BlockDefinitive {

	private List<PowerUp> powerUps;

    public BadBlock(int x, int y, int whidth, int height) {
        super(x, y, whidth, height, new Random().nextInt(4));
        this.powerUps = new ArrayList<>();
        this.powerUps.add(new PaddleSizeDecreases());
        this.powerUps.add(new BallSizeDecreases());
        this.powerUps.add(new FastDownBall());
    }


   public void applyEfect(Paddle paddle, PingBall ball) {
       PowerUp chosenPowerUp = powerUps.get(new Random().nextInt(powerUps.size()));
       chosenPowerUp.apply(paddle, ball);
   }
}








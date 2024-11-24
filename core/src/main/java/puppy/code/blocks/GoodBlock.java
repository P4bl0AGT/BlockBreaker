package puppy.code.blocks;

import puppy.code.power.BallSizeIncrease;
import puppy.code.power.SlowDownBall;
import puppy.code.power.PaddleSizeIIncrease;
import puppy.code.power.BallStrategy;
import puppy.code.power.MultiBall;
import puppy.code.power.PaddleStrategy;
import puppy.code.objetos.Paddle;
import puppy.code.objetos.PingBall;

import java.util.ArrayList;
import java.util.Random;

public class GoodBlock extends BlockDefinitive {

    private ArrayList<BallStrategy> powerUpsBalls;
    private ArrayList<PaddleStrategy> powerUpsPaddle;

    public GoodBlock(int x, int y, int width, int height) {
        super(x, y, width, height, new Random().nextInt(4));

        
        this.powerUpsBalls = new ArrayList<>();
        this.powerUpsBalls.add(new BallSizeIncrease());
        this.powerUpsBalls.add(new SlowDownBall());  
        this.powerUpsBalls.add(new MultiBall());
  

        this.powerUpsPaddle = new ArrayList<>();
        this.powerUpsPaddle.add(new PaddleSizeIIncrease());
    }

    public void applyEfect(Paddle paddle, ArrayList<PingBall> balls) {
        if (new Random().nextBoolean()) {
            
            PaddleStrategy chosenPaddleStrategy = powerUpsPaddle.get(new Random().nextInt(powerUpsPaddle.size()));
            chosenPaddleStrategy.apply(paddle);  // Aplicar el efecto al paddle
            paddle.setStrategy(chosenPaddleStrategy);
        } else {
            BallStrategy chosenBallStrategy = powerUpsBalls.get(new Random().nextInt(powerUpsBalls.size()));
            chosenBallStrategy.apply(balls);  // Aplicar el efecto a cada bola
            for(PingBall ball:balls)
            	ball.setStrategy(chosenBallStrategy);
            
        }
    }
}
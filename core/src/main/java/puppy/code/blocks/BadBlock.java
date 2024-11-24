package puppy.code.blocks;

import puppy.code.power.BallSizeDecreases;
import puppy.code.power.BallStrategy;
import puppy.code.power.FastDownBall;
import puppy.code.power.PaddleSizeDecreases;
import puppy.code.power.BallStrategy;
import puppy.code.power.PaddleStrategy;
import puppy.code.objetos.Paddle;
import puppy.code.objetos.PingBall;

import java.util.ArrayList;
import java.util.Random;

public class BadBlock extends BlockDefinitive {

    private ArrayList<BallStrategy> powerUpsBalls;
    private ArrayList<PaddleStrategy> powerUpsPaddle;

    public BadBlock(int x, int y, int width, int height) {
        super(x, y, width, height, new Random().nextInt(4));

        // Inicializar las listas de estrategias
        this.powerUpsBalls = new ArrayList<>();
        this.powerUpsBalls.add(new BallSizeDecreases());
        this.powerUpsBalls.add(new FastDownBall());

        this.powerUpsPaddle = new ArrayList<>();
        this.powerUpsPaddle.add(new PaddleSizeDecreases());
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
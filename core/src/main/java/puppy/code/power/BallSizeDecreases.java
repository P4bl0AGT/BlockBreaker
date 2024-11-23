package puppy.code.power;

import puppy.code.objetos.Paddle;
import puppy.code.objetos.PingBall;

public class BallSizeDecreases implements PowerUp {

	public void apply(Paddle paddle, PingBall ball) {
		if (!ball.getHasEffect()) {
			ball.setSize(ball.getSize() / 2);
	        ball.setHasEffect(true);
	        ball.setEffectSizeDecreases(true);
		}
	}

	public void remove(Paddle paddle, PingBall ball) {
		ball.setSize(15);
		ball.setHasEffect(false);
		ball.setEffectSizeDecreases(false);

	}
}

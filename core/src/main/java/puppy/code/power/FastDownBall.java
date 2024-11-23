package puppy.code.power;

import puppy.code.objetos.Paddle;
import puppy.code.objetos.PingBall;

public class FastDownBall implements PowerUp {

	public void apply(Paddle paddle , PingBall ball) {
		if (!ball.getHasEffect()) {
		    ball.setxSpeed(ball.getxSpeed() * 2);
		    ball.setySpeed(ball.getySpeed() * 2);
		    ball.setHasEffect(true);
		    ball.setEffectFastDownBall(true);
		}
	}


	public void remove(Paddle paddle , PingBall ball) {
		ball.setxSpeed(ball.getxSpeed() / 2);
		ball.setySpeed(ball.getySpeed() / 2);
		ball.setHasEffect(false);
		ball.setEffectFastDownBall(false);
	}

}

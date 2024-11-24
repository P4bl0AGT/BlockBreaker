package puppy.code.power;

import java.util.ArrayList;

import puppy.code.objetos.PingBall;

public class FastDownBall implements BallStrategy {

	public void apply(ArrayList<PingBall> balls) {
		for(PingBall ball:balls) {
			if (!ball.getHasEffect()) {
			    ball.setxSpeed(ball.getxSpeed() * 2);
			    ball.setySpeed(ball.getySpeed() * 2);
			    ball.setHasEffect(true);
			    ball.setEffectFastDownBall(true);
			}
		}
	}


	public void remove(ArrayList<PingBall> balls) {
    	for(PingBall ball:balls) {
	    	ball.setxSpeed((ball.getxSpeed() / 2));
			ball.setySpeed((ball.getySpeed() / 2));
			ball.setHasEffect(false);
			ball.setEffectFastDownBall(false);
    }
	}

}

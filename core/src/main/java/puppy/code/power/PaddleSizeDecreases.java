package puppy.code.power;

import java.util.ArrayList;

import puppy.code.objetos.Paddle;
import puppy.code.objetos.PingBall;

public class PaddleSizeDecreases implements PaddleStrategy {

	public void apply(Paddle paddle) {
		if (!paddle.getHasEffect()) {
			paddle.setWidth(paddle.getWidth() / 2);
	        paddle.setHasEffect(true);
	        paddle.setEffectSizeDecreases(true);
		}
	}

	public void remove(Paddle paddle) {
		paddle.setWidth(paddle.getWidth() * 2);
		paddle.setHasEffect(false);
		paddle.setEffectSizeDecreases(false);
		
	}

}

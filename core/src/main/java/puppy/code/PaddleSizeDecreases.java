package puppy.code;

public class PaddleSizeDecreases implements PowerUp{

	public void apply(Paddle paddle, PingBall ball) {
		if (!paddle.getHasEffect()) {
			paddle.setWidth(paddle.getWidth() / 2);
	        paddle.setHasEffect(true);
	        paddle.setEffectSizeDecreases(true);
		}
	}
	
	public void remove(Paddle paddle, PingBall ball) {
		paddle.setWidth(paddle.getWidth() * 2);
		paddle.setHasEffect(false);
		paddle.setEffectSizeDecreases(false);
	}
	
}

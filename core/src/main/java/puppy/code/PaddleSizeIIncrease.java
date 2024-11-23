package puppy.code;


public class PaddleSizeIIncrease implements PowerUp{

	
	public void apply(Paddle paddle, PingBall ball) {
		if(!paddle.getHasEffect()) {
	        paddle.setWidth(paddle.getWidth() * 2);
	        paddle.setHasEffect(true);
	        paddle.setEffectSizeIncrease(true);
		}
	}
	
	public void remove(Paddle paddle, PingBall ball) {
		paddle.setWidth(paddle.getWidth() / 2);
		paddle.setHasEffect(false);
		paddle.setEffectSizeIncrease(false);

	}

}

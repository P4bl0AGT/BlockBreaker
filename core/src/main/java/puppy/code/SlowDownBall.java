package puppy.code;

public class SlowDownBall implements PowerUp{

	
	 public void apply(Paddle paddle, PingBall ball) {
		 if(!ball.getHasEffect()) {
		 	ball.setxSpeed(ball.getxSpeed() / 2);
	        ball.setySpeed(ball.getySpeed() / 2);
	        ball.setHasEffect(true);
	        ball.setEffectSlowDownBall(true);
		 }
	 }

	    public void remove(Paddle paddle, PingBall ball) {
	    	ball.setxSpeed(ball.getxSpeed() * 2);
			ball.setySpeed(ball.getySpeed() * 2);
			ball.setHasEffect(false);
			ball.setEffectSlowDownBall(false);
	    }

}

package puppy.code.power;

import java.util.ArrayList;

import puppy.code.objetos.PingBall;

public class BallSizeIncrease implements BallStrategy {

	public void apply(ArrayList<PingBall> balls) {
		for (PingBall ball : balls) {
			if(!ball.getHasEffect()) {
				ball.setSize(ball.getSize() * 2);
		        ball.setHasEffect(true);
		        ball.setEffectSizeIncrease(true);
			}
		}
    }


    public void remove(ArrayList<PingBall> balls) {
	    for (PingBall ball : balls) {
	        ball.setSize(ball.getSize() / 2);             
	    }
	    for(PingBall ball:balls) {
		        ball.setHasEffect(false);    
		        ball.setEffectSizeIncrease(false); 
		    }
    	}
    }


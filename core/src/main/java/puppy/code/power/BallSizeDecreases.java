package puppy.code.power;

import java.util.ArrayList;


import puppy.code.objetos.PingBall;

public class BallSizeDecreases implements BallStrategy {

	public void apply(ArrayList<PingBall> balls) {
		
        for (PingBall ball : balls) {
            if (!ball.getHasEffect()) {
                ball.setSize(ball.getSize() / 2);   
                ball.setHasEffect(true);           
                ball.setEffectSizeDecreases(true);  
            }
        }
    }

    @Override
    public void remove(ArrayList<PingBall> balls) {
        for (PingBall ball : balls) {
	            ball.setSize(15);             
        }
        for(PingBall ball:balls) {
	        ball.setHasEffect(false);    
	        ball.setEffectSizeDecreases(false); 
        }
    }
}

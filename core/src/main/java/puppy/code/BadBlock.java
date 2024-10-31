package puppy.code;

import java.util.Random;

public class BadBlock extends BlockDefinitive{
	
	public BadBlock (int x, int y, int whidth, int height) {
		super(x, y , whidth, height, new Random().nextInt(4));
	}

	public void paddleSizeDecreases(Paddle paddle) {
    	
    	paddle.setWidth(paddle.getWidth() / 2);  
        paddle.setHasEffect(true);
        paddle.setEffectSizeDecreases(true);
	   
    }
     
    public void ballSizeDecreases(PingBall ball) {
    	
		ball.setSize(ball.getSize() / 2);
		ball.setHasEffect(true);
		ball.setEffectSizeDecreases(true);

	}
    
   public void fastDownBall(PingBall ball){
	    ball.setxSpeed(ball.getxSpeed() * 2);
	    ball.setySpeed(ball.getySpeed() * 2);
	    ball.setHasEffect(true);
	    ball.setEffectSlowDownBall(true);
	 
    }

	public void applyEfect(Paddle paddle, PingBall ball) {
		
		if(destroyed) {
			int eleccion = new Random().nextInt(4);
			
			switch(eleccion){
			
			case 0: {
				if(!paddle.getHasEffect()) {
					if(!paddle.getEffectSizeDecreases())
						paddleSizeDecreases(paddle);
					break;
				}
			}
			case 1:{
				if(!ball.getHasEffect()) {
					if(!ball.getEffectSizeIncrease())
						ballSizeDecreases(ball);
				}
				break;
			}
					
			case 2:{
				if(!ball.getHasEffect()) {
					if(!ball.getEffectSlowDownBall())
						fastDownBall(ball);
					break;
				}
			}

			}
	    }
	}

}




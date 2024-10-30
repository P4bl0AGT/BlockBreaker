package puppy.code;

import java.util.Random;

public class GoodBlock extends BlockDefinitive {
	private float effectDuration = 0;
	private boolean isPaddleSizeIncrease = false;
	private boolean isBallSizeIncrease = false;
	private boolean isSlowDownBall = false;
	/*private boolean isMultiBalls = false;*/
    public GoodBlock(int x, int y, int width, int height) {
        // Genera una resistencia aleatoria entre 2 y 5 para un bloque bueno
        super(x, y, width, height, new Random().nextInt(4));
    }


    public void paddleSizeIncrease(Paddle paddle) {
    	int copySize = paddle.getWidth();
        if(isPaddleSizeIncrease == false) {
	    	if (effectDuration <= 0) {  
	            effectDuration = 3f;  
	            paddle.setWidth(paddle.getWidth() * 2);  
	            isPaddleSizeIncrease = true;
	        }
	    	if (effectDuration > 3f) {
	    		effectDuration = 0;
	    		paddle.setWidth(copySize);
	    	}
        }
    }
    
    public void ballSizeIncrease(PingBall ball) {
    	int copySize = ball.getSize();
    	if (isBallSizeIncrease == false) {
	    	if (effectDuration <= 0) {
	    		effectDuration = 3f;
	    		ball.setSize(ball.getSize() * 2);
	    		isBallSizeIncrease = true;
	    	}
	    	if(effectDuration > 3f) {
	    		effectDuration = 0;
	    		ball.setSize(copySize);
	    		isBallSizeIncrease = false;
	    	}
    	}
    }
    
   public void slowDownBall(PingBall ball){
    	if(isSlowDownBall == false) {
	    	if (effectDuration <= 0) {
	    		effectDuration = 5f;
	    		ball.setxSpeed(ball.getxSpeed() / 2);
	    		ball.setySpeed(ball.getySpeed() / 2);
	    		isBallSizeIncrease = true;
	    	}
    	}
    	
    }
    
   /* public void multiBalls() {*/
    
    
	@Override
    public void applyEfect(Paddle paddle, PingBall ball) {
		
		if(destroyed) {
			int eleccion = new Random().nextInt(4);
			
			switch(eleccion){
			
			//case 0: paddleSizeIncrease(paddle);
			case 1:{
				ballSizeIncrease(ball);
				break;
			}
					
			case 2:{
				slowDownBall(ball);
				break;
			}

			}
	    }
	}

}

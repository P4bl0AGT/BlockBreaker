package puppy.code;

import java.util.Random;

public class GoodBlock extends BlockDefinitive {

	/*private boolean isMultiBalls = false;*/
    public GoodBlock(int x, int y, int width, int height) {
        // Genera una resistencia aleatoria entre 2 y 5 para un bloque bueno
        super(x, y, width, height, new Random().nextInt(4));
    }


    public void paddleSizeIncrease(Paddle paddle) {
    	
    	paddle.setWidth(paddle.getWidth() * 2);  
        paddle.setHasEffect(true);
        paddle.setEffectSizeIncrease(true);
	   
    }
     
    public void ballSizeIncrease(PingBall ball) {
    	
		ball.setSize(ball.getSize() * 2);
		ball.setHasEffect(true);
		ball.setEffectSizeIncrease(true);

	}
    
   public void slowDownBall(PingBall ball){
	    ball.setxSpeed(ball.getxSpeed() / 2);
	    ball.setySpeed(ball.getySpeed() / 2);
	    ball.setHasEffect(true);
	    ball.setEffectSlowDownBall(true);
	 
    }
    
   /* public void multiBalls() {*/
    
    
	@Override
    public void applyEfect(Paddle paddle, PingBall ball) {
		
		if(destroyed) {
			int eleccion = new Random().nextInt(4);
			
			switch(eleccion){
			
			case 0: 
				if(!paddle.getEffectSizeIncrease())
					paddleSizeIncrease(paddle);
				break;
			case 1:{
				if(!ball.getEffectSizeIncrease())
					ballSizeIncrease(ball);
				break;
			}
					
			case 2:{
				if(!ball.getEffectSlowDownBall())
					slowDownBall(ball);
				break;
			}

			}
	    }
	}

}

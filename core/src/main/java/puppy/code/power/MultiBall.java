package puppy.code.power;

import java.util.ArrayList;

import puppy.code.objetos.PingBall;

public class MultiBall implements BallStrategy{
	
	public void apply(ArrayList<PingBall> balls) {
		PingBall originalBall = balls.get(0);

		PingBall ball1 = new PingBall(
		    originalBall.getX(), 
		    originalBall.getY(), 
		    originalBall.getSize(), 
		    originalBall.getxSpeed() + 2, 
		    originalBall.getySpeed() - 2, 
		    false,
		    null
		);

		PingBall ball2 = new PingBall(
		    originalBall.getX(), 
		    originalBall.getY(), 
		    originalBall.getSize(), 
		    originalBall.getxSpeed() - 2, 
		    originalBall.getySpeed() + 2, 
		    false,
		    null
		);

		balls.add(ball1);
		balls.add(ball2);
	}
	
	public void remove(ArrayList<PingBall> balls) {
		return;
	}

}

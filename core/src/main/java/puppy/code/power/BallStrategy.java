package puppy.code.power;

import java.util.ArrayList;

import puppy.code.objetos.PingBall;

public interface BallStrategy {
	
	public void apply(ArrayList<PingBall>balls);
	public void remove(ArrayList<PingBall>balls);

}

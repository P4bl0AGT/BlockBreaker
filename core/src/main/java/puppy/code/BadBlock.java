package puppy.code;

import java.util.Random;

public class BadBlock extends BlockDefinitive{
	
	public BadBlock (int x, int y, int whidth, int height) {
		super(x, y , whidth, height, new Random().nextInt(4));
	}

	@Override
	public void applyEfect(Paddle paddle , PingBall ball) {
        if(destroyed == true) {
        	System.out.println("BLOQUE MALO");
        	// IMPLEMENTAR NERFeo
        }
	}
	protected void resetEffects(Paddle paddle , PingBall ball) {
		return;
	}

}


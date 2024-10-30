package puppy.code;

import java.util.Random;

public class NormalBlock extends BlockDefinitive{
		public NormalBlock (int x, int y, int whidth, int height) {
			super(x, y , whidth, height, new Random().nextInt(4));
		}

		@Override
		public void applyEfect(Paddle paddle, PingBall ball) {
	        if(destroyed == true) {
	        	System.out.println("BLOQUE NORMAL");	
	        }
		}
	}


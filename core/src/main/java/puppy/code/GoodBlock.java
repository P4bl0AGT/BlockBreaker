package puppy.code;

import java.util.Random;

public class GoodBlock extends BlockDefinitive {
	public GoodBlock (int x, int y, int whidth, int height) {
		super(x, y , whidth, height, new Random().nextInt(4) + 1);
	}

	@Override
	public void applyEfect() {
        if(destroyed == true) {
        	System.out.println("aumento VELOCIDAD");
        }
	}
}

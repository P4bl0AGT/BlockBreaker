package puppy.code;

import java.util.Random;

public class GoodBlock extends BlockDefinitive {
    public GoodBlock(int x, int y, int width, int height) {
        // Genera una resistencia aleatoria entre 2 y 5 para un bloque bueno
        super(x, y, width, height, new Random().nextInt(4));
    }


	@Override
	public void applyEfect() {
        if(destroyed == true) {
        	System.out.println("BLOQUE BUENO");
        }
	}
}

package puppy.code;

import com.badlogic.gdx.Screen;

public abstract class Template implements Screen{
	
	public void render(float delta) {
		iniciar();
		dibujar();
		actualizar();
		finalizar();
		
	}
	
	protected abstract void iniciar();
	protected abstract void dibujar();
	protected abstract void actualizar();
	protected abstract void finalizar();

}

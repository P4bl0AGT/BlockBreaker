package puppy.code.torretas;

import com.badlogic.gdx.graphics.Texture;

public interface Builder {
    void setTipo(EnemyType tipo);
    void setVida(int vida);
    void setTexture(Texture tx);
    void setVelocidad(int velocidad);
    void setBala(Bullet bala);
}

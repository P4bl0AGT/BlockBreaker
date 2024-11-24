package puppy.code.torretas;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Builder {
    void setTipo(EnemyType tipo);
    void setVida(int vida);
    void setSprite(Sprite spr);
    void setVelocidad(int velocidad);
    void setBala(Bullet bala);

    void reset();
    Enemy getProducto();

    //El metodo build() DEBE ir en el DIRECTOR para cumplir con el patrón BUILDER
}

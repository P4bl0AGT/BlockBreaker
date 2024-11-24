package puppy.code.torretas;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemyBuilder implements Builder {
    /* = = = = = = = = = = = = PRODUCTO  = = = = = = = = = = = = = */
    private Enemy producto;


    /* = = = = = = = = = = = = SET = = = = = = = = = = = = = */
    @Override
    public void setTipo(EnemyType tipo) {producto.setTipo(tipo);}

    @Override
    public void setVida(int vida) {producto.setVida(vida);}

    @Override
    public void setSprite(Sprite spr){producto.setSprite(spr);}

    @Override
    public void setVelocidad(int velocidad) {producto.setVelocidad(velocidad);}

    @Override
    public void setBala(Bullet bala) {producto.setBalaEnemy(bala);}


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    @Override
    public void reset(){
        this.producto = new Enemy();
    }

    @Override
    public Enemy getProducto() {
        return producto;
    }
}

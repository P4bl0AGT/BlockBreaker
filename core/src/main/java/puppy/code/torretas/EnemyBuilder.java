package puppy.code.torretas;

import com.badlogic.gdx.graphics.Texture;

public class EnemyBuilder implements Builder {
    private EnemyType tipo;
    private Texture tx;
    private int vida;
    private int velocidad;
    private Bullet bala;

    @Override
    public void setTipo(EnemyType tipo) {this.tipo = tipo;}

    @Override
    public void setVida(int vida) {this.vida = vida;}

    @Override
    public void setTexture(Texture tx){this.tx = tx;}

    @Override
    public void setVelocidad(int velocidad) {this.velocidad = velocidad; }

    @Override
    public void setBala(Bullet bala) {this.bala = bala;}


    public Enemy getEnemy() {
        return new Enemy(tipo, tx, vida, velocidad, bala);
    }
}

package puppy.code.torretas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Director {

    public void createEnemy1(Builder builder) {
        Bullet bala = new Bullet(new Texture(Gdx.files.internal("misil1_16x60.png")),200,900,16,60, 8, 300, 1);
        builder.setTipo(EnemyType.BAJO);
        builder.setVida(2);
        builder.setTexture(new Texture(Gdx.files.internal("torreta1.jpg")));
        builder.setVelocidad(5);
        builder.setBala(bala);
    }

    public void createEnemy2(Builder builder) {
        Bullet bala = new Bullet(new Texture(Gdx.files.internal("misil2_56x100.png")),400,900,28,50, 4, 400, 2);
        builder.setTipo(EnemyType.MEDIO);
        builder.setVida(4);
        builder.setTexture(new Texture(Gdx.files.internal("torreta2.jpg")));
        builder.setVelocidad(5);
        builder.setBala(bala);
    }

    public void createEnemy3(Builder builder) {
        Bullet bala = new Bullet(new Texture(Gdx.files.internal("misil3_64x64.png")),600,900,64,64, 2, 500, 3);
        builder.setTipo(EnemyType.ALTO);
        builder.setVida(6);
        builder.setTexture(new Texture(Gdx.files.internal("torreta3.jpg")));
        builder.setVelocidad(5);
        builder.setBala(bala);
    }
}


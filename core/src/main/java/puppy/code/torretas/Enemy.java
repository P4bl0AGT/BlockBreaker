package puppy.code.torretas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import puppy.code.objetos.Paddle;
import puppy.code.principal.BlockBreakerGame;

public class Enemy {
    /* = = = = = = = = = = = = ATRIBUTOS  = = = = = = = = = = = = = */
    private EnemyType tipo;
    private Sprite spr;
    private int vida;
    private int velocidad;
    private Array<Bullet> balas;
    private Bullet balaEnemy;


    /* = = = = = = = = = = = = CONSTANTES  = = = = = = = = = = = = = */
    public static final int DFLT_POS_Y = 726;
    public static final int DFLT_ALTO = 64;
    public static final int DFLT_ANCHO = 140;


    /* = = = = = = = = = = = = CONSTRUCTOR  = = = = = = = = = = = = = */
    public Enemy(EnemyType tipo, Texture tx, int vida, int velocidad, Bullet balaEnemy) {
        this.tipo = tipo;
        this.spr = new Sprite(tx);
        this.spr.setBounds(400, DFLT_POS_Y, DFLT_ANCHO, DFLT_ALTO);
        this.vida = vida;
        this.velocidad = velocidad;
        balas = new Array<>();
        this.balaEnemy = balaEnemy;
    }

    /* = = = = = = = = = = = = SET-GET = = = = = = = = = = = = = */
    public EnemyType getTipo() {
        return tipo;
    }
    public void setTipo(EnemyType tipo) {
        this.tipo = tipo;
    }

    public Sprite getSprite() {return spr;}
    public void setSpr(Sprite spr) {this.spr = spr;}

    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {this.vida = vida;}

    public int getVelocidad() {return velocidad;}
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public Bullet getBalaEnemy() { return balaEnemy; }
    public void setBalaEnemy(Bullet balaEnemy) {this.balaEnemy = balaEnemy;}


    /* = = = = = = = = = = = = METODOS = = = = = = = = = = = = = */
    public void crearBala(){
        Bullet nuevaBala = new Bullet(balaEnemy);
        balas.add(nuevaBala);
    }

    public void actualizarBalas() {
        if(balaEnemy.getContador() >= balaEnemy.getFrecuencia()) {
            balaEnemy.setContador(0);
            crearBala();
        }
        else{
            balaEnemy.setContador(balaEnemy.getContador() + 1);
        }

        for (int i = balas.size - 1; i >= 0; i--) {
            Bullet balaActual = balas.get(i);
            if(balaActual.getSprite().getY() + 64 < 0) {
                balas.removeIndex(i);
            }
            else {
                float posX = getSprite().getX() + (getSprite().getWidth() - balaEnemy.getAncho() )/ 2;
                float posY = balaActual.getY() - balaActual.getVelocidad();
                if (balaActual.getY() > BlockBreakerGame.DFLT_ALTO_PANTALLA) {
                    posY = Enemy.DFLT_POS_Y;
                }
                balaActual.getSprite().setPosition(posX, posY);
            }
        }
    }

    public void dibujarBalas(SpriteBatch batch) {
        for (int i = 0; i < balas.size; i++ ) {
            Bullet balaActual = balas.get(i);
            balaActual.getSprite().draw(batch);
        }
    }

    public void movimientoDaño(SpriteBatch batch) {
        float x = spr.getX();
        spr.setX(spr.getX() + MathUtils.random(-3, 3));
        batch.begin();
        spr.draw(batch);
        batch.end();
        spr.setX(x);
    }

    public boolean checkCollisionBullet(Paddle pad){
        for (int i = balas.size - 1; i >= 0; i--){
            Bullet balaActual = balas.get(i);
            if (balaActual.collidesWith(pad)){
                balas.removeIndex(i);
                return true;
            }
        }
        return false;
    }

}

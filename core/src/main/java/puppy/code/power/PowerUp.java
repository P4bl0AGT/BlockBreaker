package puppy.code.power;

import puppy.code.objetos.Paddle;
import puppy.code.objetos.PingBall;

public interface PowerUp {

    void apply(Paddle paddle, PingBall ball);
    void remove(Paddle paddle, PingBall ball);

}

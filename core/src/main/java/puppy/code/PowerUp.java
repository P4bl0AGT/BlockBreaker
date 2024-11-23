package puppy.code;

public interface PowerUp {
	
    void apply(Paddle paddle, PingBall ball);
    void remove(Paddle paddle, PingBall ball);

}

package puppy.code.power;



import puppy.code.objetos.Paddle;


public interface PaddleStrategy {

	void apply(Paddle pad);
    void remove(Paddle pad);
    
}

import java.util.Random;

public abstract class Obstacle extends GameObject {
    protected static final Random random = new Random();
    protected int speed;

    public Obstacle(int x, int y, String imagePath, int speed) {
        super(x, y, imagePath);
        this.speed = speed;
    }

    public abstract void move();

    public boolean isOutOfScreen() {
        return y > 700;
    }

    public void resetPosition() {
        y = -random.nextInt(500);
    }
}
public class Car extends GameObject {
    private static final String[] CAR_IMAGES = {
            "gamecar1.png", "gamecar2.png", "gamecar4.png", "gamecar3.png"
    };

    public Car(int x, int y, int carType) {
        super(x, y, CAR_IMAGES[carType % CAR_IMAGES.length]);
    }

    public void moveLeft() {
        x -= 100;
        if (x < 100) x = 100;
    }

    public void moveRight() {
        x += 100;
        if (x > 500) x = 500;
    }
}
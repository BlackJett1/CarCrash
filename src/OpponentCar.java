public class OpponentCar extends Obstacle {
    private static final String[] CAR_IMAGES = {
            "gamecar1.png", "gamecar2.png", "gamecar3.png", "gamecar4.png"
    };
    private static final int[] LANES = {100, 200, 300, 400, 500};

    public OpponentCar() {
        super(0, 0, CAR_IMAGES[random.nextInt(CAR_IMAGES.length)], 50);
        resetPosition();
    }

    @Override
    public void move() {
        y += speed;
        if (isOutOfScreen()) {
            resetPosition();
        }
    }

    @Override
    public void resetPosition() {
        x = LANES[random.nextInt(LANES.length)];
        y = -random.nextInt(1200) - 200;
    }
}
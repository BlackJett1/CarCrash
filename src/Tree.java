public class Tree extends Obstacle {
    public Tree(int x, int y) {
        super(x, y, "tree.png", 50);
    }

    @Override
    public void move() {
        y += speed;
        if (isOutOfScreen()) {
            resetPosition();
        }
    }
}
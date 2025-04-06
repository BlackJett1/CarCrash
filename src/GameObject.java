import java.awt.Graphics;
import javax.swing.ImageIcon;

public class GameObject {
    protected int x;
    protected int y;
    protected ImageIcon image;

    public GameObject(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        try {
            this.image = new ImageIcon(getClass().getResource(imagePath));
        } catch (Exception e) {
            System.err.println("Error loading image: " + imagePath);
            this.image = null;
        }
    }

    public void draw(Graphics g) {
        if (image != null) {
            image.paintIcon(null, g, x, y);
        }
    }

    public boolean collidesWith(GameObject other) {
        return x < other.x + other.getWidth() &&
                x + getWidth() > other.x &&
                y < other.y + other.getHeight() &&
                y + getHeight() > other.y;
    }

    public int getWidth() { return image != null ? image.getIconWidth() : 0; }
    public int getHeight() { return image != null ? image.getIconHeight() : 0; }
}
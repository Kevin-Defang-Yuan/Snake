import java.awt.Graphics2D;

public class SnakeUnit {
    private int x;
    private int y;
    private int width;
    private int height;
    public SnakeUnit(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics2D g2d) {
        g2d.fillRect(x, y, width, height);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

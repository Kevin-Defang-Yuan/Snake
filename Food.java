import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Food {

    private int x;
    private int y;
    private Game game;
    private int xDiameter;
    private int yDiameter;

    public Food(Game game) {
        this.game = game;
        this.xDiameter = game.board.getXUnitLength();
        this.yDiameter = game.board.getYUnitLength();
    }

    public void spawn() {
        this.x = (int) (game.board.getXUnitLength() * (int) (game.board.DIMENSION * Math.random()));
        this.y = (int) (game.board.getYUnitLength() * (int) (game.board.DIMENSION * Math.random()));
    }

    public void paint(Graphics2D g2d) {
        g2d.fillOval(x, y, xDiameter, yDiameter);
    }

    public boolean collide(Rectangle r) {
        Rectangle food = new Rectangle(x, y, xDiameter, yDiameter);
        return food.intersects(r);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    
}

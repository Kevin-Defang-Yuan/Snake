import java.awt.Graphics2D;

public class Board {
    private Game game;
    public final int DIMENSION = 15;
    private int XUnitLength;
    private int YUnitLength;
    public Board(Game game) {
        this.game = game;
        this.XUnitLength = game.getWidth() / DIMENSION;
        this.YUnitLength = game.getHeight() / DIMENSION;
    }

    public void paint(Graphics2D g2d) {
        g2d.drawLine(0, 0, 0, 0);
        for (int i = 0; i < DIMENSION; i++) {
            g2d.drawLine(i*XUnitLength, 0, i*XUnitLength, game.getHeight());
        }
        for (int i = 0; i < DIMENSION; i++) {
            g2d.drawLine(0, i*YUnitLength, game.getWidth(), i*YUnitLength);
        }
    }

    public int getXUnitLength() {
        return XUnitLength;
    }

    public int getYUnitLength() {
        return YUnitLength;
    }


}

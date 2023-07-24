
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Snake {
    private int width;
    private int height;
    private Direction direction = Direction.UP;
    private Game game;
    private ArrayList<SnakeUnit> body = new ArrayList<>();
    private final int STARTING_LENGTH = 2;
    private int length = 0;
    


    public Snake(Game game) {
        this.width = game.board.getXUnitLength();
        this.height = game.board.getYUnitLength();
        this.game = game; 
        for (int i = 0; i < STARTING_LENGTH; i++) {
            newUnit(game.getWidth() / 2 - width / 2, game.getHeight() / 2 - height / 2 + i*height);
            
        }
        
        // this.x = game.getWidth() / 2 - width / 2;
        // this.y = game.getHeight() / 2 - height / 2;
    }

    private void newUnit(int x, int y) {
        length++;
        SnakeUnit newUnit = new SnakeUnit(x, y, game.board.getXUnitLength(), game.board.getYUnitLength());
        body.add(newUnit);
    }


    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction = Direction.LEFT;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = Direction.RIGHT;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction = Direction.UP;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = Direction.DOWN;
        }
    }

    public void paint(Graphics2D g2d) {
        // g2d.fillRect(x, y, width, height);
        for (int i = 0; i < length; i++) {
            body.get(i).paint(g2d);
        }
    }

    public void move() {
        if (collision()) {
            game.gameOver();
            return;
        }

        Rectangle head = new Rectangle(body.get(0).getX(),body.get(0).getY(), width, height-1);
        if (game.food.collide(head)) {
            game.food.spawn();
            newUnit(body.get(length-1).getX(), body.get(length-1).getY());
        }

        // Move all the tail units
        for (int i = length - 1; i >= 1; i--) {
            body.get(i).setX(body.get(i-1).getX());
            body.get(i).setY(body.get(i-1).getY());
        }

        switch (direction) {
            case LEFT:
                body.get(0).setX(body.get(0).getX() - game.board.getXUnitLength());
                break;
            case RIGHT:
                body.get(0).setX(body.get(0).getX() + game.board.getXUnitLength());
                break;
            case UP:
                body.get(0).setY(body.get(0).getY() - game.board.getYUnitLength());
                break;
            case DOWN:
                body.get(0).setY(body.get(0).getY() + game.board.getYUnitLength());
                break;
        }
    }

    public boolean collision() {
        
        if ((body.get(0).getX() + width >= game.getWidth() && direction == Direction.RIGHT) || (body.get(0).getX()  < game.board.getXUnitLength() && direction == Direction.LEFT)) {
            return true;
            
        }
        if ((body.get(0).getY() + height > game.getHeight() - game.board.getYUnitLength() && direction == Direction.DOWN) || (body.get(0).getY() < game.board.getYUnitLength() && direction == Direction.UP)) {
            return true;
        }

        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                Rectangle r1 = new Rectangle(body.get(i).getX(), body.get(i).getY(), width, height);
                Rectangle r2 = new Rectangle(body.get(j).getX(), body.get(j).getY(), width, height);
                if (r1.intersects(r2)) {
                    return true;
                }
            }
        }
        return false;
    }


    
}

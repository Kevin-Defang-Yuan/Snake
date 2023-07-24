import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Game extends JPanel{

    Snake snake;
    final int BOARDSIZE = 600;
    Board board;
    Food food;
    private boolean start = false;

    public Game() {
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (!start) {
                    start = true;
                }
                else {
                    snake.keyPressed(e);
                    System.out.println("Hi");

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
            
        });
        setFocusable(true);
    }



    private void move() {
        snake.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        snake.paint(g2d);
        board.paint(g2d);
        food.paint(g2d);

    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        Game game = new Game();
        
        frame.setSize(game.BOARDSIZE, game.BOARDSIZE);
        frame.add(game);
        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.board = new Board(game);
        game.snake = new Snake(game);
        game.food = new Food(game);
        game.food.spawn();
        
        while (true) {
            
            if (game.start) {
                System.out.println("Hi");
                game.move();
                game.repaint();
                Thread.sleep(100);

            }
            else {
                game.repaint();
            }
        }
    }

    
}

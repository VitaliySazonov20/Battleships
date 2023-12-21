
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;


public class AttackPanel extends Panel implements MouseListener {
    int cellSize = 60;
    ComputerBot bot;
    gameWindow game;

    AttackPanel(ComputerBot bot, gameWindow game) {
        addMouseListener(this);
        name = "attack";
        setBoard();
        this.bot = bot;
        for (boolean[] row : clickedSpace) {
            Arrays.fill(row, false);
        }
        this.game=game;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!game.gameOver) {
            if (!clickedSpace[e.getY() / cellSize][e.getX() / cellSize]) {

                Graphics2D g2d = (Graphics2D) getGraphics();
                g2d.setStroke(new BasicStroke(3)); // Set the stroke to have a bold width
                if (board[e.getY() / cellSize][e.getX() / cellSize] > 0) {
                    g2d.setColor(Color.red);
                    g2d.fillOval((e.getX() / cellSize) * cellSize + 5, (e.getY() / cellSize) * cellSize + 5, cellSize - 10, cellSize - 10);
                    availabilityBoard[e.getY() / cellSize][e.getX() / cellSize] = -board[e.getY() / cellSize][e.getX() / cellSize];
                    board[e.getY() / cellSize][e.getX() / cellSize] = -board[e.getY() / cellSize][e.getX() / cellSize];
                    check(-board[e.getY() / cellSize][e.getX() / cellSize]);

                } else if (board[e.getY() / cellSize][e.getX() / cellSize] == 0) {
                    g2d.drawLine((e.getX() / cellSize) * cellSize + 5, (e.getY() / cellSize) * cellSize + 5, (e.getX() / cellSize) * cellSize + cellSize - 5, (e.getY() / cellSize) * cellSize + cellSize - 5);
                    g2d.drawLine((e.getX() / cellSize) * cellSize + 5, (e.getY() / cellSize) * cellSize + cellSize - 5, (e.getX() / cellSize) * cellSize + cellSize - 5, (e.getY() / cellSize) * cellSize + 5);
                    bot.makeMove();
                }
                clickedSpace[e.getY() / cellSize][e.getX() / cellSize] = true;
                checkAllDestroyed();

            }
        }

    }

    public void checkAllDestroyed() {
        if (ships.isEmpty()) {
            System.out.println("Player 1 wins!");
            game.gameOver=true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

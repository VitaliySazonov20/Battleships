import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ComputerBot {
    HomePanel attackPanel;
    int amountOfShips;
    Random random = new Random();
    int difficulty;
    boolean hit=false;
    ArrayList<Coordinate> coordinateStorage = new ArrayList<>();

    ComputerBot(HomePanel attackPanel, int difficulty) {

        this.attackPanel = attackPanel;
        amountOfShips = attackPanel.ships.size();
        this.difficulty = difficulty;

    }

    public void makeMove() {
        try {
            // Add a delay of 1000 milliseconds (1 second) before making the move
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Handle the interruption if needed
            Thread.currentThread().interrupt();
        }

        int x = random.nextInt(10);
        int y = random.nextInt(10);

        // Set the stroke to have a bold width
        while (attackPanel.clickedSpace[y][x]) {
            x = random.nextInt(10);
            y = random.nextInt(10);
        }
        if (amountOfShips > attackPanel.ships.size()) {
            coordinateStorage.clear();
            amountOfShips = attackPanel.ships.size();
        }
        if (!coordinateStorage.isEmpty() && difficulty > 1) {
            int randomIndex = random.nextInt(coordinateStorage.size());
            Coordinate attackCoordinate = coordinateStorage.get(randomIndex);
            while (attackPanel.clickedSpace[attackCoordinate.getY()][attackCoordinate.getX()]) {
                randomIndex = random.nextInt(coordinateStorage.size());
                attackCoordinate = coordinateStorage.get(randomIndex);
            }
            x= attackCoordinate.getX();
            y= attackCoordinate.getY();
            coordinateStorage.remove(attackCoordinate);
        }
        attack(x, y);

    }

    public void attack(int x, int y) {

        Graphics2D g2d = (Graphics2D) attackPanel.getGraphics();
        g2d.setStroke(new BasicStroke(3));
        if (attackPanel.board[y][x] > 0) {
            g2d.setColor(Color.red);
            g2d.fillOval((x) * attackPanel.cellSize + 5, (y) * attackPanel.cellSize + 5, attackPanel.cellSize - 10, attackPanel.cellSize - 10);
            attackPanel.availabilityBoard[y][x] = -attackPanel.board[y][x];
            attackPanel.board[y][x] = -attackPanel.board[y][x];
            attackPanel.check(-attackPanel.board[y][x]);
            if (x + 1 < 10) {
                coordinateStorage.add(new Coordinate(x + 1, y));

            }
            if (x - 1 > -1) {
                coordinateStorage.add(new Coordinate(x - 1, y));
            }
            if (y + 1 < 10) {
                coordinateStorage.add(new Coordinate(x, y + 1));
            }
            if (y - 1 > -1) {
                coordinateStorage.add(new Coordinate(x, y - 1));
            }
            if ((x + 1 < 10 && attackPanel.board[y][x + 1] == attackPanel.board[y][x]) || (x - 1 > -1 && attackPanel.board[y][x - 1] == attackPanel.board[y][x])) {
                coordinateStorage.removeIf(coordinate -> coordinate.getY() != y);
            }
            if ((y + 1 < 10 && attackPanel.board[y + 1][x] == attackPanel.board[y][x]) || (y - 1 > -1 && attackPanel.board[y - 1][x] == attackPanel.board[y][x])) {
                coordinateStorage.removeIf(coordinate -> coordinate.getX() != x);
            }
            if(difficulty==3){
                coordinateStorage.removeIf(coordinate -> attackPanel.board[coordinate.getY()][coordinate.getX()] != -attackPanel.board[y][x]);
            }
            hit=true;

        } else {
            g2d.drawLine((x) * attackPanel.cellSize + 5, (y) * attackPanel.cellSize + 5, (x) * attackPanel.cellSize + attackPanel.cellSize - 5, (y) * attackPanel.cellSize + attackPanel.cellSize - 5);
            g2d.drawLine((x) * attackPanel.cellSize + 5, (y) * attackPanel.cellSize + attackPanel.cellSize - 5, (x) * attackPanel.cellSize + attackPanel.cellSize - 5, (y) * attackPanel.cellSize + 5);
            hit=false;
        }
        attackPanel.clickedSpace[y][x] = true;
        if(attackPanel.ships.isEmpty()){
            System.out.println("Bot wins!");
            attackPanel.game.gameOver=true;
        } else if (hit) {
            makeMove();
        }

    }

}

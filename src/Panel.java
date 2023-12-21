import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class Panel extends JPanel {

    int cellSize = 60;
    int[][] board = new int[10][10];
    ArrayList<Ship> ships = new ArrayList<>();
    String name;

    int[][] availabilityBoard = new int[10][10];
    boolean[][] clickedSpace = new boolean[10][10];

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }

    public void drawGrid(Graphics graphics) {
        for (int i = 0; i < 10; i++) {
            graphics.drawLine(i * cellSize, 0, i * cellSize, 600);
        }
        graphics.drawLine(600 - 1, 0, 600 - 1, 600);
        for (int i = 0; i < 10; i++) {
            graphics.drawLine(0, i * cellSize, 600, i * cellSize);
        }
        graphics.drawLine(0, 600 - 1, 600, 600 - 1);

    }

    public void check(int id) {
        boolean destroyed = true;
        outerLoop:
        for (int i = 0; i < board.length; i++) {
            for (int[] ints : board) {

                if (ints[i] == id) {
                    destroyed = false;
                    break outerLoop;
                }
            }
        }
        if (destroyed) {
            Graphics2D g2d = (Graphics2D) getGraphics();
            int x = 0;
            int y = 0;
            boolean vertical = false;
            boolean hasNext = true;
            g2d.setStroke(new BasicStroke(3));
            outerLoop:
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {

                    if (availabilityBoard[j][i] == -id) {
                        y = j;
                        x = i;
                        break outerLoop;
                    }
                }
            }
            if (y + 1 < 10 && availabilityBoard[y + 1][x] == -id) {
                vertical = true;
            }
            while (hasNext) {
                if (y + 1 < 10 && availabilityBoard[y + 1][x] != -id) {
                    availabilityBoard[y + 1][x] = -1;
                    if (x + 1 < 10 && availabilityBoard[y + 1][x + 1] != -id) {
                        availabilityBoard[y + 1][x + 1] = -1;
                    }
                    if (x - 1 > -1 && availabilityBoard[y + 1][x - 1] != -id) {
                        availabilityBoard[y + 1][x - 1] = -1;
                    }
                }
                if (y - 1 > -1 && availabilityBoard[y - 1][x] != -id) {
                    availabilityBoard[y - 1][x] = -1;
                    if (x + 1 < 10 && availabilityBoard[y - 1][x + 1] != -id) {
                        availabilityBoard[y - 1][x + 1] = -1;
                    }
                    if (x - 1 > -1 && availabilityBoard[y - 1][x - 1] != -id) {
                        availabilityBoard[y - 1][x - 1] = -1;
                    }
                }

                if (x + 1 < 10 && availabilityBoard[y][x + 1] != -id) {
                    availabilityBoard[y][x + 1] = -1;
                }

                if (x - 1 > -1 && availabilityBoard[y][x - 1] != -id) {
                    availabilityBoard[y][x - 1] = -1;
                }
                if (x + 1 < 10 && availabilityBoard[y][x + 1] == -id) {
                    hasNext = true;
                } else hasNext = (y + 1 < 10 && availabilityBoard[y + 1][x] == -id);
                if (vertical) {
                    y++;
                } else x++;


            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {

                    if (availabilityBoard[j][i] == -1) {
                        g2d.drawLine(i * cellSize + 5, j * cellSize + 5, i * cellSize + cellSize - 5, j * cellSize + cellSize - 5);
                        g2d.drawLine(i * cellSize + 5, j * cellSize + cellSize - 5, i * cellSize + cellSize - 5, j * cellSize + 5);
                        clickedSpace[j][i] = true;
                    }
                }

            }
            Iterator<Ship> iterator = ships.iterator();
            while (iterator.hasNext()) {
                Ship ship = iterator.next();

                if (ship.shipId == id) {
                    iterator.remove();
                    break;
                }
            }


        }

    }

    public void setBoard() {
        Ship fourPointer = new Ship(4, board, availabilityBoard);
        ships.add(fourPointer);
        for (int i = 0; i < 2; i++) {
            Ship threePointer = new Ship(3, board, availabilityBoard);
            ships.add(threePointer);
        }
        for (int i = 0; i < 3; i++) {
            Ship twoPointer = new Ship(2, board, availabilityBoard);
            ships.add(twoPointer);
        }
        for (int i = 0; i < 4; i++) {
            Ship onePointer = new Ship(1, board, availabilityBoard);
            ships.add(onePointer);
        }
    }

    public void drawShips(Graphics graphics) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[j][i] > 0) {
                    graphics.fillRect(i * cellSize + 5, j * cellSize + 5, cellSize - 10, cellSize - 10);

                }
            }
        }

    }

}

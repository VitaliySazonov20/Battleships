
import java.awt.*;
import java.util.Arrays;


public class HomePanel extends Panel {
    int cellSize= 60;
    gameWindow game;



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawShips(g);
    }
    HomePanel(gameWindow game){
        name="home";
        setBoard();
        for(boolean[] row:clickedSpace){
            Arrays.fill(row, false);
        }
        this.game=game;
    }
    @Override
    public void drawShips(Graphics graphics){
        graphics.setColor(Color.BLUE);
        super.drawShips(graphics);
    }
}

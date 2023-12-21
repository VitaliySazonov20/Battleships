import javax.swing.*;
import java.awt.*;
public class gameWindow {
    private static final int WIDTH=1600;
    private static final int HEIGHT=750;
    JFrame window ;
    JButton restartButton = new JButton("Restart Game");
    boolean gameOver=false;

    //ComputerBot bot;
    gameWindow(int difficulty){
        window= new JFrame();

        HomePanel homePanel = new HomePanel(this);
        AttackPanel attackPanel = new AttackPanel(new ComputerBot(homePanel,difficulty),this);
        //bot= new ComputerBot(homePanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WIDTH, HEIGHT);
        window.setLayout(null);
        window.setVisible(true);
        attackPanel.setBounds(750,50, 600,600);
        attackPanel.setBackground(Color.lightGray);
        window.add(attackPanel);
        homePanel.setBounds(50,50, 600,600);
        homePanel.setBackground(Color.lightGray);
        window.add(homePanel);
        restartButton.setBounds(WIDTH-200,50,150,50);
        restartButton.addActionListener(e -> {
            window.dispose();
            new DifficultyWindow();
        });
        window.add(restartButton);

    }
    public void setGameOver(boolean gameOver){
        this.gameOver=gameOver;
    }

}


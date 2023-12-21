import javax.swing.*;

public class DifficultyWindow {
    JFrame window;
    JButton[] difficulty= new JButton[3];
    DifficultyWindow(){
        window=new JFrame("Choose your difficulty");
        for(int i=0; i< difficulty.length;i++){
            difficulty[i]=new JButton();
            difficulty[i].setBounds(0,i*100,200,100);
            window.add(difficulty[i]);
        }
        difficulty[0].setText("Easy");
        difficulty[1].setText("Medium");
        difficulty[2].setText("Hard");
        for(int i=0; i< difficulty.length;i++){
            int index=i;
            difficulty[i].addActionListener(e -> {
                window.dispose();
                new gameWindow(index+1);
            });
        }
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(225, 400);
        window.setLayout(null);
        window.setVisible(true);
    }
}

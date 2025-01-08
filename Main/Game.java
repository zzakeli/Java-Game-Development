package Main;

import javax.swing.*;

public class Game extends JFrame {
    protected GamePanel gamePanel = new GamePanel();

    Game() {
        super("2D Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        add(gamePanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        gamePanel.startGameThread();
    }
}

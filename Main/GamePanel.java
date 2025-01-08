package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;

public class GamePanel extends JPanel implements Runnable {

    protected Thread gameThread;
    protected KeyHandler keyH = new KeyHandler();
    protected final int originalTileSize = 16; // 16x16 tile
    protected final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    protected final int maxScreenCol = 16;
    protected final int maxScreenRow = 12;
    protected final int screenWidth = tileSize * maxScreenCol; // 768px
    protected final int screenHeight = tileSize * maxScreenRow; // 576px

    protected int FPS = 60;
    protected int playerPosX = 100;
    protected int playerPosY = 100;
    protected int playerSpeed = 4;

    protected Player player = new Player(keyH, this);

    protected GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    protected void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    protected void update() {
        player.update();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }
}

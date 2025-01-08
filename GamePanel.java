import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    protected Thread gameThread;
    protected KeyHandler keyH = new KeyHandler();
    protected final int originalTileSize = 16; // 16x16 tile
    protected final int scale = 3;
    protected final int tileSize = originalTileSize * scale; // 48x48 tile
    protected final int maxScreenCol = 16;
    protected final int maxScreenRow = 12;
    protected final int screenWidth = tileSize * maxScreenCol; // 768px
    protected final int screenHeight = tileSize * maxScreenRow; // 576px

    protected double playerPosX = 100;
    protected double playerPosY = 100;
    protected double playerSpeed = 0.00004;

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
        while (gameThread != null) {

            update();

            repaint();
        }
    }

    protected void update() {
        double diagSpeed = playerSpeed / 6;
        if (keyH.up && keyH.right) {
            playerPosX += diagSpeed;
            playerPosY -= diagSpeed;
        } else if (keyH.up && keyH.left) {
            playerPosX -= diagSpeed;
            playerPosY -= diagSpeed;
        } else if (keyH.down && keyH.right) {
            playerPosX += diagSpeed;
            playerPosY += diagSpeed;
        } else if (keyH.down && keyH.left) {
            playerPosX -= diagSpeed;
            playerPosY += diagSpeed;
        }

        if (keyH.up) {
            playerPosY -= playerSpeed;
        } else if (keyH.down) {
            playerPosY += playerSpeed;
        } else if (keyH.right) {
            playerPosX += playerSpeed;
        } else if (keyH.left) {
            playerPosX -= playerSpeed;
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect((int) playerPosX, (int) playerPosY, tileSize, tileSize);
        g2.dispose();
    }
}

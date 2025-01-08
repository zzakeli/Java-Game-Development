package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entity {

    KeyHandler keyH;
    GamePanel gamePanel;

    public Player(KeyHandler keyH, GamePanel gamePanel) {
        this.keyH = keyH;
        this.gamePanel = gamePanel;
        setDefaultValues();
        getPlayerImages();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void getPlayerImages() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Resources/Player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Resources/Player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Resources/Player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Resources/Player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Resources/Player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Resources/Player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Resources/Player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Resources/Player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        double diagSpeed = speed / 4;
        if (keyH.up && keyH.right) {
            x += diagSpeed;
            y -= diagSpeed;
        } else if (keyH.up && keyH.left) {
            x -= diagSpeed;
            y -= diagSpeed;
        } else if (keyH.down && keyH.right) {
            x += diagSpeed;
            y += diagSpeed;
        } else if (keyH.down && keyH.left) {
            x -= diagSpeed;
            y += diagSpeed;
        }

        if (keyH.up) {
            direction = "up";
            y -= speed;
        } else if (keyH.down) {
            direction = "down";
            y += speed;
        } else if (keyH.right) {
            direction = "right";
            x += speed;
        } else if (keyH.left) {
            direction = "left";
            x -= speed;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage playerImage = down1;

        switch (direction) {
            case "up":
                playerImage = up1;
                break;
            case "down":
                playerImage = down1;
                break;
            case "left":
                playerImage = left1;
                break;
            case "right":
                playerImage = right1;
                break;
            default:
                break;
        }

        g2.drawImage(playerImage, x, y, gamePanel.tileSize, gamePanel.tileSize, null);

    }
}

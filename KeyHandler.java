import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    protected boolean up, down, right, left;
    protected boolean teleport;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            up = true;
        } else if (code == KeyEvent.VK_A) {
            left = true;
        } else if (code == KeyEvent.VK_S) {
            down = true;
        } else if (code == KeyEvent.VK_D) {
            right = true;
        }

        if (code == KeyEvent.VK_C)
            teleport = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            up = false;
        } else if (code == KeyEvent.VK_A) {
            left = false;
        } else if (code == KeyEvent.VK_S) {
            down = false;
        } else if (code == KeyEvent.VK_D) {
            right = false;
        }
        if (code == KeyEvent.VK_C)
            teleport = false;
    }

}

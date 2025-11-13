import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TAdapter extends KeyAdapter {
                  //  directions:   up     down   right  left
    private double[] directions = {0.0, 0.0, 0.0, 0.0};

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("KEYPRESSED");

        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT)) {
            directions[3] = 1.0;
        }

        if ((key == KeyEvent.VK_RIGHT)) {
            directions[2] = 1.0;
        }

        if ((key == KeyEvent.VK_UP)) {
            directions[0] = 1.0;
        }

        if ((key == KeyEvent.VK_DOWN)) {
            directions[1] = 1.0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT)) {
            directions[3] = 0.0;
        }

        if ((key == KeyEvent.VK_RIGHT)) {
            directions[2] = 0.0;
        }

        if ((key == KeyEvent.VK_UP)) {
            directions[0] = 0.0;
        }

        if ((key == KeyEvent.VK_DOWN)) {
            directions[1] = 0.0;
        }
    }

    public double[] getDirections() {
        return directions;
    }
}
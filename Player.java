import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
    private double x;
    private double y;
    private double vx = 0;
    private double vy = 0;
    private Image img;
    private int height;
    private int width;

    public Player()
    {
        x = 120;
        y = 200;
        width = 60;
        height = 60;

        try {
            img = ImageIO.read(new File("Assets/duck.png")); // Specify your image path
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics2D drawingPanel)
    {
        drawingPanel.drawImage(img, (int) x, (int) y, width, height, null);
    }
}


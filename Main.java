import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Main {
    private static Player player;
    private static GraphicsExample graphics;
    public static void main(String[] args) {
        player = new Player();
        graphics = new GraphicsExample(player);    
        gameLoop();    
    }

    public static void gameLoop()
    {
        // while (true)
        // {
        
        // }
    }


}
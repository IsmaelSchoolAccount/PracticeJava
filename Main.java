import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import java.awt.Image;
import javax.swing.JFrame;

public class Main {
    
    private static Player player;
    private static GraphicsExample graphics;
    private static Tilemap tilemap;
    private static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private static TAdapter keyListener = new TAdapter();
    public static void main(String[] args) {

        double[] pos = {120.0, 200.0};
        int[] size = {60, 60};
        Image img = null;
        try {
            img = ImageIO.read(new File("Assets/duck.png")); // Specify your image path
        } catch (IOException e) {
            e.printStackTrace();
        }
        player = new Player("Player", pos, size, img);
        tilemap = new Tilemap(Constants.tile_size);
        graphics = new GraphicsExample(player, tilemap);
        

        // Runnable repaint = () -> {
        //     // Your code to be executed repeatedly
        //     double[] inputs = {5.0, 0.0};
        //     player.update(tilemap, inputs)
        //     System.out.println("REPRINT")
        //     graphics.frame.repaint
        // }; 

        int executionsPerSecond = 60; //runs 60 times per second
        long periodMilliseconds = 1000L / executionsPerSecond;

        scheduler.scheduleAtFixedRate(() -> graphics.frame.repaint(), 0, periodMilliseconds, TimeUnit.MILLISECONDS);
          
        game();
    }

    public static void game()
    {
        
        try {
            Thread.sleep(5000); // Let the task run for 5 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        scheduler.shutdown();
        System.out.println("Scheduler shut down.");
    }


}
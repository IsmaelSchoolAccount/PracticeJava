import java.awt.Graphics2D;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Main {
    private static Player player = new Player();
    private static GraphicsExample graphics = new GraphicsExample(player);
    private static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    public static void main(String[] args) {
        Runnable repaint = () -> {
            // Your code to be executed repeatedly
            graphics.frame.repaint();
        }; 

        int executionsPerSecond = 60; //runs 60 times per second
        long periodMilliseconds = 1000L / executionsPerSecond;

        scheduler.scheduleAtFixedRate(repaint, 0, periodMilliseconds, TimeUnit.MILLISECONDS);
          
        gameLoop();
    }

    public static void gameLoop()
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
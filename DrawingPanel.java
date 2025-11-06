import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

class DrawingPanel extends JPanel {

    private Player player;
    private Tilemap tilemap;

    public DrawingPanel(Player m_player, Tilemap m_tilemap)
    {
        repaint();
        player = m_player;
        tilemap = m_tilemap;
        
    }

    public void paint(Graphics g) {

        int[] offset = {0, 0};

        // Cast Graphics object to Graphics2D for more advanced drawing options
        Graphics2D g2d = (Graphics2D) g;

        double[] inputs = {1.0, 0.0};
        player.update(tilemap, inputs);

        player.render(g2d, offset);
        System.out.println("REPRINT");
        
    }

    
}
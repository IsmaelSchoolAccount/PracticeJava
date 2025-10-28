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

    public DrawingPanel(Player m_player)
    {
        repaint();
        player = m_player;
        
    }

    public void paint(Graphics g) {

        // Cast Graphics object to Graphics2D for more advanced drawing options
        Graphics2D g2d = (Graphics2D) g;

        player.render(g2d);
        
    }

    
}
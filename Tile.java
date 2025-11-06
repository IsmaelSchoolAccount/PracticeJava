import java.awt.Graphics2D;
import java.awt.Image;

public class Tile
{
    private int[] position;
    private String type;
    private Image image;

    public Tile(int[] m_position, String m_type, Image m_image)
    {
        position = m_position;
        type = m_type;
        image = m_image;
    }

    public String getType()
    {
        return type;
    }

    public void render(Graphics2D g2d, int[] offset)
    {
        g2d.drawImage(image, (int) position[0] + offset[0], (int) position[1] + offset[1], Constants.tile_size, Constants.tile_size, null);
    }
}
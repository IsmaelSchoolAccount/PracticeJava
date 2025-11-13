import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends PhysicsEntity{

    private TAdapter keys;

    public Player(String m_e_type, double[] m_pos, int[] m_size, Image m_img) {
        super(m_e_type, m_pos, m_size, m_img);
        keys = new TAdapter();
    }

    public void update(Tilemap tilemap)
    {
        update(tilemap, keys.getDirections());
    }
}


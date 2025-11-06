import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class PhysicsEntity {
    private String e_type;
    private double[] pos = {0.0, 0.0};
    private int[] size = {0, 0};
    private double[] velocity = {0.0, 0.0};
    //                directions:   up     down   right  left
    private boolean[] collisions = {false, false, false, false};
    private boolean flip = false;
    private Image img;

    public PhysicsEntity(String m_e_type, double[] m_pos, int[] m_size, Image m_img)
    {
        e_type = m_e_type;
        pos = m_pos;
        size = m_size;
        img = m_img;
    }

    private void resetCollisions()
    {
        collisions[0] = false;
        collisions[1] = false;
        collisions[2] = false;
        collisions[3] = false;
    }

    private Rectangle get_rect()
    {
        return new Rectangle((int)pos[0], (int)pos[1], size[0], size[1]);
    }

    public void update(Tilemap tilemap, double[] movement)
    {
        resetCollisions();

        double[] frame_movement = {movement[0] + velocity[0], movement[1] + velocity[1]};

        pos[0] += frame_movement[0];
        System.out.println(pos[0]);

        Rectangle entity_rect = get_rect();
        for (Rectangle rect: tilemap.physics_rects_around(tilemap.point_to_location(pos)))
        {
            if (entity_rect.intersects(rect))
            {
                if (frame_movement[0] > 0)
                {
                    entity_rect.setLocation((int)rect.getX(), (int)entity_rect.getY());
                    collisions[2] = true;
                }
                if (frame_movement[0] < 0)
                {
                    entity_rect.setLocation((int)(rect.getX() + rect.getWidth()), (int)entity_rect.getY());
                    collisions[3] = true;
                }
                pos[0] = entity_rect.getX();
                System.out.println(pos[0]);
            }
        }

        pos[1] += frame_movement[1];

        entity_rect = get_rect();
        for (Rectangle rect: tilemap.physics_rects_around(tilemap.point_to_location(pos)))
        {
            if (entity_rect.intersects(rect))
            {
                if (frame_movement[1] > 0)
                {
                    entity_rect.setLocation((int)entity_rect.getX(), (int)rect.getY());
                    collisions[1] = true;
                }
                if (frame_movement[1] < 0)
                {
                    entity_rect.setLocation((int)entity_rect.getX(), (int)(rect.getY() + rect.getHeight()));
                    collisions[0] = true;
                }
                pos[0] = entity_rect.getX();
            }
        }

        if (movement[0] > 0)
        {
            flip = false;
        }
        if (movement[0] < 0)
        {
            flip = true;
        }

        velocity[0] = velocity[0] + movement[0] * 0.1;
        velocity[0] *= 0.9;

        if (collisions[2] || collisions[3])
        {
            velocity[0] = 0;
        }
        if (collisions[0] || collisions[1])
        {
            velocity[1] = 0;
        }
    }

    public void render(Graphics2D g2d, int[] offset)
    {
        g2d.drawImage(img, (int) pos[0] + offset[0], (int) pos[1] + offset[1], size[0], size[1], null);
    }
}

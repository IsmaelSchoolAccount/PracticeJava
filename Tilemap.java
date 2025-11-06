import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Arrays;

public class Tilemap{
    private int tile_size;
    private Tile[][] tilemap = new Tile[200][200];

    public Tilemap(int m_tile_size)
    {
        tile_size = m_tile_size;
    } 

    public int[] point_to_location(double[] pos)
    {
        int[] location = {(int)(pos[0] / tile_size), (int)(pos[1] / tile_size)};
        return location;
    }

    public double[] location_to_point(int[] pos)
    {
        double[] point = {pos[0] * tile_size, pos[1] * tile_size};
        return point;
    }

    public Tile[] tiles_around(int[] pos)
    {
        Tile[] tiles = {};

        for (int[] offset: Constants.NEIGHBOR_OFFSETS)
        {
            int[] check_loc = {pos[0] + offset[0], pos[1] + offset[1]};
            if (tilemap[check_loc[0]][check_loc[1]] != null)
            {
                tiles[tiles.length] = tilemap[check_loc[0]][check_loc[1]];
            }
        }

        return tiles;
    }

    public Rectangle[] physics_rects_around(int[] pos)
    {
        Rectangle[] rects = {};
        for (Tile tile: tiles_around(pos))
        {
            if (Arrays.asList(Constants.PHYSICS_TILES).contains(tile.getType()))
            {
                rects[rects.length] = new Rectangle(pos[0]*tile_size, pos[1]*tile_size, tile_size, tile_size);
            }
        }

        return rects;
    }

    public void render(Graphics2D g2d, int[] offset)
    {
        for (Tile[] tiles: tilemap)
        {
            for (Tile tile: tiles)
            {
                tile.render(g2d, offset);
            }
        }
    }

}
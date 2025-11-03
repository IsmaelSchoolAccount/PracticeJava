public class Tilemap{
    private int tile_size;
    private int[][] tilemap;

    public Tilemap(int m_tile_size)
    {
        tile_size = m_tile_size;
    } 

    public int[] point_to_location(double[] pos)
    {
        int[] location = {pos[0] / tile_size, pos[1] / tile_size};
        return location;
    }

    public int[] location_to_point(int[] pos)
    {
        double[] point = {pos[0] * tile_size, pos[1] * tile_size};
        return point;
    }

}

    def tiles_around(self, pos, offsets):
        tiles = []
        tile_loc = self.point_to_loc(pos)
        for offset in offsets:
            check_loc = (tile_loc[0] + offset[0], tile_loc[1] + offset[1])
            if check_loc in self.tilemap:
                tiles.append(self.tilemap[check_loc])
        return tiles

    def physics_rects_around(self, pos):
        rects = []
        for tile in self.tiles_around(pos, constants.NEIGHBOR_OFFSETS):
            if tile.type in constants.PHYSICS_TILES:
                rects.append(pygame.Rect(tile.pos[0] * self.tile_size, tile.pos[1] * self.tile_size, self.tile_size, self.tile_size))
        return rects
    
    def check_win(self, pos):
        for tile in self.tiles_around(pos, constants.OVERLAP_OFFSETS):
            if tile.type == "win":
                return True
        return False

    def render(self, surface, offset=(0, 0)):
        for x in range(offset[0] // self.tile_size, (offset[0] + surface.get_width()) // self.tile_size + 1):
            for y in range(offset[1] // self.tile_size, (offset[1] + surface.get_height()) // self.tile_size + 1):
                location = (x, y)
                if location in self.tilemap:
                    tile = self.tilemap[(x, y)]  
                    surface.blit(self.game.assets[tile.type][tile.variant], (tile.pos[0] * self.tile_size - offset[0], tile.pos[1] * self.tile_size - offset[1]))  
                    
    def tile_to_dict(self, tilemap) -> dict:
        tiles = {}
        for tile in tilemap:
            tiles[str(tile[0]) + ";" + str(tile[1])] = {"type": tilemap[tile].type, "variant": tilemap[tile].variant, "pos": tilemap[tile].pos}
        return tiles
    
    def dict_to_tile(self, game, tilemap):
        tiles = {}
        for tile in tilemap:
            if tilemap[tile]["type"] == "swallows" and not self.game.editor:
                game.swallows.append(swallowable(self.game, (tilemap[tile]["pos"][0] * self.tile_size, tilemap[tile]["pos"][1] * self.tile_size), tilemap[tile]["type"], tilemap[tile]["variant"], tilemap[tile]["pos"][1]*self.tile_size))
            else:
                tiles[(tilemap[tile]["pos"][0], tilemap[tile]["pos"][1])] = Tile(tilemap[tile]["pos"], tilemap[tile]["type"], tilemap[tile]["variant"])
        return tiles
                    
    def save(self, path):
        f = open(path, "w")
        json.dump({"tile_size": 16, "tilemap": self.tile_to_dict(self.tilemap)}, f)
        f.close()

    def load(self, game, path):
        f = open(path, "r")
        map_data = json.load(f)
        f.close()

        self.tile_size = map_data["tile_size"]
        self.tilemap = self.dict_to_tile(game, map_data["tilemap"])

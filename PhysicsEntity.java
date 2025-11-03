
public class PhysicsEntity {
    private String e_type;
    private double[] pos = {0.0, 0.0};
    private int size;
    private double[] velocity = {0.0, 0.0};
    //                directions:   up     down   right  left
    private boolean[] collisions = {false, false, false, false};

    public PhysicsEntity(String m_e_type, double[] m_pos, int m_size)
    {
        e_type = m_e_type;
        pos = m_pos;
        size = m_size;
    }

    def update(self, tilemap, movement=(0, 0)):
        self.collisions = {'up': False, 'down': False, 'right': False, 'left': False}
        
        frame_movement = (movement[0] + self.velocity[0], movement[1] + self.velocity[1])
        
        self.pos[0] += frame_movement[0]
        entity_rect = self.rect()
        for rect in tilemap.physics_rects_around(self.pos):
            if entity_rect.colliderect(rect):
                if frame_movement[0] > 0:
                    entity_rect.right = rect.left
                    self.collisions['right'] = True
                if frame_movement[0] < 0:
                    entity_rect.left = rect.right
                    self.collisions['left'] = True
                self.pos[0] = entity_rect.x
        
        self.pos[1] += frame_movement[1]
        entity_rect = self.rect()
        for rect in tilemap.physics_rects_around(self.pos):
            if entity_rect.colliderect(rect):
                if frame_movement[1] > 0:
                    entity_rect.bottom = rect.top
                    self.collisions['down'] = True
                if frame_movement[1] < 0:
                    entity_rect.top = rect.bottom
                    self.collisions['up'] = True
                self.pos[1] = entity_rect.y
                
        if movement[0] > 0:
            self.flip = False
        if movement[0] < 0:
            self.flip = True
            
        self.last_movement = movement

        self.last_vel = self.velocity
        
        self.velocity[0] = self.velocity[0] + movement[0] * 0.1
        self.velocity[0] *= 0.9
        
        if self.collisions['right'] or self.collisions['left']:
            self.velocity[0] = 0
        if self.collisions['down'] or self.collisions['up']:
            self.velocity[1] = 0
    
        self.animation.update()

    def render(self, surf, offset=(0, 0)):
        surf.blit(pygame.transform.flip(self.animation.img(), self.flip, False), (self.pos[0] - offset[0] + self.anim_offset[0], self.pos[1] - offset[1] + self.anim_offset[1]))
        #surf.blit(pygame.transform.flip(pygame.surface.Surface(self.size), self.flip, False), (self.pos[0] - offset[0], self.pos[1] - offset[1]))

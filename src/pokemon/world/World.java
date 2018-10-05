package pokemon.world;

import engine.rendering.Renderer;
import engine.window.Window;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class World extends engine.components.world.World{

    private ArrayList<Tile> tiles;
    
    private int gridX = 25, gridY = 25;
    
    public World(Window window, float width, float height, Renderer.Layer layer) {
        super(window, width, height, layer);
        
        tiles = new ArrayList<>();
    }

    public void initialize(){
        
        for(int i = 0; i < 300; i++){
            for(int j = 0; j < 300; j++){
                tiles.add(new Tile(layer, i*gridX, j*gridY, gridX, gridY, "pokemon/resources/images/world/grass.png"));
            }
        }
        
        boolean asdf = false;
        
        
    }
    
    @Override
    public void draw(Graphics2D g, float x, float y) {
        
        for (Tile tile : tiles) {
            tile.draw(g, x+tile.getX(), y+tile.getY());
            
        }
        
    }
    
}

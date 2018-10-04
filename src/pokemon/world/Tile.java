package pokemon.world;

import engine.filesystem.FileSystem;
import engine.filesystem.ImageHandler;
import engine.rendering.RenderableObject;
import engine.rendering.Renderer;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile extends RenderableObject{

    private String imgSource;
    private BufferedImage img;
    
    public Tile(Renderer.Layer layer, float x, float y, float width, float height, String imgSource) {
        super(layer, x, y, width, height);
        
        this.imgSource = imgSource;
        img = FileSystem.readInternImage(imgSource);
        img = ImageHandler.scaleImage(img, (int)width, (int)height);
    }

    @Override
    public void draw(Graphics2D g, float x, float y) {
        g.drawImage(img, (int)x, (int)y, null);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
    
    
}

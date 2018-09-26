package pokemon.visualisation.text;

import engine.Time;
import engine.filesystem.FileSystem;
import engine.filesystem.ImageHandler;
import engine.rendering.RenderableObject;
import engine.rendering.Renderer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class TextBox extends RenderableObject{

    private Dimension   win_size;
    
    private String text;
    private String visibleText;
    
    public static int TEXT_SPEED_SLOW = 10;
    public static int TEXT_SPEED_MEDIUM = 18;
    public static int TEXT_SPEED_FAST = 30;
    public static int TEXT_SPEED_FASTEST = 50;
    public static int TEXT_SPEED_INSTANT = 1000;
    
    private BufferedImage img;
    
    public TextBox(Dimension panelSize) {
        super(Renderer.Layer.LAYER_5, 0, panelSize.height - panelSize.height/5, panelSize.width -10, panelSize.height/5 -10);
        win_size = panelSize;
        
        text = "";
        visibleText = "";
        
        img = new BufferedImage((int)win_size.width -10, (int)win_size.height/5 -10, BufferedImage.TYPE_INT_ARGB);
    }

    public void setText(String text){
        this.text = text;
    }
    
    public void writeText(int charPerSecond){
        for(int i = 0; i < text.length(); i++){
            visibleText += text.charAt(i);
            
            update();
            
            Time.sleep(1000 / charPerSecond);
        }
    }
    
    public void clearText(){
        
    }
    
    public void update(){
        Graphics2D g = (Graphics2D)img.createGraphics();
        
        g.setColor(new Color(130,130,130));
        g.fillRoundRect(0, 0, (int)width, (int)height, 20, 20);
        g.setColor(new Color(45,160,175));
        g.fillRoundRect(2, 2, (int)width -4, (int)height -4, 20, 20);
        g.setColor(Color.cyan);
        g.fillRoundRect(7, 7, (int)width -14, (int)height -14, 20, 20);
        g.setColor(Color.black);
        g.fillRoundRect(9, 9, (int)width -18, (int)height -18, 20, 20);
        g.setColor(Color.white);
        g.fillRoundRect(10, 10, (int)width -20, (int)height -20, 20, 20);
        
        if(visibleText != null && visibleText.length() > 0){
            g.setColor(Color.black);
            g.setFont(new Font("MS Serif", 0, 40));
            g.drawString(visibleText, 12 + 10, 12 + 40);
        }
        
    }
    
    @Override
    public void draw(Graphics2D g, float x, float y) {
        if(img != null){
            g.drawImage(img, (int)this.x +5, (int)this.y +5, null);
        }
    }
    
}

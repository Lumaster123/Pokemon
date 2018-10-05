package pokemon.entity.player;

import engine.ThreadHandler;
import engine.Time;
import engine.components.Keyboard;
import engine.components.entity.Entity;
import engine.components.world.World;
import engine.physic.Direction;
import engine.physic.Physic_MoveListener;
import engine.rendering.Renderer;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player extends Entity implements Physic_MoveListener{

    public Player(World world, float x, float y, float width, float height, Renderer.Layer layer, Keyboard keyboard) {
        super(world, x, y, width, height, layer, keyboard);
    }


    @Override
    public void draw(Graphics2D g, float x, float y) {
        g.setColor(Color.red);
        g.fillOval((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public void collision(Object object, Direction direction) {
        
    }

    @Override
    public void move() {
        
        boolean w, a, s, d, space, up, left, down, right;
        
        w = keyboard.getPressedKeys().contains(KeyEvent.VK_W);
        a = keyboard.getPressedKeys().contains(KeyEvent.VK_A);
        s = keyboard.getPressedKeys().contains(KeyEvent.VK_S);
        d = keyboard.getPressedKeys().contains(KeyEvent.VK_D);
        
        space = keyboard.getPressedKeys().contains(KeyEvent.VK_SPACE);
        
        up = keyboard.getPressedKeys().contains(KeyEvent.VK_UP);
        left = keyboard.getPressedKeys().contains(KeyEvent.VK_LEFT);
        down = keyboard.getPressedKeys().contains(KeyEvent.VK_DOWN);
        right = keyboard.getPressedKeys().contains(KeyEvent.VK_RIGHT);
        
//        System.out.println(w+"\t"+a+"\t"+s+"\t"+d+"\t"+space+"\t"+up+"\t"+left+"\t"+down+"\t"+right);
        
        if(w || up){
            speedY = -2f;
        }else if(s || down){
           speedY = 2f;
        }else{
            speedY = 0;
        }
        
        float update = 0.1f;
        float speed = 2f;
        
        if((a || left) && (d || right)){
            if(speedX > 0){
                if(speedX - update < 0){
                    speedX = 0;
                }else{
                    speedX -= update;
                }
            }else if(speedX < 0){
                if(speedX + update > 0){
                    speedX = 0;
                }else{
                    speedX += update;
                }
            }
            
        }else if(a || left){
            
            speedX = -speed;
            
        }else if(d || right){
            
            speedX = speed;
            
        }else{
            if(speedX > 0){
                if(speedX - update < 0){
                    speedX = 0;
                }else{
                    speedX -= update;
                }
            }else if(speedX < 0){
                if(speedX + update > 0){
                    speedX = 0;
                }else{
                    speedX += update;
                }
            }
            
        }
        
        
        x += speedX;
        y += speedY;
        
    }
    
}

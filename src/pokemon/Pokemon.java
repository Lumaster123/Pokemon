package pokemon;

import engine.Initializer;
import engine.Time;
import engine.components.Config;
import engine.rendering.Renderer;
import engine.window.Window;
import pokemon.visualisation.text.TextBox;

public class Pokemon {
    
    public static void main(String[] args){
        
        Initializer init = new Initializer();
        Config.FPS_CURRENT_SETTING = Config.FPS_60;
        init.initializeWindow(new Window(Window.Window_Size.FULL_SIZE, "Pokemon", "pokemon/resources/images/icon/ultraball.png"));
        init.initializeConsole();
        init.initializeRenderer();
        Time.sleep(5);
        
        DataManager.initializeData();
        
        TextBox box = new TextBox(init.getWindow().getContentPane().getSize());
        
        
        Renderer.getRenderList().add(box);
        
        box.update();
        
        box.setText("Hello World! how are you today my friend dddddddddddd <br> dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        Time.sleep(2 * 1000);
        box.writeText(TextBox.TEXT_SPEED_FASTEST);
        
        System.out.println(box.getTextWidth() + " " + box.getTextHeight());
        
        init.printInitializationDuration();
        
    }
    
}

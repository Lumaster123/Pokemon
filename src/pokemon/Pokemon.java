package pokemon;

import engine.Initializer;
import engine.Time;
import engine.components.Config;
import engine.components.Keyboard;
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
        Keyboard keyboard = new Keyboard();
        init.initializeKeyboard(keyboard);
        Time.sleep(5);
        
        DataManager.initializeData();
        
        init.printInitializationDuration();
        
        TextBox box = new TextBox(init.getWindow().getContentPane().getSize());
        keyboard.addListener(box);
        
        Renderer.getRenderList().add(box);
        
        box.update();
        
        box.setText("Hello World! how are you today my friend I need some text to stand here but I don't know what to write so I write and write until I have enought word standing here.\nI hope these are enough.");
        Time.sleep(2 * 1000);
        box.writeText(TextBox.TEXT_SPEED_FASTEST);
        
        
        
        
    }
    
}

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
        
        box.setText("d d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d d");
        //box.setText("Test if it works");
        //box.writeText(TextBox.TEXT_SPEED_FASTEST);
        System.out.println(box.writeTextWithAnswer(TextBox.TEXT_SPEED_INSTANT, new String[]{"Ja","Nein"}));
        
        
        
    }
    
}

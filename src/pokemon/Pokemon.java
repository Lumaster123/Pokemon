package pokemon;

import engine.Initializer;
import engine.window.Window;

public class Pokemon {
    
    public static void main(String[] args){
        
        Initializer init = new Initializer();
        
        init.initializeWindow(new Window(Window.Window_Size.FULL_SIZE, "Pokemon", null));
        
    }
    
}
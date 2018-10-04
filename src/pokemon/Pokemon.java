package pokemon;

import engine.Initializer;
import engine.Time;
import engine.components.Config;
import engine.components.Keyboard;
import engine.rendering.Renderer;
import engine.window.Window;
import pokemon.AttackMoves.BaseAttackMove;
import pokemon.data.TypeEfficiency;
import pokemon.pokemon.BasePokemon;
import pokemon.pokemon.ElementType;
import pokemon.pokemon.ExpType;
import pokemon.visualisation.text.TextBox;
import pokemon.world.World;

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
        
        World world = new World(init.getWindow(), 15000, 15000, Renderer.Layer.LAYER_0);
        world.initialize();
        Renderer.getRenderList().add(world);
        
        init.printInitializationDuration();
        
        TextBox box = new TextBox(init.getWindow().getContentPane().getSize());
        keyboard.addListener(box);
        
        Renderer.getRenderList().add(box);
        
        box.update();
        
        BasePokemon test = new BasePokemon(ElementType.FIGHT, ElementType.BUG);
        test.setLevel(100);
        test.setExpType(ExpType.FLUCTUATING);
        test.leveling();
        
        BaseAttackMove rasierblatt = new BaseAttackMove(ElementType.FLYING);
        
        System.out.println(" help " + test.getExpNeeded());
        
        
        float damageMultiplier = TypeEfficiency.getDamageMultiplier(rasierblatt, test);
        System.out.println(damageMultiplier);
        
        
        box.setText("d d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d dd d d d d d d d d d d d d d d dd  d d d d d d d d");
//        box.setText("Test if it works");
//        box.writeText(TextBox.TEXT_SPEED_FASTEST);
        //System.out.println(box.writeTextWithAnswer(TextBox.TEXT_SPEED_INSTANT, new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17"}));
        
        
        
        
        
    }
    
}

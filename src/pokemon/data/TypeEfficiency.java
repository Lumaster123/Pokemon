package pokemon.data;

import java.util.ArrayList;
import java.util.HashMap;
import pokemon.AttackMoves.BaseAttackMove;
import pokemon.pokemon.BasePokemon;
import pokemon.pokemon.ElementType;

public class TypeEfficiency {
    
    private static HashMap<ElementType, ArrayList<ElementType>> strong = new HashMap<>();
    private static HashMap<ElementType, ArrayList<ElementType>> weak = new HashMap<>();
    private static HashMap<ElementType, ArrayList<ElementType>> no_effekt = new HashMap<>();
    
    
    public static float getDamageMultiplier(BaseAttackMove attack, BasePokemon defender){
        int count = 0;
        
        for(ElementType defendType : no_effekt.get(attack.getElementType())){
            if(defendType == defender.getElementType(0)){
                return 0;
            }
            if(defendType == defender.getElementType(1)){
                return 0;
            }
        }
        
        for(ElementType defendType : strong.get(attack.getElementType())){
            if(defendType == defender.getElementType(0)){
                count++;
            }
            if(defendType == defender.getElementType(1)){
                count++;
            }
        }
        
        for(ElementType defendType : weak.get(attack.getElementType())){
            if(defendType == defender.getElementType(0)){
                count--;
            }
            if(defendType == defender.getElementType(1)){
                count--;
            }
        }
        
        switch (count) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case -1:
                return 0.5f;
            case -2:
                return 0.25f;
            default:
                System.err.println("The TypeEfficiency_count returned "+count+" with is an uncaught state!");
                return 0;
        }
    }
    
    public static void fillEfficiencyList(){
        ArrayList<ElementType> list = new ArrayList<>();
        
        // exsample for insert
        list.add(ElementType.GRASS);
        list.add(ElementType.ICE);
        strong.put(ElementType.FIRE, list);
        list.clear();
        list.add(ElementType.WATER);
        list.add(ElementType.GROUND);
        weak.put(ElementType.FIRE, list);
        list.clear();
        // if has no effect do the same as before with no_effect list
        
        
    }
    
}

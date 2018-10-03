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

    public static float getDamageMultiplier(BaseAttackMove attack, BasePokemon defender) {
        int count = 0;
        
        for (ElementType defendType : no_effekt.get(attack.getElementType())) {
            if (defendType == defender.getElementType(0)) {
                return 0;
            }
            if (defendType == defender.getElementType(1)) {
                return 0;
            }
        }


        for (ElementType defendType : strong.get(attack.getElementType())) {
            if (defendType == defender.getElementType(0)) {
                count++;
            }
            if (defendType == defender.getElementType(1)) {
                count++;
            }
        }

        for (ElementType defendType : weak.get(attack.getElementType())) {
            if (defendType == defender.getElementType(0)) {
                count--;
            }
            if (defendType == defender.getElementType(1)) {
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
                System.err.println("The TypeEfficiency_count returned " + count + " with is an uncaught state!");
                return 0;
        }
    }

    public static void fillEfficiencyList() {
        ArrayList<ElementType> list = new ArrayList<>();

        //Normal
        list.add(ElementType.ROCK);
        list.add(ElementType.STEEL);
        weak.put(ElementType.NORMAL, list);
        list.clear();

        list.add(ElementType.GHOST);
        no_effekt.put(ElementType.NORMAL, list);
        list.clear();
        //Normal End

        //Fight
        list.add(ElementType.NORMAL);
        list.add(ElementType.ROCK);
        list.add(ElementType.STEEL);
        list.add(ElementType.ICE);
        list.add(ElementType.DARK);
        strong.put(ElementType.FIGHT, list);
        list.clear();

        list.add(ElementType.FLYING);
        list.add(ElementType.POISON);
        list.add(ElementType.BUG);
        list.add(ElementType.PSYCHIC);
        list.add(ElementType.FAIRY);
        weak.put(ElementType.FIGHT, list);

        list.add(ElementType.GHOST);
        no_effekt.put(ElementType.FIGHT, list);
        list.clear();
        //Fight End

        //Flying
        list.add(ElementType.FIGHT);
        list.add(ElementType.BUG);
        list.add(ElementType.GRASS);
        strong.put(ElementType.FLYING, list);
        list.clear();

        list.add(ElementType.ROCK);
        list.add(ElementType.STEEL);
        list.add(ElementType.ELECTRO);
        weak.put(ElementType.FLYING, list);
        list.clear();
        //Flying End

        //Poison
        list.add(ElementType.GRASS);
        list.add(ElementType.FAIRY);
        strong.put(ElementType.POISON, list);
        list.clear();

        list.add(ElementType.POISON);
        list.add(ElementType.GROUND);
        list.add(ElementType.ROCK);
        list.add(ElementType.GHOST);
        weak.put(ElementType.POISON, list);
        list.clear();

        list.add(ElementType.STEEL);
        no_effekt.put(ElementType.POISON, list);
        list.clear();
        //Poison End
        
        //Ground
        list.add(ElementType.POISON);
        list.add(ElementType.ROCK);
        list.add(ElementType.STEEL);
        list.add(ElementType.FIRE);
        list.add(ElementType.ELECTRO);
        strong.put(ElementType.GROUND, list);
        list.clear();
        
        list.add(ElementType.BUG);
        list.add(ElementType.GRASS);
        weak.put(ElementType.GROUND, list);
        list.clear();
        
        list.add(ElementType.FLYING);
        no_effekt.put(ElementType.GROUND, list);
        list.clear();
        //Ground End
        
        //Rock
        list.add(ElementType.FLYING);
        list.add(ElementType.BUG);
        list.add(ElementType.FIRE);
        list.add(ElementType.ICE);
        strong.put(ElementType.ROCK,list);
        list.clear();
        
        list.add(ElementType.FIGHT);
        list.add(ElementType.GROUND);
        list.add(ElementType.STEEL);
        weak.put(ElementType.ROCK, list);
        list.clear();
        //Rock End
        
        //Bug
        list.add(ElementType.GRASS);
        list.add(ElementType.PSYCHIC);
        list.add(ElementType.DARK);
        strong.put(ElementType.BUG, list);
        list.clear();
        
        list.add(ElementType.FIGHT);
        list.add(ElementType.FLYING);
        list.add(ElementType.POISON);
        list.add(ElementType.GHOST);
        list.add(ElementType.STEEL);
        list.add(ElementType.FIRE);
        list.add(ElementType.FAIRY);
        weak.put(ElementType.BUG, list);
        list.clear();
        //Bug End
        
        //Ghost
        list.add(ElementType.GHOST);
        list.add(ElementType.ELECTRO);
        strong.put(ElementType.GHOST, list);
        list.clear();
        
        list.add(ElementType.DARK);
        weak.put(ElementType.GHOST, list);
        list.clear();
        
        list.add(ElementType.NORMAL);
        no_effekt.put(ElementType.GHOST, list);
        list.clear();
        //Ghost End
        
        //Steel
        list.add(ElementType.ROCK);
        list.add(ElementType.ICE);
        list.add(ElementType.FAIRY);
        strong.put(ElementType.STEEL, list);
        list.clear();
        
        list.add(ElementType.STEEL);
        list.add(ElementType.FIRE);
        list.add(ElementType.WATER);
        list.add(ElementType.ELECTRO);
        weak.put(ElementType.STEEL, list);
        list.clear();
        //Steel End
        
        // Fire
        list.add(ElementType.GRASS);
        list.add(ElementType.ICE);
        list.add(ElementType.BUG);
        list.add(ElementType.STEEL);
        strong.put(ElementType.FIRE, list);
        list.clear();
        
        list.add(ElementType.WATER);
        list.add(ElementType.ROCK);
        list.add(ElementType.FIRE);
        list.add(ElementType.DRAGON);
        weak.put(ElementType.FIRE, list);
        list.clear();
        //Fire End
        
        
        //Water
        list.add(ElementType.GROUND);
        list.add(ElementType.ROCK);
        list.add(ElementType.FIRE);
        strong.put(ElementType.WATER, list);
        list.clear();
        
        list.add(ElementType.WATER);
        list.add(ElementType.GRASS);
        list.add(ElementType.DRAGON);
        weak.put(ElementType.WATER, list);
        list.clear();
        //Water End
        
        //Grass
        list.add(ElementType.GROUND);
        list.add(ElementType.ROCK);
        list.add(ElementType.WATER);
        strong.put(ElementType.GRASS, list);
        list.clear();
        
        list.add(ElementType.FLYING);
        list.add(ElementType.POISON);
        list.add(ElementType.BUG);
        list.add(ElementType.STEEL);
        list.add(ElementType.FIRE);
        list.add(ElementType.GRASS);
        list.add(ElementType.DRAGON);
        weak.put(ElementType.GRASS, list);
        list.clear();
        //Grass End
        
        //Electro
        list.add(ElementType.FLYING);
        list.add(ElementType.WATER);
        strong.put(ElementType.ELECTRO, list);
        list.clear();
        
        list.add(ElementType.GRASS);
        list.add(ElementType.ELECTRO);
        list.add(ElementType.DRAGON);
        weak.put(ElementType.ELECTRO, list);
        list.clear();
        
        list.add(ElementType.GROUND);
        no_effekt.put(ElementType.ELECTRO, list);
        list.clear();
        //Electro End
        
        //Psychic
        list.add(ElementType.FIGHT);
        list.add(ElementType.POISON);
        strong.put(ElementType.PSYCHIC, list);
        list.clear();
        
        list.add(ElementType.STEEL);
        list.add(ElementType.PSYCHIC);
        weak.put(ElementType.PSYCHIC, list);
        list.clear();
        
        list.add(ElementType.DARK);
        no_effekt.put(ElementType.PSYCHIC, list);
        list.clear();
        //Psychic End
        
        //Ice
        list.add(ElementType.FLYING);
        list.add(ElementType.GRASS);
        list.add(ElementType.GROUND);
        list.add(ElementType.DRAGON);
        strong.put(ElementType.ICE, list);
        list.clear();
        
        list.add(ElementType.STEEL);
        list.add(ElementType.FIRE);
        list.add(ElementType.WATER);
        list.add(ElementType.ICE);
        weak.put(ElementType.ICE, list);
        list.clear();
        //Ice End
        
        //Dragon
        list.add(ElementType.DRAGON);
        strong.put(ElementType.DRAGON, list);
        list.clear();
        
        list.add(ElementType.STEEL);
        weak.put(ElementType.DRAGON, list);
        list.clear();
        
        list.add(ElementType.FAIRY);
        no_effekt.put(ElementType.DRAGON, list);
        list.clear();
        //Dragon End
        
        //Dark
        list.add(ElementType.GHOST);
        list.add(ElementType.PSYCHIC);
        strong.put(ElementType.DARK, list);
        list.clear();
        
        list.add(ElementType.FIGHT);
        list.add(ElementType.DARK);
        list.add(ElementType.FAIRY);
        weak.put(ElementType.DARK, list);
        list.clear();
        //Dark End
        
        //Fairy
        list.add(ElementType.FIGHT);
        list.add(ElementType.DRAGON);
        list.add(ElementType.DARK);
        strong.put(ElementType.FAIRY, list);
        list.clear();
        
        list.add(ElementType.POISON);
        list.add(ElementType.STEEL);
        list.add(ElementType.FIRE);
        weak.put(ElementType.FAIRY, list);
        list.clear();
        //Fairy End
    }

}

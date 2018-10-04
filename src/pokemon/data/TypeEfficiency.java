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

        if (no_effekt.get(attack.getElementType()) != null) {
            for (ElementType defendType : no_effekt.get(attack.getElementType())) {
                System.out.println("NO " + defendType);
                if (defendType == defender.getElementType(0)) {
                    return 0;
                }
                if (defendType == defender.getElementType(1)) {
                    return 0;
                }
            }
        }

        if (strong.get(attack.getElementType()) != null) {
            for (ElementType defendType : strong.get(attack.getElementType())) {
                System.out.println("STRONG " + defendType);
                if (defendType == defender.getElementType(0)) {
                    count++;
                }
                if (defendType == defender.getElementType(1)) {
                    count++;
                }
            }
        }

        if (weak.get(attack.getElementType()) != null) {
            for (ElementType defendType : weak.get(attack.getElementType())) {
                System.out.println("WEAK " + defendType);
                if (defendType == defender.getElementType(0)) {
                    count--;
                }
                if (defendType == defender.getElementType(1)) {
                    count--;
                }
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
        for(ElementType type : ElementType.values()){
            no_effekt.put(type, new ArrayList<>());
            weak.put(type, new ArrayList<>());
            strong.put(type, new ArrayList<>());
        }

        //Normal
        weak.get(ElementType.NORMAL).add(ElementType.ROCK);
        weak.get(ElementType.NORMAL).add(ElementType.STEEL);
        
        no_effekt.get(ElementType.NORMAL).add(ElementType.GHOST);

        //Normal End
        //Fight
        strong.get(ElementType.FIGHT).add(ElementType.NORMAL);
        strong.get(ElementType.FIGHT).add(ElementType.ROCK);
        strong.get(ElementType.FIGHT).add(ElementType.STEEL);
        strong.get(ElementType.FIGHT).add(ElementType.ICE);
        strong.get(ElementType.FIGHT).add(ElementType.DARK);

        weak.get(ElementType.FIGHT).add(ElementType.FLYING);
        weak.get(ElementType.FIGHT).add(ElementType.POISON);
        weak.get(ElementType.FIGHT).add(ElementType.BUG);
        weak.get(ElementType.FIGHT).add(ElementType.PSYCHIC);
        weak.get(ElementType.FIGHT).add(ElementType.FAIRY);

        no_effekt.get(ElementType.FIGHT).add(ElementType.GHOST);
        //Fight End

        //Flying
        strong.get(ElementType.FLYING).add(ElementType.FIGHT);
        strong.get(ElementType.FLYING).add(ElementType.BUG);
        strong.get(ElementType.FLYING).add(ElementType.GRASS);

        weak.get(ElementType.FLYING).add(ElementType.STEEL);
        weak.get(ElementType.FLYING).add(ElementType.ELECTRO);
        weak.get(ElementType.FLYING).add(ElementType.ROCK);
        //Flying End

        //Poison
        strong.get(ElementType.POISON).add(ElementType.GRASS);
        strong.get(ElementType.POISON).add(ElementType.FAIRY);

        weak.get(ElementType.POISON).add(ElementType.POISON);
        weak.get(ElementType.POISON).add(ElementType.GROUND);
        weak.get(ElementType.POISON).add(ElementType.ROCK);
        weak.get(ElementType.POISON).add(ElementType.GHOST);

        no_effekt.get(ElementType.POISON).add(ElementType.STEEL);
        //Poison End
//
        //Ground
        strong.get(ElementType.GROUND).add(ElementType.POISON);
        strong.get(ElementType.GROUND).add(ElementType.ROCK);
        strong.get(ElementType.GROUND).add(ElementType.STEEL);
        strong.get(ElementType.GROUND).add(ElementType.FIRE);
        strong.get(ElementType.GROUND).add(ElementType.ELECTRO);

        weak.get(ElementType.GROUND).add(ElementType.BUG);
        weak.get(ElementType.GROUND).add(ElementType.GRASS);

        no_effekt.get(ElementType.GROUND).add(ElementType.FLYING);
        //Ground End

        //Rock
        strong.get(ElementType.ROCK).add(ElementType.FLYING);
        strong.get(ElementType.ROCK).add(ElementType.BUG);
        strong.get(ElementType.ROCK).add(ElementType.FIRE);
        strong.get(ElementType.ROCK).add(ElementType.ICE);

        weak.get(ElementType.ROCK).add(ElementType.FIGHT);
        weak.get(ElementType.ROCK).add(ElementType.GROUND);
        weak.get(ElementType.ROCK).add(ElementType.STEEL);
        //Rock End

        //Bug
        strong.get(ElementType.BUG).add(ElementType.GRASS);
        strong.get(ElementType.BUG).add(ElementType.PSYCHIC);
        strong.get(ElementType.BUG).add(ElementType.DARK);

        weak.get(ElementType.BUG).add(ElementType.FIGHT);
        weak.get(ElementType.BUG).add(ElementType.FLYING);
        weak.get(ElementType.BUG).add(ElementType.POISON);
        weak.get(ElementType.BUG).add(ElementType.GHOST);
        weak.get(ElementType.BUG).add(ElementType.STEEL);
        weak.get(ElementType.BUG).add(ElementType.FIRE);
        weak.get(ElementType.BUG).add(ElementType.FAIRY);
        //Bug End

        //Ghost
        strong.get(ElementType.GHOST).add(ElementType.GHOST);
        strong.get(ElementType.GHOST).add(ElementType.ELECTRO);

        weak.get(ElementType.GHOST).add(ElementType.DARK);

        no_effekt.get(ElementType.GHOST).add(ElementType.NORMAL);
        //Ghost End

        //Steel
        strong.get(ElementType.STEEL).add(ElementType.ROCK);
        strong.get(ElementType.STEEL).add(ElementType.ICE);
        strong.get(ElementType.STEEL).add(ElementType.FAIRY);

        weak.get(ElementType.STEEL).add(ElementType.STEEL);
        weak.get(ElementType.STEEL).add(ElementType.FIRE);
        weak.get(ElementType.STEEL).add(ElementType.WATER);
        weak.get(ElementType.STEEL).add(ElementType.ELECTRO);
        //Steel End

        // Fire
        strong.get(ElementType.FIRE).add(ElementType.GRASS);
        strong.get(ElementType.FIRE).add(ElementType.ICE);
        strong.get(ElementType.FIRE).add(ElementType.BUG);
        strong.get(ElementType.FIRE).add(ElementType.STEEL);

        weak.get(ElementType.FIRE).add(ElementType.WATER);
        weak.get(ElementType.FIRE).add(ElementType.ROCK);
        weak.get(ElementType.FIRE).add(ElementType.FIRE);
        weak.get(ElementType.FIRE).add(ElementType.DRAGON);
        //Fire End

        //Water
        strong.get(ElementType.WATER).add(ElementType.GROUND);
        strong.get(ElementType.WATER).add(ElementType.ROCK);
        strong.get(ElementType.WATER).add(ElementType.FIRE);

        weak.get(ElementType.WATER).add(ElementType.WATER);
        weak.get(ElementType.WATER).add(ElementType.GRASS);
        weak.get(ElementType.WATER).add(ElementType.DRAGON);
        //Water End

        //Grass
        strong.get(ElementType.GRASS).add(ElementType.GROUND);
        strong.get(ElementType.GRASS).add(ElementType.ROCK);
        strong.get(ElementType.GRASS).add(ElementType.WATER);

        weak.get(ElementType.GRASS).add(ElementType.FLYING);
        weak.get(ElementType.GRASS).add(ElementType.POISON);
        weak.get(ElementType.GRASS).add(ElementType.BUG);
        weak.get(ElementType.GRASS).add(ElementType.STEEL);
        weak.get(ElementType.GRASS).add(ElementType.FIRE);
        weak.get(ElementType.GRASS).add(ElementType.GRASS);
        weak.get(ElementType.GRASS).add(ElementType.DRAGON);
        //Grass End

        //Electro
        strong.get(ElementType.ELECTRO).add(ElementType.FLYING);
        strong.get(ElementType.ELECTRO).add(ElementType.WATER);

        weak.get(ElementType.ELECTRO).add(ElementType.GRASS);
        weak.get(ElementType.ELECTRO).add(ElementType.ELECTRO);
        weak.get(ElementType.ELECTRO).add(ElementType.DRAGON);

        no_effekt.get(ElementType.ELECTRO).add(ElementType.GROUND);
        //Electro End

        //Psychic
        strong.get(ElementType.PSYCHIC).add(ElementType.FIGHT);
        strong.get(ElementType.PSYCHIC).add(ElementType.POISON);

        weak.get(ElementType.PSYCHIC).add(ElementType.STEEL);
        weak.get(ElementType.PSYCHIC).add(ElementType.PSYCHIC);

        no_effekt.get(ElementType.PSYCHIC).add(ElementType.DARK);
        //Psychic End

        //Ice
        strong.get(ElementType.ICE).add(ElementType.FLYING);
        strong.get(ElementType.ICE).add(ElementType.GRASS);
        strong.get(ElementType.ICE).add(ElementType.GROUND);
        strong.get(ElementType.ICE).add(ElementType.DRAGON);

        weak.get(ElementType.ICE).add(ElementType.STEEL);
        weak.get(ElementType.ICE).add(ElementType.FIRE);
        weak.get(ElementType.ICE).add(ElementType.WATER);
        weak.get(ElementType.ICE).add(ElementType.ICE);
        //Ice End

        //Dragon
        strong.get(ElementType.DRAGON).add(ElementType.DRAGON);

        weak.get(ElementType.DRAGON).add(ElementType.STEEL);

        no_effekt.get(ElementType.DRAGON).add(ElementType.FAIRY);
        //Dragon End

        //Dark
        strong.get(ElementType.DARK).add(ElementType.GHOST);
        strong.get(ElementType.DARK).add(ElementType.PSYCHIC);

        weak.get(ElementType.DARK).add(ElementType.FIGHT);
        weak.get(ElementType.DARK).add(ElementType.DARK);
        weak.get(ElementType.DARK).add(ElementType.FAIRY);
        //Dark End

        //Fairy
        strong.get(ElementType.FAIRY).add(ElementType.FIGHT);
        strong.get(ElementType.FAIRY).add(ElementType.DRAGON);
        strong.get(ElementType.FAIRY).add(ElementType.DARK);

        weak.get(ElementType.FAIRY).add(ElementType.POISON);
        weak.get(ElementType.FAIRY).add(ElementType.STEEL);
        weak.get(ElementType.FAIRY).add(ElementType.FIRE);
        //Fairy End
    }

}

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
//        //Ground
//        list.add(ElementType.POISON);
//        list.add(ElementType.ROCK);
//        list.add(ElementType.STEEL);
//        list.add(ElementType.FIRE);
//        list.add(ElementType.ELECTRO);
//        strong.put(ElementType.GROUND, list);
//        list.clear();
//
//        list.add(ElementType.BUG);
//        list.add(ElementType.GRASS);
//        weak.put(ElementType.GROUND, list);
//        list.clear();
//
//        list.add(ElementType.FLYING);
//        no_effekt.put(ElementType.GROUND, list);
//        list.clear();
//        //Ground End
//
//        //Rock
//        list.add(ElementType.FLYING);
//        list.add(ElementType.BUG);
//        list.add(ElementType.FIRE);
//        list.add(ElementType.ICE);
//        strong.put(ElementType.ROCK, list);
//        list.clear();
//
//        list.add(ElementType.FIGHT);
//        list.add(ElementType.GROUND);
//        list.add(ElementType.STEEL);
//        weak.put(ElementType.ROCK, list);
//        list.clear();
//        //Rock End
//
//        //Bug
//        list.add(ElementType.GRASS);
//        list.add(ElementType.PSYCHIC);
//        list.add(ElementType.DARK);
//        strong.put(ElementType.BUG, list);
//        list.clear();
//
//        list.add(ElementType.FIGHT);
//        list.add(ElementType.FLYING);
//        list.add(ElementType.POISON);
//        list.add(ElementType.GHOST);
//        list.add(ElementType.STEEL);
//        list.add(ElementType.FIRE);
//        list.add(ElementType.FAIRY);
//        weak.put(ElementType.BUG, list);
//        list.clear();
//        //Bug End
//
//        //Ghost
//        list.add(ElementType.GHOST);
//        list.add(ElementType.ELECTRO);
//        strong.put(ElementType.GHOST, list);
//        list.clear();
//
//        list.add(ElementType.DARK);
//        weak.put(ElementType.GHOST, list);
//        list.clear();
//
//        list.add(ElementType.NORMAL);
//        no_effekt.put(ElementType.GHOST, list);
//        list.clear();
//        //Ghost End
//
//        //Steel
//        list.add(ElementType.ROCK);
//        list.add(ElementType.ICE);
//        list.add(ElementType.FAIRY);
//        strong.put(ElementType.STEEL, list);
//        list.clear();
//
//        list.add(ElementType.STEEL);
//        list.add(ElementType.FIRE);
//        list.add(ElementType.WATER);
//        list.add(ElementType.ELECTRO);
//        weak.put(ElementType.STEEL, list);
//        list.clear();
//        //Steel End
//
//        // Fire
//        list.add(ElementType.GRASS);
//        list.add(ElementType.ICE);
//        list.add(ElementType.BUG);
//        list.add(ElementType.STEEL);
//        strong.put(ElementType.FIRE, list);
//        list.clear();
//
//        list.add(ElementType.WATER);
//        list.add(ElementType.ROCK);
//        list.add(ElementType.FIRE);
//        list.add(ElementType.DRAGON);
//        weak.put(ElementType.FIRE, list);
//        list.clear();
//        //Fire End
//
//        //Water
//        list.add(ElementType.GROUND);
//        list.add(ElementType.ROCK);
//        list.add(ElementType.FIRE);
//        strong.put(ElementType.WATER, list);
//        list.clear();
//
//        list.add(ElementType.WATER);
//        list.add(ElementType.GRASS);
//        list.add(ElementType.DRAGON);
//        weak.put(ElementType.WATER, list);
//        list.clear();
//        //Water End
//
//        //Grass
//        list.add(ElementType.GROUND);
//        list.add(ElementType.ROCK);
//        list.add(ElementType.WATER);
//        strong.put(ElementType.GRASS, list);
//        list.clear();
//
//        list.add(ElementType.FLYING);
//        list.add(ElementType.POISON);
//        list.add(ElementType.BUG);
//        list.add(ElementType.STEEL);
//        list.add(ElementType.FIRE);
//        list.add(ElementType.GRASS);
//        list.add(ElementType.DRAGON);
//        weak.put(ElementType.GRASS, list);
//        list.clear();
//        //Grass End
//
//        //Electro
//        list.add(ElementType.FLYING);
//        list.add(ElementType.WATER);
//        strong.put(ElementType.ELECTRO, list);
//        list.clear();
//
//        list.add(ElementType.GRASS);
//        list.add(ElementType.ELECTRO);
//        list.add(ElementType.DRAGON);
//        weak.put(ElementType.ELECTRO, list);
//        list.clear();
//
//        list.add(ElementType.GROUND);
//        no_effekt.put(ElementType.ELECTRO, list);
//        list.clear();
//        //Electro End
//
//        //Psychic
//        list.add(ElementType.FIGHT);
//        list.add(ElementType.POISON);
//        strong.put(ElementType.PSYCHIC, list);
//        list.clear();
//
//        list.add(ElementType.STEEL);
//        list.add(ElementType.PSYCHIC);
//        weak.put(ElementType.PSYCHIC, list);
//        list.clear();
//
//        list.add(ElementType.DARK);
//        no_effekt.put(ElementType.PSYCHIC, list);
//        list.clear();
//        //Psychic End
//
//        //Ice
//        list.add(ElementType.FLYING);
//        list.add(ElementType.GRASS);
//        list.add(ElementType.GROUND);
//        list.add(ElementType.DRAGON);
//        strong.put(ElementType.ICE, list);
//        list.clear();
//
//        list.add(ElementType.STEEL);
//        list.add(ElementType.FIRE);
//        list.add(ElementType.WATER);
//        list.add(ElementType.ICE);
//        weak.put(ElementType.ICE, list);
//        list.clear();
//        //Ice End
//
//        //Dragon
//        list.add(ElementType.DRAGON);
//        strong.put(ElementType.DRAGON, list);
//        list.clear();
//
//        list.add(ElementType.STEEL);
//        weak.put(ElementType.DRAGON, list);
//        list.clear();
//
//        list.add(ElementType.FAIRY);
//        no_effekt.put(ElementType.DRAGON, list);
//        list.clear();
//        //Dragon End
//
//        //Dark
//        list.add(ElementType.GHOST);
//        list.add(ElementType.PSYCHIC);
//        strong.put(ElementType.DARK, list);
//        list.clear();
//
//        list.add(ElementType.FIGHT);
//        list.add(ElementType.DARK);
//        list.add(ElementType.FAIRY);
//        weak.put(ElementType.DARK, list);
//        list.clear();
//        //Dark End
//
//        //Fairy
//        list.add(ElementType.FIGHT);
//        list.add(ElementType.DRAGON);
//        list.add(ElementType.DARK);
//        strong.put(ElementType.FAIRY, list);
//        list.clear();
//
//        list.add(ElementType.POISON);
//        list.add(ElementType.STEEL);
//        list.add(ElementType.FIRE);
//        weak.put(ElementType.FAIRY, list);
////        list.clear();
//        //Fairy End
    }

}

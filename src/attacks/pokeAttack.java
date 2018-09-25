package attacks;

public class pokeAttack {

    

    private PokeTypes attackType;
    private int baseDMG;
    private PokeTypes defenderTypeOne;
    private PokeTypes defenderTypeTwo;

    private int attackDamage;

    public pokeAttack(PokeTypes attackType, int baseDMG, PokeTypes defenderTypeOne, PokeTypes defenderTypeTwo) {
        this.attackType = attackType;
        this.baseDMG = baseDMG;
        this.defenderTypeOne = defenderTypeOne;
        this.defenderTypeTwo = defenderTypeTwo;
    }

    public void againstTypeOne() {
        
        
    }

}

package pokemon.pokemon;

import java.util.ArrayList;
import pokemon.AttackMoves.BaseAttackMove;

public class BasePokemon{

    protected BaseAttackMove[] attackMoves;
    
    protected ElementType[] elementTypes;
    
    public BasePokemon(){
        attackMoves = new BaseAttackMove[4];
        elementTypes = new ElementType[2];
    }
    
    
    public void adjustHealth() {
        
    }

    public boolean isAlive() {
        return true;
    }

    public void attack() {
        
    }

    public ElementType[] getElementTypes() {
        return elementTypes;
    }
    
    public ElementType getElementType(int index){
        return elementTypes[index];
    }
    
}

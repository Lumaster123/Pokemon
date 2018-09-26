package pokemon.AttackMoves;

import pokemon.pokemon.ElementType;

public class BaseAttackMove {
    
    protected ElementType elementType;
    protected int maxAttackAP;
    protected int attackAP;
    
    
    public BaseAttackMove(){
        
    }

    public ElementType getElementType() {
        return elementType;
    }

    public int getMaxAttackAP() {
        return maxAttackAP;
    }

    public int getAttackAP() {
        return attackAP;
    }

    public void setAttackAP(int attackAP) {
        this.attackAP = attackAP;
    }
    
    
    
    
    
    
    
}

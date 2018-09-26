package pokemon.pokemon;

import java.util.ArrayList;
import pokemon.AttackMoves.BaseAttackMove;

public class BasePokemon{

    protected BaseAttackMove[] attackMoves;
    
    protected ElementType[] elementTypes;
    
    protected int level;
    protected int affection;
    
    
    //Pokemon Values
    protected int kP;
    protected int attackDMG;
    protected int defenseValue;
    protected int init;
    protected int specialDefense;
    protected int specialAtackkDMG;
    
    public BasePokemon(){
        attackMoves = new BaseAttackMove[4];
        elementTypes = new ElementType[2];
    }
    
    // <editor-fold defaultstate="collapsed" desc="Healing">
    
    public void adjustHealth() {
        
    }
    
    public void adjustAttackAP(){
        for(BaseAttackMove atk : attackMoves){
            atk.setAttackAP(atk.getMaxAttackAP());
        }
    }
    
    // </editor-fold>
    
    public void levelUP(){
        
    }

    public boolean isAlive() {
        return true;
    }

    public void attack() {
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getter & Setter">

    public ElementType[] getElementTypes() {
        return elementTypes;
    }
    
    public ElementType getElementType(int index){
        return elementTypes[index];
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getkP() {
        return kP;
    }

    public int getAttackDMG() {
        return attackDMG;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public int getInit() {
        return init;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public int getSpecialAtackkDMG() {
        return specialAtackkDMG;
    }

    public void setkP(int kP) {
        this.kP = kP;
    }

    public void setAttackDMG(int attackDMG) {
        this.attackDMG = attackDMG;
    }

    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }

    public void setInit(int init) {
        this.init = init;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public void setSpecialAtackkDMG(int specialAtackkDMG) {
        this.specialAtackkDMG = specialAtackkDMG;
    }
    
    // </editor-fold>
     
}

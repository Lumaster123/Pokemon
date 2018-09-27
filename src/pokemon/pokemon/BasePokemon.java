package pokemon.pokemon;

import java.util.ArrayList;
import pokemon.AttackMoves.BaseAttackMove;

public class BasePokemon {

    protected BaseAttackMove[] attackMoves;

    protected ElementType[] elementTypes;
    
    protected String name;
    
    protected int level;
    protected double affection;
    protected int health;
    protected int exp;

    //Pokemon Values
    protected int kP;
    protected int attackDMG;
    protected int defenseValue;
    protected int init;
    protected int specialDefense;
    protected int specialAtackkDMG;

    public BasePokemon() {
        attackMoves = new BaseAttackMove[4];
        elementTypes = new ElementType[2];
    }

    // <editor-fold defaultstate="collapsed" desc="Healing">
    public void adjustHealth(int amount) {
        if (isAlive()) {
            if (amount + health >= kP) {
                setHealth(kP);
            } else {
                setHealth(getHealth() + amount);
            }
        }
    }

    public void adjustAttackAP() {
        for (BaseAttackMove atk : attackMoves) {
            atk.setAttackAP(atk.getMaxAttackAP());
        }
    }

    // </editor-fold>
    
    public void levelUP() {

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

    public ElementType getElementType(int index) {
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

    public int getHealth() {
        return health;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getAffection() {
        return affection;
    }

    public void setAffection(double affection) {
        this.affection = affection;
    }

    public int getExp() {
        return exp;
    }

    // </editor-fold>
}

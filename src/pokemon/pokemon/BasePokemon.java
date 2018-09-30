package pokemon.pokemon;

import pokemon.AttackMoves.BaseAttackMove;
import pokemon.data.TypeEfficiency;

public class BasePokemon {

    protected BaseAttackMove[] attackMoves;
    protected ExpType expType;
    protected ElementType[] elementTypes;

    protected String name;

    protected int level;
    protected double affection;
    protected int health;
    protected int exp;
    protected int expNeeded;

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

        levelUPEffect();

    }

    public void leveling() {
        switch (getExpType()) {
            case ERRATIC:
                if (level <= 50) {
                    expNeeded = (int) ((Math.pow(level, 3)) * (100 - level)) / 50;
                } else if (level >= 50 && level <= 68) {
                    expNeeded = (int) ((Math.pow(level, 3)) * (150 - level)) / 100;
                } else if (level >= 68 && level <= 98) {
                    expNeeded = (int) ((Math.pow(level, 3)) * ((1911 - (10 * level)) / 3)) / 500;
                } else if (level >= 98 && level <= 100) {
                    expNeeded = (int) ((Math.pow(level, 3)) * (160 - level)) / 100;
                }
                break;
            case FAST:
                expNeeded = (int) (4 * (Math.pow(level, 3))) / 5;
                break;
            case MEDIUMFAST:
                expNeeded = (int) (Math.pow(level, 3));
                break;
            case MEDIUMSLOW:
                expNeeded = (int) (((double) 6 / 5) * Math.pow(level, 3) - (15 * Math.pow(level, 2)) + (100 * level) - 140);
                break;
            case SLOW:
                expNeeded = (int) (5 * (Math.pow(level, 3))) / 4;
                break;
            case FLUCTUATING:
                if (level <= 15) {
                    expNeeded = (int) ((double) (Math.pow(level, 3)) * (((level + 1) / 3) + 24) / 50);
                } else if (level >= 15 && level <= 36) {
                    expNeeded = (int) ((Math.pow(level, 3)) * ((double) (level + 14) / 50));
                } else if (level >= 36 && level <= 100) {
                    expNeeded = (int) ((double) (Math.pow(level, 3)) * ((level / 2) + 32) / 50);
                }
                break;
            default:
                break;
        }
    }

    public void levelUPEffect() {

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

    public ExpType getExpType() {
        return expType;
    }

    public void setExpType(ExpType expType) {
        this.expType = expType;
    }

    public int getExpNeeded() {
        return expNeeded;
    }

    // </editor-fold>
}

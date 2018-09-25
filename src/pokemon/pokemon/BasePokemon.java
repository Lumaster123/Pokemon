package pokemon.pokemon;

import pokemon.AttackMoves.BaseAttackMove;

public class BasePokemon implements PokemonAbilitys{

    protected BaseAttackMove[] attackMoves;
    
    
    public BasePokemon(){
        attackMoves = new BaseAttackMove[4];
    }
    
    
    @Override
    public void adjustHealth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAlive() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

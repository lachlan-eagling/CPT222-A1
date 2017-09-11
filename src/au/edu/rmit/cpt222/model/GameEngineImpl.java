package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

import java.util.Collection;

public class GameEngineImpl implements GameEngine{

    public GameEngineImpl(int coins){

    }

    public GameEngineImpl(){

    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {

    }

    @Override
    public void addPlayer(Player player) {

    }

    @Override
    public void calculateResult() {

    }

    @Override
    public void flip(int flipDelay, int coinDelay) {

    }

    @Override
    public Collection<Player> getAllPlayers() {
        return null;
    }

    @Override
    public Player getPlayer(String id) {
        return null;
    }

    @Override
    public void placeBet(Player player, Coin.Face face, int bet) throws InsufficientFundsException {

    }

    @Override
    public void removeGameEngineCallback(GameEngineCallback gameEngineCallback) {

    }

    @Override
    public boolean removePlayer(Player player) {
        return false;
    }

    @Override
    public void setPlayerPoints(Player player, int totalPoints) {

    }
}

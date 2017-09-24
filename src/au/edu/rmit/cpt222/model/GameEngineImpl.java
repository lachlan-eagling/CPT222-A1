package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

import java.util.*;

public class GameEngineImpl implements GameEngine{


    public GameEngineImpl(int coins){
        this.coins = coins;
    }

    public GameEngineImpl(){

    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
        this.gameEngineCallback = gameEngineCallback;
    }

    @Override
    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public void calculateResult() {

        for(Player player : players){
            // TODO: Sort by scores and return winner.
        }

    }

    @Override
    public void flip(int flipDelay, int coinDelay) {

        // Get copy of number of coins so can keep reference of original number.
        int coinsToFlip = coins;

        while(coins > 0){

            CoinImpl coin = new CoinImpl(Coin.Face.heads);

            // Generate random number of times to flip current coin.
            int flips = (int) (Math.random() * 100);

            for(int i = 0; i < flips; i++){
                try{
                    Thread.sleep(coinDelay);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                coin.swapFace();
                gameEngineCallback.coinFlip(coin.getCurrentFace(), this);
            }
            gameEngineCallback.coinFlipOutcome(coins - coinsToFlip + 1, coin.getCurrentFace(), this);
            coinsToFlip--;
            try{
                Thread.sleep(flipDelay);
            } catch(InterruptedException e){
                e.printStackTrace();
            }

        }

    }

    @Override
    public Collection<Player> getAllPlayers() {
        return Collections.unmodifiableList(players);
    }

    @Override
    public Player getPlayer(String id) {

        Player _player = null;

        for(Player player : players){
            if(player.getPlayerId().equalsIgnoreCase(id)){
                _player = player;
            }
        }

        return _player;
    }

    @Override
    public void placeBet(Player player, Coin.Face face, int bet) throws InsufficientFundsException {
        // TODO: Implement placeBet() method.
    }

    @Override
    public void removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
        // TODO: Implement removeGameEngineCallback() method.
    }

    @Override
    public boolean removePlayer(Player player) {

        if(players.contains(player)){
            players.remove(player);
            return true;
        } else{
            return false;
        }

    }

    @Override
    public void setPlayerPoints(Player player, int totalPoints) {

        // TODO: Add check that player exists.
        player.setPoints(totalPoints);

    }
}

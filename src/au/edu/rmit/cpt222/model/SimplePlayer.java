package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class SimplePlayer implements Player{

    String playerNumber;
    String name;
    int creditPoints;

    public SimplePlayer(){

    }

    public SimplePlayer(String playerNumber, String name, int creditPoints){
        this.playerNumber = playerNumber;
        this.name = name;
        this.creditPoints = creditPoints;
    }

    @Override
    public int getBet() {
        return 0;
    }

    @Override
    public Coin.Face getFacePick() {
        return null;
    }

    @Override
    public String getPlayerId() {
        return null;
    }

    @Override
    public String getPlayerName() {
        return null;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public GameEngine.GameStatus getResult() {
        return null;
    }

    @Override
    public void placeBet(Coin.Face facePick, int bet) throws InsufficientFundsException {

    }

    @Override
    public void setPlayerName(String playerName) {

    }

    @Override
    public void setPoints(int points) {

    }

    @Override
    public void setResult(GameEngine.GameStatus status) {

    }
}

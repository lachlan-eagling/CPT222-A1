package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class SimplePlayer implements Player{

    private String playerNumber;
    private String name;
    private int creditPoints;
    private Bet currentBet;

    public SimplePlayer(){

    }

    public SimplePlayer(String playerNumber, String name, int creditPoints){
        this.playerNumber = playerNumber;
        this.name = name;
        this.creditPoints = creditPoints;
        this.currentBet = new Bet();
    }

    @Override
    public int getBet() {
        return currentBet.points;
    }


    @Override
    public Coin.Face getFacePick() {
        return currentBet.face;
    }

    @Override
    public String getPlayerId() {
        return playerNumber;
    }

    @Override
    public String getPlayerName() {
        return name;
    }

    @Override
    public int getPoints() {
        return creditPoints;
    }

    @Override
    public GameEngine.GameStatus getResult() {
        return currentBet.result;
    }

    @Override
    public void placeBet(Coin.Face facePick, int bet) throws InsufficientFundsException {
        if(bet <= creditPoints){
            this.currentBet = new Bet(facePick, bet);
        } else{
            throw new InsufficientFundsException();
        }
    }

    @Override
    public void setPlayerName(String playerName) {
        this.name = playerName;
    }

    @Override
    public void setPoints(int points) {
        this.creditPoints = points;
    }

    @Override
    public void setResult(GameEngine.GameStatus status) {
        this.currentBet.result = status;
    }
}

class Bet{

    Coin.Face face;
    int points;
    GameEngine.GameStatus result;

     Bet(){
        // Set default values.
        this.face = Coin.Face.heads;
        this.points = 0;
        this.result = GameEngine.GameStatus.LOST;
    }

    Bet(Coin.Face face, int points){
        if(face != null && points > 0){
            this.face = face;
            this.points = points;
        } else{
            throw new AssertionError("Invalid bet data...");
        }

    }

    Coin.Face getFace(){ return face; }

}
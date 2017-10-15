package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;

/*
* Class to encapsulate game history.
* */

public class Game {

    private Player player;
    private Coin.Face betCoin;
    private int betAmount;
    private GameEngine.GameStatus gameResult;

    public Game(Player player, Coin.Face betCoin, int betAmount, GameEngine.GameStatus gameResult){
        this.player = player;
        this.betCoin = betCoin;
        this.betAmount = betAmount;
        this.gameResult = gameResult;
    }

    public GameEngine.GameStatus getGameResult() {
        return gameResult;
    }

    public Player getPlayer() {
        return player;
    }

    public Coin.Face getBetCoin() {
        return betCoin;
    }

    public int getBetAmount() {
        return betAmount;
    }

    @Override
    public String toString() {
        String out = "Name: " + player.getPlayerName()
                + "Bet Coin: " + betCoin
                + "Bet Amount:" + betAmount
                +"\n";
        return out;
    }
}

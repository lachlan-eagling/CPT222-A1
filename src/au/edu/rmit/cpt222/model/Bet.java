package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;


public class Bet{

    private Coin.Face face;
    private int points;
    private int coinsToFlip;
    private GameEngine.GameStatus result;

    public Bet(){

        this.points = 0;
        this.result = null;

    }

    public Bet(Coin.Face face, int points){

        this.face = face;
        this.points = points;

    }

    public Bet(Coin.Face face, int points, int coinsToFlip){

        this.face = face;
        this.points = points;
        this.coinsToFlip = coinsToFlip;

    }



    public Coin.Face getFace(){ return face; }

    public int getPoints() {
        return points;
    }

    public int getCoinsToFlip() {
        return coinsToFlip;
    }

    public GameEngine.GameStatus getResult() {
        return result;
    }

    public void setFace(Coin.Face face) {
        this.face = face;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setCoinsToFlip(int coinsToFlip) {
        this.coinsToFlip = coinsToFlip;
    }

    public void setResult(GameEngine.GameStatus result) {
        this.result = result;
    }
}
package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;


public class Bet{

    Coin.Face face;
    int points;
    int coinsToFlip;
    GameEngine.GameStatus result;

    public Bet(){
        // Set default values.
        //this.face = Coin.Face.heads;
        this.points = 0;
        this.result = GameEngine.GameStatus.LOST;
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
}
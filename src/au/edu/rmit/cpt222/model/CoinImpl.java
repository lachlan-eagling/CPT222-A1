package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.interfaces.Coin;

public class CoinImpl implements Coin{


    private Coin.Face currentFace;

    public CoinImpl(Coin.Face currentFace){
        this.currentFace = currentFace;

    }

    @Override
    public Face getCurrentFace() {
        return currentFace;
    }

    @Override
    public void setCurrentFace(Face currentFace) {
        this.currentFace = currentFace;
    }

    @Override
    public void swapFace() {
        currentFace = currentFace == Face.heads ? Face.tails : Face.heads;
    }
}

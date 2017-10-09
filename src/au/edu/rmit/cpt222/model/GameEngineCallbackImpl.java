package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.test.harness.TestHarness;

public class GameEngineCallbackImpl implements GameEngineCallback{

    public GameEngineCallbackImpl(){

    }

    @Override
    public void coinFlip(Coin.Face coinFace, GameEngine engine) {

    }

    @Override
    public void coinFlipOutcome(int coinNumber, Coin.Face coinFace, GameEngine engine) {

    }

    @Override
    public void gameResult(Player player, GameEngine.GameStatus result, GameEngine engine) {

    }
}

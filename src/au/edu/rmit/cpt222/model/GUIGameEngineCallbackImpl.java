package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;

import javax.swing.*;

public class GUIGameEngineCallbackImpl extends GameEngineCallbackImpl {

    public GUIGameEngineCallbackImpl() {
        super();
    }

    //TODO: Change method implementations.

    @Override
    public void coinFlip(Coin.Face coinFace, GameEngine engine) {
        //super.coinFlip(coinFace, engine);
        GameEngineImpl _engine = (GameEngineImpl) engine;
        _engine.getController().updateCoinLabel();

    }

    @Override
    public void coinFlipOutcome(int coinNumber, Coin.Face coinFace, GameEngine engine) {
        //super.coinFlipOutcome(coinNumber, coinFace, engine);
        GameEngineImpl _engine = (GameEngineImpl) engine;
        _engine.getController().updateLastCoinFlip(coinFace);
    }

    @Override
    public void gameResult(Player player, GameEngine.GameStatus result, GameEngine engine) {
        //super.gameResult(player, result, engine);
        GameEngineImpl _engine = (GameEngineImpl) engine;
        _engine.getController().updateGameOutcome(player, result);
    }

    public void clearResults() {

    }


}

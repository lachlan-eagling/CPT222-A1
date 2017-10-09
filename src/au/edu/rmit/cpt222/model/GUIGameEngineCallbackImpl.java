package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.controller.MainWindowController;
import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.test.harness.TestHarness;

public class GUIGameEngineCallbackImpl extends GameEngineCallbackImpl {

    public GUIGameEngineCallbackImpl() {
        super();
    }

    //TODO: Change method implementations.

    @Override
    public void coinFlip(Coin.Face coinFace, GameEngine engine) {
        GameEngineImpl _engine = (GameEngineImpl) engine;
        _engine.getController().updateCoinLabel();
        super.coinFlip(coinFace, engine);
    }

    @Override
    public void coinFlipOutcome(int coinNumber, Coin.Face coinFace, GameEngine engine) {
        super.coinFlipOutcome(coinNumber, coinFace, engine);
    }

    @Override
    public void gameResult(Player player, GameEngine.GameStatus result, GameEngine engine) {
        super.gameResult(player, result, engine);
    }

    public void clearResults() {

    }


}

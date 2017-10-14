package au.edu.rmit.cpt222.controller;

import au.edu.rmit.cpt222.model.Bet;
import au.edu.rmit.cpt222.model.GUIGameEngineCallbackImpl;
import au.edu.rmit.cpt222.model.Game;
import au.edu.rmit.cpt222.model.GameEngineImpl;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.*;
import au.edu.rmit.cpt222.view.AddPlayerDialog;
import au.edu.rmit.cpt222.view.GameHistoryWindow;
import au.edu.rmit.cpt222.view.GameWindow;
import au.edu.rmit.cpt222.view.PlaceBetDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameControllerImpl implements GameController{

    private GameWindow gameWindow;
    private GameEngine engine;
    private static GameController controller;
    private Player player;

    private Bet bet;

    public final static int DEFAULT_FLIP_DELAY = 300;
    public final static int DEFAULT_COIN_DELAY = 500;

    public GameControllerImpl(){
        this.engine = new GameEngineImpl();
        engine.addGameEngineCallback(new GUIGameEngineCallbackImpl());
        GameEngineImpl _engine = (GameEngineImpl) engine;
        _engine.setController(this);

        gameWindow = new GameWindow(this);
        gameWindow.displayWindow();
    }

    public static void main(String[] args) {
        new GameControllerImpl();
    }

    @Override
    public Player addPlayer() {
        AddPlayerDialog addPlayerDialog = new AddPlayerDialog(gameWindow.getWindowContentFrame());
        addPlayerDialog.setVisible(true);
        Player _player = addPlayerDialog.getAddPlayerResult();
        if(_player != null){
            engine.addPlayer(addPlayerDialog.getAddPlayerResult());
            this.player = _player;
            return _player;
        }
        return null;
    }

    @Override
    public void editPlayer() {
        // TODO: Implement editPlayer event handler.
    }

    @Override
    public void addBet() {
        PlaceBetDialog placeBetDialog = new PlaceBetDialog(gameWindow.getWindowContentFrame());
        placeBetDialog.setVisible(true);
        bet = placeBetDialog.getNewBetResult();
        try{
            engine.getPlayer(player.getPlayerId()).placeBet(bet.getFace(), bet.getPoints());
        } catch (InsufficientFundsException e){
            displayError(e);
        }
    }

    @Override
    public void spinCoin() {
        // TODO: Implement spinCoin eventHandler.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                engine.flip(DEFAULT_FLIP_DELAY, DEFAULT_COIN_DELAY);
            }
        });
        thread.run();
    }

    @Override
    public void updatePlayer() {
        // TODO: Implement method to update player details. This should update the current player details in UI.
    }

    @Override
    public void updateLastCoinFlip(Coin.Face coinFace) {
        gameWindow.updateCoinOutcome(coinFace);
    }

    @Override
    public void updateGameOutcome(Player player, GameEngine.GameStatus result) {
        String gameResult = result.toString();
        String betCoin = player.getFacePick().toString();
        String betCredits = String.valueOf(bet.getPoints());
        String updatedCredits = String.valueOf(player.getPoints());
        gameWindow.updateGameResult(gameResult, betCoin, betCredits, updatedCredits);
    }

    public void updateCoinLabel(){
        gameWindow.swapCoinFace();
    }

    @Override
    public void showGameHistory() {
        ArrayList<Game> games = new ArrayList<>();
        if(engine instanceof GameHistory){
            GameHistory historyEngine = (GameHistory) engine;
            games.addAll(historyEngine.getAllGames());
        }

        GameHistoryWindow gameHistoryWindow = new GameHistoryWindow(games);

        // Test output to console
        for(Game game : games){
            System.out.println(game.toString());
        }
    }

    @Override
    public void displayError(Exception exception) {
        String error = exception.getMessage();
        String title = exception.getClass().getSimpleName();
        JOptionPane.showMessageDialog(gameWindow.getWindowContentFrame(), error, title, JOptionPane.ERROR_MESSAGE);
    }
}

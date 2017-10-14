package au.edu.rmit.cpt222.controller;

import au.edu.rmit.cpt222.model.Bet;
import au.edu.rmit.cpt222.model.Game;
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

    private Bet bet;

    public GameControllerImpl(GameEngine engine){
        this.engine = engine;
        gameWindow = new GameWindow(this);
        gameWindow.displayWindow();
    }

    @Override
    public Player addPlayer() {
        AddPlayerDialog addPlayerDialog = new AddPlayerDialog(gameWindow.getWindowContentFrame());
        addPlayerDialog.setVisible(true);
        Player _player = addPlayerDialog.getAddPlayerResult();
        if(_player != null){
            engine.addPlayer(addPlayerDialog.getAddPlayerResult());
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
    }

    @Override
    public void spinCoin() {
        // TODO: Implement spinCoin eventHandler.
    }

    @Override
    public void updatePlayer() {
        // TODO: Implement method to update player details. This should update the current player details in UI.
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

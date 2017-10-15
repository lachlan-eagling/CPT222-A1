package au.edu.rmit.cpt222.driver;

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
import java.util.ArrayList;

public class Ass1Driver implements GameController{

    private GameWindow gameWindow;
    private GameEngine engine;
    private Player player;

    private Bet bet;

    private final static int DEFAULT_FLIP_DELAY = 300;
    private final static int DEFAULT_COIN_DELAY = 500;

    private Ass1Driver(){

        // Add GameEngine and GameEngineCallback
        this.engine = new GameEngineImpl();
        engine.addGameEngineCallback(new GUIGameEngineCallbackImpl());
        GameEngineImpl _engine = (GameEngineImpl) engine;
        _engine.setController(this);

        gameWindow = new GameWindow(this);
        gameWindow.displayWindow();

    }

    public static void main(String[] args) {

        // Initialise and start game window.
        new Ass1Driver();

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

        String message = "Functionality yet to be implemented.";
        String title = "Information";
        JOptionPane.showMessageDialog(gameWindow.getWindowContentFrame(),
                message, title,
                JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    public void addBet() {

        PlaceBetDialog placeBetDialog = new PlaceBetDialog(gameWindow.getWindowContentFrame());;
        if(player != null){
            placeBetDialog.setVisible(true);

            // Result from placeBetDialog can be null if cancel is pressed.
            if(placeBetDialog.getNewBetResult() != null){
                bet = placeBetDialog.getNewBetResult();
                try{
                    engine.getPlayer(player.getPlayerId()).placeBet(bet.getFace(), bet.getPoints());
                } catch (InsufficientFundsException e){
                    displayError(e);
                }
            }

        } else{
            // If no players cannot add a bet.
            displayError(new IllegalStateException("No players added, please add a player first."));
        }

    }

    @Override
    public void spinCoin() {

        new Thread(){
            @Override
            public void run(){
            	
            	// Check that bet has been placed to avoid NullPointerException.
            	if(bet != null) {
            		GameEngineImpl _engine;
                    if(engine instanceof GameEngineImpl){
                        _engine = (GameEngineImpl) engine;
                        _engine.setNumOfCoins(bet.getCoinsToFlip());
                    }
                    engine.flip(DEFAULT_FLIP_DELAY, DEFAULT_COIN_DELAY);
            	} else {
            		displayError(new IllegalStateException("No bets, place a bet first."));
            	}
                
            }
        }.start();

    }

    @Override
    public void updateLastCoinFlip(int coinNumber, Coin.Face coinFace) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameWindow.updateCoinOutcome(coinNumber, coinFace);
            }
        });

    }

    @Override
    public void updateGameOutcome(Player player, GameEngine.GameStatus result) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String gameResult = result.toString();
                String betCoin = player.getFacePick().toString();
                String betCredits = String.valueOf(bet.getPoints());
                String updatedCredits = String.valueOf(player.getPoints());
                gameWindow.updateGameResult(gameResult, betCoin, betCredits, updatedCredits);
            }
        });

    }

    public void updateCoinLabel(){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameWindow.swapCoinFace();
            }
        });

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

        // Get details of exception and display in message dialog.
        String error = exception.getMessage();
        String title = exception.getClass().getSimpleName();
        JOptionPane.showMessageDialog(gameWindow.getWindowContentFrame(), error, title, JOptionPane.ERROR_MESSAGE);

    }

}

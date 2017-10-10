package au.edu.rmit.cpt222.model.controller;

import au.edu.rmit.cpt222.model.interfaces.GameController;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.view.MainWindow;

public class GameControllerImpl implements GameController{

    private MainWindow mainWindow;
    private GameEngine engine;

    public GameControllerImpl(GameEngine engine){
        this.engine = engine;
        mainWindow = new MainWindow();
        mainWindow.displayWindow();
    }

    @Override
    public void newGame() {
        // TODO: Implement newGame event handler.
    }

    @Override
    public Player addPlayer() {
        // TODO: Implement addPlayer event handler.
        return null;
    }

    @Override
    public void editPlayer() {
        // TODO: Implement editPlayer event handler.
    }

    @Override
    public void addBet() {
        // TODO: Implement method to add a new bet.
    }

    @Override
    public void spinCoin() {
        // TODO: Implement spinCoin eventHandler.
    }

    @Override
    public void updatePlayer() {
        // TODO: Implement method to update player details.
    }

    @Override
    public void updateGameOutcome() {
        // TODO: Implement method to update game outcome.
    }

    public void updateCoinLabel(){
        mainWindow.swapCoinFace();
    }

    @Override
    public void showGameHistory() {
        // TODO: Implement method to display game history.
    }

    @Override
    public void displayError() {
        // TODO: Implement method to display error dialog for exceptions.
    }
}

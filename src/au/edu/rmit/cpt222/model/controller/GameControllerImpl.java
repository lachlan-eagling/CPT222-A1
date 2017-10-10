package au.edu.rmit.cpt222.model.controller;

import au.edu.rmit.cpt222.model.interfaces.GameController;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.view.AddPlayerDialog;
import au.edu.rmit.cpt222.view.GameWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameControllerImpl implements GameController{

    private GameWindow gameWindow;
    private GameEngine engine;

    public GameControllerImpl(GameEngine engine){
        this.engine = engine;
        gameWindow = new GameWindow(this);
        gameWindow.displayWindow();
    }

    @Override
    public void newGame() {
        // TODO: Implement newGame event handler.
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
        gameWindow.swapCoinFace();
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

class AddPlayerListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

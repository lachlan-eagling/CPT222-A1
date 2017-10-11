package au.edu.rmit.cpt222.model.interfaces;

public interface GameController {

    void newGame();
    Player addPlayer();
    void editPlayer();
    void addBet();

    void spinCoin();
    void updatePlayer();
    void updateGameOutcome();
    void updateCoinLabel();

    void showGameHistory();
    void displayError(Exception error);
}

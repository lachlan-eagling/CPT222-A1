package au.edu.rmit.cpt222.model.interfaces;

public interface GameController {

    Player addPlayer();
    void editPlayer();
    void addBet();
    void spinCoin();
    void updateGameOutcome(Player player, GameEngine.GameStatus result);
    void updateCoinLabel();
    void updateLastCoinFlip(int coinNumber, Coin.Face coinFace);
    void showGameHistory();
    void displayError(Exception error);
}

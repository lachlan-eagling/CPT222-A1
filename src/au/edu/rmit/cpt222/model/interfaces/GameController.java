package au.edu.rmit.cpt222.model.interfaces;

public interface GameController {

    Player addPlayer();
    void editPlayer();
    void addBet();

    void spinCoin();
    void updatePlayer();
    void updateGameOutcome(Player player, GameEngine.GameStatus result);
    void updateCoinLabel();
    void updateLastCoinFlip(Coin.Face coinFace);

    void showGameHistory();
    void displayError(Exception error);
}

package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.*;



import java.util.*;

public class GameEngineImpl implements GameEngine, GameHistory{

    private static final int NUM_OF_COINS = 2;
    private int coins;
    private GameEngineCallback gameEngineCallback;
    private List<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private GameController controller;
    private List<BetOutcome> results = new ArrayList<>();
    private Collection<Game> gameHistory = new ArrayList<>();


    public GameEngineImpl(int coins){ this.coins = coins; }

    public GameEngineImpl(){ }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {

        this.gameEngineCallback = gameEngineCallback;

    }

    @Override
    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public void calculateResult() {

        int numberOfWins = 0;
        int numberOfLoss = 0;
        GameStatus finalOutcome;

        // Check flip outcomes.
        for(BetOutcome result : results){
            if (result.getResult().equals(GameStatus.WON)){
                numberOfWins++;
            }else{
                numberOfLoss++;
            }
        }

        // Check number of wins vs losses.
        if(numberOfWins > numberOfLoss){
            finalOutcome = GameStatus.WON;
        } else if(numberOfWins < numberOfLoss){
            finalOutcome = GameStatus.LOST;
        } else{
            finalOutcome = GameStatus.DREW;
        }

        // Apply points changes based on game outcome.
        switch(finalOutcome){
            case WON:
                currentPlayer.setPoints(currentPlayer.getPoints() + currentPlayer.getBet());
                break;
            case LOST:
                currentPlayer.setPoints(currentPlayer.getPoints() - currentPlayer.getBet());
                break;
            case DREW:
                break;
        }

        // Add game to history and update UI.
        addGameToHistory(new Game(currentPlayer, currentPlayer.getFacePick(), currentPlayer.getBet(), finalOutcome));
        gameEngineCallback.gameResult(currentPlayer, finalOutcome, this);
    }

    @Override
    public void flip(int flipDelay, int coinDelay) {

        for(Player player : players){

            currentPlayer = player;
            Coin.Face betFace = player.getFacePick();

            // Get copy of number of coins so can keep reference of original number.
            int coinsToFlip = coins == 0 ? NUM_OF_COINS : coins;

            while(coinsToFlip > 0){

                // Initialise coin and calculate current coin number.
                CoinImpl coin = new CoinImpl(getRandomCoinFace());
                int currentCoin = (coins == 0 ? NUM_OF_COINS : coins) - coinsToFlip;

                // Store flip result here.
                GameEngine.GameStatus result;

                // Generate random number of times to flip current coin.
                int flips = (int) (Math.random() * 15);

                // Perform coin "flips".
                for(int i = 0; i < flips - 1; i++){

                    try{
                        Thread.sleep(flipDelay);
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    // Swap current face and update UI.
                    coin.swapFace();
                    gameEngineCallback.coinFlip(coin.getCurrentFace(), this);

                }

                // Set flip result.
                if(coin.getCurrentFace().equals(betFace)){
                    result = GameStatus.WON;
                } else{
                    result = GameStatus.LOST;
                }

                coinsToFlip--;

                // Pause flip execution between coins.
                try{
                    Thread.sleep(coinDelay);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }

                // Add flip to overall game results and update UI.
                results.add(new BetOutcome(result));
                gameEngineCallback.coinFlipOutcome(currentCoin + 1, coin.getCurrentFace(), this);

            }

            calculateResult();

        }

    }

    @Override
    public Collection<Player> getAllPlayers() {
        return Collections.unmodifiableList(players);
    }

    @Override
    public Player getPlayer(String id) {

        Player _player = null;

        for(Player player : players){
            if(player.getPlayerId().equalsIgnoreCase(id)){
                _player = player;
            }
        }

        return _player;
    }

    @Override
    public void placeBet(Player player, Coin.Face face, int bet) throws InsufficientFundsException {
        player.placeBet(face, bet);
    }

    @Override
    public void removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
        // Method implementation not required for assignment one.
    }

    @Override
    public boolean removePlayer(Player player) {

        if(players.contains(player)){
            players.remove(player);
            return true;
        } else{
            return false;
        }

    }

    @Override
    public void setPlayerPoints(Player player, int totalPoints) {

        if(players.contains(player)){
            player.setPoints(totalPoints);
        }

    }

    public void setController(GameController controller){
        this.controller = controller;
    }

    public GameController getController() {
        return controller;
    }

    @Override
    public void addGameToHistory(Game game) {
        gameHistory.add(game);
    }

    @Override
    public Collection<Game> getAllGames() {
        return gameHistory;
    }

    public void setNumOfCoins(int coins){
        this.coins = coins;
    }

    // Choose a random coin face.
    private Coin.Face getRandomCoinFace(){
        int rand = new Random().nextInt(Coin.Face.values().length);
        return Coin.Face.values()[rand];
    }
}

// Inner class to encapsulate coin flip result.
class BetOutcome{

    private GameEngine.GameStatus result;

    BetOutcome(GameEngine.GameStatus result){
        this.result = result;
    }

    public GameEngine.GameStatus getResult() {
        return result;
    }

}

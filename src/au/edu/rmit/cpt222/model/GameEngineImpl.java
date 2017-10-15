package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.controller.GameControllerImpl;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.*;

import static org.assertj.core.api.Assertions.*;


import java.util.*;

public class GameEngineImpl implements GameEngine, GameHistory{

    private static final int NUM_OF_COINS = 2;
    private int coins;
    private GameEngineCallback gameEngineCallback;
    private List<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private GameController controller;
    private List<BetOutcome> results = new ArrayList<>();

    Collection<Game> gameHistory = new ArrayList<>();


    public GameEngineImpl(int coins){
        assertThat(coins).isGreaterThanOrEqualTo(2);
        //controller = new GameControllerImpl();
        this.coins = coins;
    }

    public GameEngineImpl(){
        //controller = new GameControllerImpl();
    }

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
        int numberOfFlips = results.size();
        int numberOfWins = 0;
        int numberOfLoss = 0;
        GameStatus finalOutcome;

        for(BetOutcome result : results){
            if (result.getResult().equals(GameStatus.WON)){
                numberOfWins++;
            }else{
                numberOfLoss++;
            }
        }

        if(numberOfWins > numberOfLoss){
            finalOutcome = GameStatus.WON;
        } else if(numberOfWins < numberOfLoss){
            finalOutcome = GameStatus.LOST;
        } else{
            finalOutcome = GameStatus.DREW;
        }

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
        addGameToHistory(new Game(currentPlayer, currentPlayer.getFacePick(), currentPlayer.getBet(), finalOutcome));
        gameEngineCallback.gameResult(currentPlayer, finalOutcome, this);
    }

    @Override
    public void flip(int flipDelay, int coinDelay) {

        assertThat(flipDelay).isGreaterThanOrEqualTo(0);
        assertThat(coinDelay).isGreaterThanOrEqualTo(0);

        // Loop over players.
        for(Player player : players){
            currentPlayer = player;
            Coin.Face betFace = player.getFacePick();

            // Get copy of number of coins so can keep reference of original number.
            int coinsToFlip = coins == 0 ? NUM_OF_COINS : coins;
            int wins = 0;

            System.out.println(coinsToFlip);
            while(coinsToFlip > 0){

                CoinImpl coin = new CoinImpl(getRandomCoinFace());
                int currentCoin = (coins == 0 ? NUM_OF_COINS : coins) - coinsToFlip;
                GameEngine.GameStatus result;

                // Generate random number of times to flip current coin.
                int flips = (int) (Math.random() * 15);

                for(int i = 0; i < flips - 1; i++){
                    try{
                        Thread.sleep(flipDelay);
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    coin.swapFace();
                    gameEngineCallback.coinFlip(coin.getCurrentFace(), this);
                }
//                System.out.println("CHECKPOINT...");

                if(coin.getCurrentFace().equals(betFace)){
                    result = GameStatus.WON;
                    wins++;
                } else{
                    result = GameStatus.LOST;
                }

                coinsToFlip--;

                try{
                    Thread.sleep(coinDelay);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }

                results.add(new BetOutcome(result));
                gameEngineCallback.coinFlipOutcome(currentCoin + 1, coin.getCurrentFace(), this);

            }

//            if(wins > coinsToFlip / 2){
//                player.setResult(GameStatus.WON);
//            } else if(wins < coinsToFlip / 2){
//                player.setResult(GameStatus.LOST);
//            } else{
//                player.setResult(GameStatus.DREW);
//            }

            wins = 0;
            // TODO: Move add to history into calcultateResult method.

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

    private Coin.Face getRandomCoinFace(){
        int rand = new Random().nextInt(Coin.Face.values().length);
        return Coin.Face.values()[rand];
    }
}
class BetOutcome{
    private GameEngine.GameStatus result;

    BetOutcome(GameEngine.GameStatus result){
        this.result = result;
    }

    public GameEngine.GameStatus getResult() {
        return result;
    }
}

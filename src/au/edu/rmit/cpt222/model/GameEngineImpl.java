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
        switch(currentPlayer.getResult()){
            case WON:
                currentPlayer.setPoints(currentPlayer.getPoints() + currentPlayer.getBet());
                break;
            case LOST:
                currentPlayer.setPoints(currentPlayer.getPoints() - currentPlayer.getBet());
                break;
            case DREW:
                break;
        }

        gameEngineCallback.gameResult(currentPlayer, currentPlayer.getResult(), this);
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

            while(coinsToFlip > 0){

                CoinImpl coin = new CoinImpl(Coin.Face.heads);
                int currentCoin = (coins == 0 ? NUM_OF_COINS : coins) - coinsToFlip;

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
                    wins++;
                }

                coinsToFlip--;

                try{
                    Thread.sleep(coinDelay);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }

                gameEngineCallback.coinFlipOutcome(currentCoin, coin.getCurrentFace(), this);

            }

            if(wins > coinsToFlip){
                player.setResult(GameStatus.WON);
            } else if(wins < coinsToFlip){
                player.setResult(GameStatus.LOST);
            } else{
                player.setResult(GameStatus.DREW);
            }

            addGameToHistory(new Game(player, betFace, player.getBet(), player.getResult()));
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
}

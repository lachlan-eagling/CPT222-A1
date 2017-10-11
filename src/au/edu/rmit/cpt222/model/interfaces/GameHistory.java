package au.edu.rmit.cpt222.model.interfaces;

import au.edu.rmit.cpt222.model.Game;

import java.util.Collection;

public interface GameHistory extends GameEngine{

    void addGameToHistory(Game game);
    Collection<Game> getAllGames();

}

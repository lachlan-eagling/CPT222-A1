package au.edu.rmit.cpt222.view;

import au.edu.rmit.cpt222.model.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameHistoryWindow {

    // Setup arrays for data and column headings.
    private String[] columns = {"Player Name", "Bet Coin", "Bet Amount", "Game Result"};
    private Object[][] data = new Object[20][4];

    private JTable table;
    private JFrame frame;
    private Container container;

    public GameHistoryWindow(ArrayList<Game> games){

        // Populate data array.
        gameAdapter(games);

        // Setup Window and table.
        table = new JTable(data, columns);
        frame = new JFrame("Game History");
        container = frame.getContentPane();
        container.add(new JScrollPane(table));
        frame.setPreferredSize(new Dimension(600, 400));
        table.setFillsViewportHeight(true);

        // Show window.
        frame.pack();
        frame.setVisible(true);
    }


    private void gameAdapter(ArrayList<Game> games){

        for(int i = 0; i < games.size(); i++){

            // Only need to display history for 20 games.
            Game game = games.get(i);
            if(i < 20){

                // Populate data array from each game field.
                data[i][0] = game.getPlayer().getPlayerName();
                data[i][1] = game.getBetCoin().toString();
                data[i][2] = game.getBetAmount();
                data[i][3] = game.getGameResult();

            }
        }
    }

}

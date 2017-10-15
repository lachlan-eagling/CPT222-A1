package au.edu.rmit.cpt222.view;

import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow{

    private static final String LABEL_HEADS = "Heads";
    private static final String LABEL_TAILS = "Tails";

    private GameController controller;

    // Declare window components
    private JFrame frame;
    private LayoutManager layoutManager;
    private Dimension dimens;
    private Container container;

    // Menu Components
    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem placeBetMenuItem;
    private JMenuItem playerMenuItem;
    private JMenuItem addPlayerMenuItem;
    private JMenuItem editPlayerMenuItem;
    private JMenuItem viewGameHistoryMenuItem;

    // Button components.
    private JPanel buttonContainer;
    private LayoutManager buttonLayout;
    private JButton placeBetButton;
    private JButton addPlayerButton;
    private JButton flipCoinButton;

    // Coin components
    private JLabel coinLabel;

    // Game outcome components
    private JPanel gameOutcomeContainer;
    private LayoutManager gameOutcomeLayout;

    private JLabel lineBreak;
    private JLabel lblLastCoinFlip;
    private JLabel lastCoinFlip;

    private JLabel lblGameResult;
    private JLabel lblBetCoin;
    private JLabel lblBetAmmount;
    private JLabel lblUpdatedPoints;

    private JLabel gameResultData;
    private JLabel betCoinData;
    private JLabel betAmountData;
    private JLabel updatedPointsData;

    public GameWindow(GameController controller){

        this.controller = controller;
        setupWindowAndComponents();

    }

    private void setupWindowAndComponents(){

        // Split window creation into separate methods to improve cohesion of class.
        setUpWindow();
        setupMenu();
        setupButtons();
        setupCoinView();
        setupGameOutcomeView();

    }

    private void setUpWindow(){

        // Setup window components amd set params.
        frame = new JFrame("CPT222 - Assignment One");
        dimens = new Dimension();
        layoutManager = new BorderLayout();
        container = frame.getContentPane();

        dimens.setSize(600, 450);
        frame.setSize(dimens);
        frame.setPreferredSize(dimens);
        frame.setLayout(layoutManager);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    private void setupMenu(){

        // Initialise and setup all menu components.
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");

        placeBetMenuItem = new JMenuItem("Place Bet");

        playerMenuItem = new JMenu("Player...");
        addPlayerMenuItem = new JMenuItem("Add Player");
        editPlayerMenuItem = new JMenuItem("Edit Player");

        viewGameHistoryMenuItem = new JMenuItem("Game History");

        container.add(menuBar, BorderLayout.PAGE_START);
        menuBar.add(gameMenu);

        gameMenu.add(placeBetMenuItem);
        placeBetMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addBet();
            }
        });

        gameMenu.add(viewGameHistoryMenuItem);
        viewGameHistoryMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showGameHistory();
            }
        });

        gameMenu.add(playerMenuItem);
        playerMenuItem.add(addPlayerMenuItem);
        playerMenuItem.add(editPlayerMenuItem);
    }

    private void setupButtons(){

        // Initialise and setup all button components.
        buttonContainer = new JPanel();
        buttonLayout = new FlowLayout();
        buttonContainer.setLayout(buttonLayout);
        container.add(buttonContainer, BorderLayout.PAGE_END);

        placeBetButton = new JButton("Place Bet");
        addPlayerButton = new JButton("Add Player");
        flipCoinButton = new JButton("Flip Coins");

        buttonContainer.add(placeBetButton, BorderLayout.PAGE_END);
        buttonContainer.add(addPlayerButton, BorderLayout.PAGE_END);
        buttonContainer.add(flipCoinButton, BorderLayout.PAGE_END);

        placeBetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addBet();
            }
        });

        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addPlayer();
            }
        });

        flipCoinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.spinCoin();
            }
        });

        editPlayerMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.editPlayer();
            }
        });

    }

    private void setupCoinView(){

        // Displays coin face for flip in UI.
        coinLabel = new JLabel();
        coinLabel.setText(LABEL_HEADS);
        coinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        coinLabel.setFont(coinLabel.getFont().deriveFont(64.0f));
        container.add(coinLabel, BorderLayout.CENTER);

    }

    private void setupGameOutcomeView(){

        // Initialise and setup all game outcome data components.
        Font font = new Font("Courier", Font.BOLD, 12);

        gameOutcomeContainer = new JPanel();
        gameOutcomeLayout = new BoxLayout(gameOutcomeContainer, BoxLayout.Y_AXIS);
        gameOutcomeContainer.setLayout(gameOutcomeLayout);
        container.add(gameOutcomeContainer, BorderLayout.WEST);

        lineBreak = new JLabel("\n");


        lblLastCoinFlip = new JLabel("Last Coin Result: ");
        lastCoinFlip = new JLabel("");

        lblGameResult = new JLabel("Result: ");
        lblBetCoin = new JLabel("Bet Coin: ");
        lblBetAmmount = new JLabel("Bet Credits: ");
        lblUpdatedPoints = new JLabel("Updated Credits: ");

        lblGameResult.setFont(font);
        lblBetCoin.setFont(font);
        lblBetAmmount.setFont(font);
        lblUpdatedPoints.setFont(font);

        gameResultData = new JLabel();
        betCoinData = new JLabel();
        betAmountData = new JLabel();
        updatedPointsData = new JLabel();

        gameOutcomeContainer.add(lblGameResult);
        gameOutcomeContainer.add(gameResultData);
        gameOutcomeContainer.add(lineBreak);

        gameOutcomeContainer.add(lblBetCoin);
        gameOutcomeContainer.add(betCoinData);
        gameOutcomeContainer.add(lineBreak);

        gameOutcomeContainer.add(lblBetAmmount);
        gameOutcomeContainer.add(betAmountData);
        gameOutcomeContainer.add(lineBreak);

        gameOutcomeContainer.add(lblUpdatedPoints);
        gameOutcomeContainer.add(updatedPointsData);
        gameOutcomeContainer.add(lineBreak);


        gameOutcomeContainer.add(lblLastCoinFlip);
        gameOutcomeContainer.add(lastCoinFlip);

    }

    public void displayWindow(){

        //Show window.
        frame.pack();
        frame.setVisible(true);

    }

    public void swapCoinFace(){

        // Change current coin face in coinLabel.
        String currentFace = coinLabel.getText();
        coinLabel.setText(currentFace.equalsIgnoreCase(LABEL_HEADS) ? LABEL_TAILS : LABEL_HEADS);
        gameOutcomeContainer.repaint();

    }

    public Frame getWindowContentFrame(){
        return this.frame;
    }

    public void updateGameResult(String gameResult, String betCoin, String betCredits, String updatedCredits){

        // Post game result to screen.
        gameResultData.setText(gameResult);
        betCoinData.setText(betCoin);
        betAmountData.setText(betCredits);
        updatedPointsData.setText(updatedCredits);
        gameOutcomeContainer.repaint();

    }

    public void updateCoinOutcome(int coinNumber, Coin.Face face){

        // Update intermediate flip outcomes to UI.
        String out = String.format("%s (Coin: %d)", face.toString(), coinNumber);
        lastCoinFlip.setText(out);

    }

}

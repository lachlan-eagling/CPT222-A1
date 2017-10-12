package au.edu.rmit.cpt222.view;

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
    private JButton spinCoinButton;

    // Coin components
    private JLabel coinLabel;

    public GameWindow(GameController controller){
        this.controller = controller;
        setupWindowAndComponents();
    }

    private void setupWindowAndComponents(){
        setUpWindow();
        setupMenu();
        setupButtons();
        setupCoinView();
    }

    private void setUpWindow(){

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

        buttonContainer = new JPanel();
        buttonLayout = new FlowLayout();
        buttonContainer.setLayout(buttonLayout);
        container.add(buttonContainer, BorderLayout.PAGE_END);

        placeBetButton = new JButton("Place Bet");
        addPlayerButton = new JButton("Add Player");
        spinCoinButton = new JButton("Spin Coin");

        buttonContainer.add(placeBetButton, BorderLayout.PAGE_END);
        buttonContainer.add(addPlayerButton, BorderLayout.PAGE_END);
        buttonContainer.add(spinCoinButton, BorderLayout.PAGE_END);

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
    }

    private void setupCoinView(){
        coinLabel = new JLabel();
        coinLabel.setText(LABEL_HEADS);
        coinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        coinLabel.setFont(coinLabel.getFont().deriveFont(64.0f));
        container.add(coinLabel, BorderLayout.CENTER);
    }

    public void displayWindow(){
        frame.pack();
        frame.setVisible(true);
    }

    public void swapCoinFace(){
        String currentFace = coinLabel.getText();
        coinLabel.setText(currentFace.equalsIgnoreCase(LABEL_HEADS) ? LABEL_TAILS : LABEL_HEADS);
    }

    public Frame getWindowContentFrame(){
        return this.frame;
    }

}

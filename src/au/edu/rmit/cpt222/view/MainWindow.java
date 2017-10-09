package au.edu.rmit.cpt222.view;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private static final String LABEL_HEADS = "Heads";
    private static final String LABEL_TAILS = "Tails";

    // Declare window components
    private JFrame frame;
    private LayoutManager layoutManager;
    private Dimension dimens;
    private Container container;

    // Menu Components
    private JMenuBar menuBar;

    private JMenu gameMenu;
    private JMenuItem newGameMenuItem;
    private JMenuItem playerMenuItem;
    private JMenuItem addPlayerMenuItem;
    private JMenuItem editPlayerMenuItem;

    // Button components.
    private JPanel buttonContainer;
    private LayoutManager buttonLayout;
    private JButton newGameButton;
    private JButton addPlayerButton;
    private JButton spinCoinButton;

    // Coin components
    private JLabel coinLabel;

    public MainWindow(){
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

    }

    private void setupMenu(){

        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        newGameMenuItem = new JMenuItem("New Game");
        playerMenuItem = new JMenu("Player...");
        addPlayerMenuItem = new JMenuItem("Add Player");
        editPlayerMenuItem = new JMenuItem("Edit Player");

        container.add(menuBar, BorderLayout.PAGE_START);
        menuBar.add(gameMenu);
        gameMenu.add(newGameMenuItem);
        gameMenu.add(playerMenuItem);

        playerMenuItem.add(addPlayerMenuItem);
        playerMenuItem.add(editPlayerMenuItem);
    }

    private void setupButtons(){

        buttonContainer = new JPanel();
        buttonLayout = new FlowLayout();
        buttonContainer.setLayout(buttonLayout);
        container.add(buttonContainer, BorderLayout.PAGE_END);

        newGameButton = new JButton("New Game");
        addPlayerButton = new JButton("Add Player");
        spinCoinButton = new JButton("Spin Coin");

        buttonContainer.add(newGameButton, BorderLayout.PAGE_END);
        buttonContainer.add(addPlayerButton, BorderLayout.PAGE_END);
        buttonContainer.add(spinCoinButton, BorderLayout.PAGE_END);
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

}

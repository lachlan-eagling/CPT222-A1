package au.edu.rmit.cpt222.view;

import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.model.Bet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaceBetDialog extends JDialog{

    private JLabel lblCoinFaceHeads;
    private JLabel lblCoinFaceTails;
    private JLabel lblBetAmount;
    private JLabel lblCoinsToFlip;

    private ButtonGroup coinButtonGroup;
    private JRadioButton headsRadioButton;
    private JRadioButton tailsRadioButton;

    private JSpinner betAmmount;

    private JSpinner coinsToFlip;

    private Bet bet;

    public PlaceBetDialog(Frame parent){
        // Setup Window.
        super(parent, "Place Bet", true);
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Add coin face labels and radio buttons.
        coinButtonGroup = new ButtonGroup();
        headsRadioButton = new JRadioButton();
        tailsRadioButton = new JRadioButton();
        coinButtonGroup.add(headsRadioButton);;
        coinButtonGroup.add(tailsRadioButton);
        headsRadioButton.setSelected(true);

        lblCoinFaceHeads = new JLabel("Heads ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        inputPanel.add(lblCoinFaceHeads, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        inputPanel.add(headsRadioButton, constraints);

        lblCoinFaceTails = new JLabel("Tails ");
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        inputPanel.add(lblCoinFaceTails, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        inputPanel.add(tailsRadioButton, constraints);


        // Add bet amount controls.
        lblBetAmount = new JLabel("Bet Amount: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        inputPanel.add(lblBetAmount, constraints);

        betAmmount = new JSpinner(new SpinnerNumberModel());
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        inputPanel.add(betAmmount, constraints);


        // Add num of coins controls.
        lblCoinsToFlip = new JLabel("Coins to flip: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        inputPanel.add(lblCoinsToFlip, constraints);

        coinsToFlip = new JSpinner(new SpinnerNumberModel(2, 2, 50, 1));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        inputPanel.add(coinsToFlip, constraints);

        // Add action listeners.
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coin.Face coinFace = coinButtonGroup.getSelection() == headsRadioButton ?
                        Coin.Face.heads : Coin.Face.tails;
                int points = (int) betAmmount.getValue();
                int coins = (int) coinsToFlip.getValue();
                bet = new Bet(coinFace, points, coins);
                dispose();
            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnOk);
        buttonPanel.add(btnCancel);

        getContentPane().add(inputPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        // Display window.
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    // Allows controller to retrieve input.
    public Bet getNewBetResult(){
        return bet;
    }
}

package au.edu.rmit.cpt222.view;

import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerDialog extends JDialog {

    private JTextField playerNumber;
    private JTextField playerName;
    private JSpinner creditPoints;

    private Player player;

    public AddPlayerDialog(Frame parent){

        super(parent, "Add Player", true);
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Add components and set constraints for each.
        JLabel lblPlayerNumber = new JLabel("Player Number: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        inputPanel.add(lblPlayerNumber, constraints);

        playerNumber = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        inputPanel.add(playerNumber, constraints);

        JLabel lblName = new JLabel("Name: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        inputPanel.add(lblName, constraints);

        playerName = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        inputPanel.add(playerName, constraints);

        JLabel lblCreditPoints = new JLabel("Credit Points: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        inputPanel.add(lblCreditPoints, constraints);

        creditPoints = new JSpinner(new SpinnerNumberModel());
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        inputPanel.add(creditPoints, constraints);

        // Add buttons and listeners.
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String _playerNumber = playerNumber.getText();
                String _playerName = playerName.getText();
                int _creditPoints = (int) creditPoints.getValue();
                player = new SimplePlayer(_playerNumber, _playerName, _creditPoints);
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

    public Player getAddPlayerResult(){
        return player;
    }
}

package au.edu.rmit.cpt222.view;

import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.interfaces.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerDialog extends JDialog {

    private JLabel lblPlayerNumber;
    private JLabel lblName;
    private JLabel lblCreditPoints;

    private JTextField playerNumber;
    private JTextField playerName;
    private JTextField creditPoints;

    private JButton btnOk;
    private JButton btnCancel;

    private Player player;

    public AddPlayerDialog(Frame parent){

        super(parent, "Add Player", true);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        lblPlayerNumber = new JLabel("Player Number: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lblPlayerNumber, constraints);

        lblName = new JLabel("Name: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lblName, constraints);

        lblCreditPoints = new JLabel("Credit Points: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.gridheight = 3;
        panel.add(lblName, constraints);

        playerNumber = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.gridheight = 3;
        panel.add(playerNumber, constraints);

        playerName = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.gridheight = 3;
        panel.add(playerName, constraints);

        creditPoints = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.gridheight = 3;
        panel.add(creditPoints, constraints);

        btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String _playerNumber = playerNumber.getText();
                String _playerName = playerName.getText();
                Integer _creditPoints = Integer.parseInt(playerName.getText());
                player = new SimplePlayer(_playerNumber, _playerName, _creditPoints);
                dispose();
            }
        });

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel bp = new JPanel();
        bp.add(btnOk);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.PAGE_END);

        pack();
        setResizable(true);
        setLocationRelativeTo(parent);

    }

    public Player getAddPlayerResult(){
        return player;
    }
}

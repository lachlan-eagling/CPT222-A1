package au.edu.rmit.cpt222.view;

import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerDialog2 extends JDialog {

    private JLabel lblPlayerNumber;
    private JLabel lblName;
    private JLabel lblCreditPoints;

    private JTextField playerNumber;
    private JTextField playerName;
    private JTextField creditPoints;

    private JButton btnOk;
    private JButton btnCancel;

    private Player player;

    public AddPlayerDialog2(Frame parent){

        super(parent, "Add Player", true);
        JPanel panel = new JPanel(new BorderLayout());
        LayoutManager labelLayout = new FlowLayout();
        LayoutManager fieldLayout = new FlowLayout();

        lblPlayerNumber = new JLabel("Player Number: ");

        lblName = new JLabel("Name: ");

        lblCreditPoints = new JLabel("Credit Points: ");

        playerNumber = new JTextField();

        playerName = new JTextField();

        creditPoints = new JTextField();

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

package edu.msoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dennis on 1/12/2015.
 */

public class Lecture10 extends JFrame implements ActionListener {

    private final static int WIDTH = 250;
    private final static int HEIGHT = 100;
    private JButton btnShow;
    private JButton btnHide;
    private JLabel ponyLabel;

    public static void main(String[] args) {
        JFrame gui = new Lecture10();
    }

    public Lecture10() {
        setTitle("Dog and Pony Show");
        setSize(WIDTH, HEIGHT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Dog"));

        btnShow = new JButton("Show");
        btnShow.setActionCommand("Show");
        btnShow.addActionListener(this);
        add(btnShow);

        btnHide = new JButton("Hide");
        btnHide.addActionListener(this);
        btnHide.setActionCommand("Hide");
        add(btnHide);

        ponyLabel = new JLabel("");
        add(ponyLabel);

        setVisible(true);
    }

    @Override
 //   public void actionPerformed(ActionEvent event) {
 //       ponyLabel.setText(ponyLabel.getText() + "Pony");
//    }

/*    public void actionPerformed(ActionEvent event) {
        if (event.getSource() instanceof JButton) {
            JButton eventSrc = (JButton) event.getSource();
            if (eventSrc == btnShow) {
                ponyLabel.setText(ponyLabel.getText() + "Pony");
            } else if (eventSrc == btnHide) {
                ponyLabel.setText("");
            }
        }
    }
*/
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() instanceof JButton) {
            String command = event.getActionCommand();
            if (command.equals("Show")) {
                ponyLabel.setText(ponyLabel.getText() + "Pony");
            } else if (command.equals("Hide")) {
                ponyLabel.setText("");
            }
        }
    }
}
























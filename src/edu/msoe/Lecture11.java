package edu.msoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dennis on 1/12/2015.
 */

public class Lecture11 extends JFrame implements ActionListener {

    private final static int WIDTH = 600;
    private final static int HEIGHT = 400;
    private JButton btnShow;
    private JButton btnHide;
    private JLabel ponyLabel;

    public static void main(String[] args) {
        JFrame gui = new Lecture11();
    }

    public Lecture11() {
        setTitle("Dog and Pony Show");
        setSize(WIDTH, HEIGHT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //buildBorderLayout();
        buildGridLayout();
        //buildNestedLayout();

    }

    private void buildBorderLayout() {
        //http://docs.oracle.com/javase/8/docs/api/java/awt/BorderLayout.html
        setLayout(new BorderLayout());

        add(new JLabel("Dog"), BorderLayout.WEST);

        btnShow = new JButton("Show");
        btnShow.setActionCommand("Show");
        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ponyLabel.setText(ponyLabel.getText() + "Pony");
            }
        });
        add(btnShow, BorderLayout.NORTH);

        btnHide = new JButton("Hide");
        btnHide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ponyLabel.setText("");
            }
        });
        btnHide.setActionCommand("Hide");
        add(btnHide, BorderLayout.SOUTH);

        ponyLabel = new JLabel("");
        add(ponyLabel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void buildGridLayout() {
        //http://docs.oracle.com/javase/7/docs/api/java/awt/GridLayout.html

        /* rows, cols */
        setLayout(new GridLayout(3, 2));


        add(new JLabel("Dog"));

        btnShow = new JButton("Show");
        btnShow.addActionListener(new showButtonHandler());
        add(btnShow);
/*
        btnHide = new JButton("Hide");
        btnHide.addActionListener(new hideButtonHandler());
*/
        btnHide.addActionListener(e -> {
                ponyLabel.setText("");
        });

        add(btnHide);

        ponyLabel = new JLabel("");
        add(ponyLabel);

        setVisible(true);
    }

    private void buildNestedLayout()
    {
        //http://docs.oracle.com/javase/8/docs/api/java/awt/BorderLayout.html
        setLayout(new BorderLayout());

        add(new JLabel("Dog"), BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());


        btnShow = new JButton("Show");
        btnShow.setActionCommand("Show");
        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ponyLabel.setText(ponyLabel.getText() + "Pony");
            }
        });
        buttonPanel.add(btnShow);

        btnHide = new JButton("Hide");
        btnHide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ponyLabel.setText("");
            }
        });
        buttonPanel. add(btnHide);

        ponyLabel = new JLabel("");
        add(ponyLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

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


    private class showButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            ponyLabel.setText(ponyLabel.getText() + "Pony");
        }
    }


    private class hideButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            ponyLabel.setText("");
        }
    }

}
























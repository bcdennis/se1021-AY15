package edu.msoe;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Lecture12 {

    JFrame actionPerformedDemoFrame;

    public static void main(String[] args) {

        //AWTDemo();
        //swingDemo();
        actionPerformedDemo();
    }

    private static void AWTDemo() {

        Frame awtFrame = new Frame();
        awtFrame.setSize(400,200);

        Label aLabel = new Label("A Label");
        TextField aTextField = new TextField("", 10);
        Button firstButton = new Button("First Button");
        Button secondButton = new Button("Second Button");

        awtFrame.setLayout(new FlowLayout());

        awtFrame.add(aLabel);
        awtFrame.add(aTextField);
        awtFrame.add(firstButton);
        awtFrame.add(secondButton);


        awtFrame.addWindowListener(new WindowAdapter() {

            public  void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        awtFrame.setVisible(true);
    }

    private static void swingDemo() {

        JFrame swingFrame = new JFrame();
        swingFrame.setSize(400,200);

        JLabel aLabel = new JLabel("A Label");
        JTextField aTextField = new JTextField("", 10);
        JButton firstButton = new JButton("First Button");
        JButton secondButton = new JButton("Second Button");

        swingFrame.setLayout(new FlowLayout());

        swingFrame.add(aLabel);
        swingFrame.add(aTextField);
        swingFrame.add(firstButton);
        swingFrame.add(secondButton);

        swingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        swingFrame.setVisible(true);
    }

    private static void actionPerformedDemo() {
        Lecture12 lecture = new Lecture12();
        lecture.actionPerformedDemoFrame = new JFrame();
        lecture.actionPerformedDemoFrame.setSize(400,200);

        JLabel aLabel = new JLabel("A Label");
        JTextField aTextField = new JTextField("", 30);
        JButton firstButton = new JButton("First Button");
        JButton secondButton = new JButton("Second Button");

        lecture.actionPerformedDemoFrame.setLayout(new FlowLayout());

        lecture.actionPerformedDemoFrame.add(aLabel);
        lecture.actionPerformedDemoFrame.add(aTextField);
        lecture.actionPerformedDemoFrame.add(firstButton);
        lecture.actionPerformedDemoFrame.add(secondButton);

        firstButton.addActionListener(lecture.new FirstButtonListener(aLabel));


        secondButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                aLabel.setText("You clicked the second button.");
            }
        });

        lecture.actionPerformedDemoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lecture.actionPerformedDemoFrame.setVisible(true);
    }

    private class FirstButtonListener implements ActionListener {

        JLabel targetLabel;

        public FirstButtonListener(JLabel target) {
            this.targetLabel = target;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            targetLabel.setText("You clicked the first button.");
        }
    }
}

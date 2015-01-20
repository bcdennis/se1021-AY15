package edu.msoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Scanner;

public class Lecture13 extends JFrame{
    JLabel messageLabel;
    JLabel instructionsLabel;
    JLabel errorLabel;
    JTextField inputField;
    JButton goButton;

    public static void main(String[] args) {
        Lecture13 lecture = new Lecture13();
        lecture.go();

    }


    public void go() {
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        whatAreExceptions();
        //commandLineInput();
    }

    public void commandLineInput() {

        Scanner standardIn = new Scanner(System.in);
        String userInput;

        System.out.print("Enter an integer ('q' to quit.):");
        userInput = standardIn.next();

        while (!userInput.equalsIgnoreCase("q")) {

            int userInteger;
            try {
                userInteger = Integer.parseInt(userInput);
                for (int i = 0; i < userInteger; i++) {
                    System.out.print(userInteger);
                }
                System.out.println("");
            } catch (NumberFormatException ex) {
                System.out.println("Invalid entry: '" + userInput + "'.  You must enter an integer.");
                //ex.printStackTrace();
            }
            System.out.print("Enter an integer ('q' to quit.):");
            userInput = standardIn.next();

        }
    }

    public void whatAreExceptions() {

        setLayout(new GridLayout(5, 1));


        messageLabel = new JLabel("Exceptions are events that disrupt the normal flow of a program.");
        instructionsLabel = new JLabel("Enter 1 when you have this memorized:");
        errorLabel = new JLabel("");
        inputField = new JTextField("enter a numeric value only", 20);
        goButton = new JButton("Go");
        goButton.setMnemonic('G');


        inputField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                inputField.setText("");
                errorLabel.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        goButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int answer;
                try {
                    answer = Integer.parseInt(inputField.getText());

                    if (answer == 1) {
                        JOptionPane.showMessageDialog(null, "You'll be an expert in no time!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Keep trying!");
                    }

                } catch (NumberFormatException ex) {
                    // http://docs.oracle.com/javase/7/docs/api/java/lang/NumberFormatException.html

                    String message = formatExceptions(ex);
                    errorLabel.setText(message);
                }


            }
        });


        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout());
        messagePanel.add(messageLabel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(instructionsLabel);
        inputPanel.add(inputField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JPanel errorPanel = new JPanel();
        errorPanel.setLayout(new FlowLayout());
        errorPanel.add(errorLabel);

        buttonPanel.add(goButton);
        add(messagePanel);
        add(inputPanel);
        add(buttonPanel);
        add(errorPanel);


        this.setVisible(true);

        getRootPane().setDefaultButton(goButton);
        goButton.requestFocus();

    }

    private String formatExceptions(Exception ex) {
        String message = "<html><body>";
        message += ex.getClass();
        message += "<br/>";
        message += ex.getMessage();
        message += "</body></html>";

        return message;
    }
}

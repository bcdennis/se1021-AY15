package edu.msoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by dennis on 1/8/2015.
 */
public class Lecture09 implements ActionListener {
    public static void main(String[] args) {

        // http://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html

        //JOptionPaneDemo();


        JFrameDemo();
        JFrameDemoWithActions();


    }

    private static void JOptionPaneDemo() {
        System.out.println("Hello");
        JOptionPane.showMessageDialog(null, "Hello");

        System.out.println("What is your name?");

        String name = "";
        name = JOptionPane.showInputDialog(null, "What is your name?", "I have a question",
                JOptionPane.QUESTION_MESSAGE);

        System.out.println("Hi, " + name + "!");


        System.out.println("Do you like SE1021?");
        if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null, "Do you like SE1021?",
                "I have a question",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
            System.out.println("Of course you do.");
        } else {
            System.out.println("I knew it!");
        }


        System.out.println("How do you feel?");

        int selection = 0;
        Object[] options = {"Happy", "Happier", "Happiest"};

        selection = JOptionPane.showOptionDialog(null,"How do you feel?", "I have a question",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

        System.out.println ("You said you were '" + options[selection] + "'!  Me too.");
    }

    private static void JFrameDemo()
    {
        // http://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html
        JFrame demoFrame = new JFrame("JFrame Demo");

        demoFrame.setSize(600,400);
        demoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // http://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
        demoFrame.setLayout(new FlowLayout());
        //demoFrame.setVisible(true);

        // http://docs.oracle.com/javase/8/docs/api/javax/swing/JLabel.html
        // http://docs.oracle.com/javase/tutorial/uiswing/components/label.html
        JLabel firstNameLabel = new JLabel("First Name:");
        demoFrame.add(firstNameLabel);

        // http://docs.oracle.com/javase/8/docs/api/javax/swing/JTextField.html
        // http://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html

        JTextField firstNameTextField = new JTextField(25);
        demoFrame.add(firstNameTextField);

        // http://docs.oracle.com/javase/8/docs/api/javax/swing/JButton.html
        // http://docs.oracle.com/javase/tutorial/uiswing/components/button.html
        JButton saveButton = new JButton("Save");
        saveButton.setMnemonic(KeyEvent.VK_S);
        demoFrame.add(saveButton);


        JButton cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic(KeyEvent.VK_C);
        demoFrame.add(cancelButton);

        demoFrame.setVisible(true);
        // demoFrame.revalidate();

    }

    private static void JFrameDemoWithActions() {

        JFrame demoFrame = new JFrame("JFrame Demo");

        demoFrame.setSize(600,400);
        demoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        demoFrame.setLayout(new FlowLayout());

        JLabel firstNameLabel = new JLabel("First Name:");
        demoFrame.add(firstNameLabel);

        JTextField firstNameTextField = new JTextField(25);
        demoFrame.add(firstNameTextField);

        JButton saveButton = new JButton("Save");
        saveButton.setMnemonic(KeyEvent.VK_S);
        saveButton.setActionCommand("save");
        demoFrame.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic(KeyEvent.VK_C);
        cancelButton.setActionCommand("cancel");
        demoFrame.add(cancelButton);

        saveButton.addActionListener(new Lecture09());
        cancelButton.addActionListener(new Lecture09());

        demoFrame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if ("save".equals(e.getActionCommand())) {
            System.out.println("Saved!");
        }

        if ("cancel".equals(e.getActionCommand())) {
            System.exit(0);
        }

    }
}

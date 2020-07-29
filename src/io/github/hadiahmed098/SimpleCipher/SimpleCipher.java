package io.github.hadiahmed098.SimpleCipher;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;

public class SimpleCipher {


    //Private variables
    private JTextArea input;
    private JTextArea output;

    public static void main(String[] args) {
        new SimpleCipher().runner();
    }

    private void runner()                                           //Creates and runs the GUI
    {
        class Listener implements ActionListener                    //Inner class to handle user actions from GUI
        {
            public void actionPerformed(ActionEvent evt)
            {
                if(evt.getActionCommand().equals("Encrypt"))        //Handles input from the Plaintext box
                {
                    output.setText("");
                    output.setText(Encoder.encodeStart(input.getText()));
                }
                else                                                //Handles input from the Encrypted box
                {
                    input.setText("");
                    input.setText(Decoder.decodeStart(output.getText()));
                }
            }
        }

        //Initializes the basic frame and properties
        JFrame frame = new JFrame("Simple Cipher");
        frame.setSize(380,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        //Initialize all buttons with listeners
        JButton encrypt = new JButton("Encrypt");
        JButton decrypt = new JButton("Decrypt");
        ActionListener l = new Listener();
        encrypt.addActionListener(l);
        decrypt.addActionListener(l);

        //Initialize all text fields and properties
        input = new JTextArea(4,30);
        output = new JTextArea(4,30);
        input.setLineWrap(true);
        output.setLineWrap(true);
        JScrollPane input1 = new JScrollPane(input);
        JScrollPane output1 = new JScrollPane(output);
        input1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        output1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        input1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        output1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        //Add objects to frame
        frame.add(new JLabel("Plaintext:"));
        frame.add(input1);
        frame.add(new JLabel("------------------------------------------------------------------------------------------"));
        frame.add(new JLabel("Encrypted:"));
        frame.add(output1);
        frame.add(new JLabel("------------------------------------------------------------------------------------------"));
        frame.add(encrypt);
        frame.add(decrypt);

        frame.setVisible(true);
    }
}
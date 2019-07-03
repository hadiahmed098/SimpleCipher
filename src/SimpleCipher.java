import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;

public class SimpleCipher {


    //Private variables
    private JTextArea input;
    private JTextArea output;
    private static Encoder e = new Encoder();
    private static Decoder d = new Decoder();

    public static void main(String[] args) {
        //Test for future release
        //TODO encode the output string as a series of ASCII characters
        System.out.println((char) Integer.parseInt("0021",16)); System.out.println(Integer.toHexString(75));
        new SimpleCipher().runner();
    }

    private void runner() //Creates and runs the GUI
    {
        //Inner class to handle user actions from GUI
        class Listener implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                if(evt.getActionCommand().equals("Encrypt")) //Handles input from the Plaintext box
                {
                    output.setText("");
                    output.setText(e.encodeStart(input.getText()));
                }
                else //Handles input from the Encrypted box
                {
                    if(!checkOutput(output.getText())) //Verifies input only contains numbers
                    {
                        JOptionPane.showMessageDialog(null,"Encrypted Text Can Only Contain Numbers!","Error",JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    input.setText("");
                    input.setText(d.decodeStart(output.getText()));
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

    //Private method to verify encrypted text box only contains numbers
    private boolean checkOutput(String out)
    {
        for(int i=0;i<out.length();i++)
        {
            try
            {
                Integer.parseInt(out.substring(i,i+1));
            } catch(NumberFormatException nfe)
            {
                return false;
            }
        }
        return true;
    }
}
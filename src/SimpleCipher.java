import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SimpleCipher {

    private JTextArea input;
    private JTextArea output;
    private static Encoder e = new Encoder();
    private static Decoder d = new Decoder();
    private JFrame frame;

    public static void main(String[] args) {SimpleCipher s = new SimpleCipher();s.runner();}

    public void runner()
    {
        class Listener implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                if(evt.getActionCommand().equals("Encrypt"))
                {
                    if(input.getText().length() == 0)
                    {
                        JOptionPane.showMessageDialog(null,"Please Enter Text!","Error",JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    output.setText(e.encodeStart(input.getText()));
                }
                else
                {
                    if(output.getText().length() == 0)
                    {
                        JOptionPane.showMessageDialog(null,"Please Enter Text!","Error",JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if(!checkOutput(output.getText()))
                    {
                        JOptionPane.showMessageDialog(null,"Encrypted Text Can Only Contain Numbers!","Error",JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    input.setText(d.decodeStart(output.getText()));
                }
            }
        }

        frame = new JFrame("Simple Cipher");
        frame.setSize(380,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JButton encrypt = new JButton("Encrypt");
        JButton decrypt = new JButton("Decrypt");
        ActionListener l = new Listener();
        encrypt.addActionListener(l);
        decrypt.addActionListener(l);

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

    private boolean checkOutput(String out)
    {

        ArrayList<String> numbers = new ArrayList<String>();
        for(int i=0; i<10; i++)
        {
            numbers.add(Integer.toString(i));
        }

        boolean yeet = true;
        String letter;

        for(int i=0;i<out.length();i++)
        {
            letter = Character.toString(out.charAt(i));
            if (numbers.indexOf(letter) < 0)
                yeet=false;
        }

        return yeet;
    }
}
package VoteUp;

import javax.swing.*;

public class VoteUpFrame extends JFrame{
    private JButton YESButton;
    private JButton NOButton;
    private JTextField question;
    private JTextField uid;
    private JTextField name;
    private JTextField email;
    private JLabel VoteUp;
    private JPanel mainPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;

    VoteUpFrame()
    {
        super("VoteUp GUI Application");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    public static void main(String[] args) {
        VoteUpFrame voteUpFrame=new VoteUpFrame();
        voteUpFrame.setVisible(true);
    }
}

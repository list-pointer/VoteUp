package VoteUp;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class VoteUpFrame extends JFrame {
    Connection con;
    String sqlQuery;
    private JButton YESButton;
    private JButton NOButton;
    private JTextField uid;
    private JTextField name;
    private JTextField email;
    private JPanel mainPanel;
    private JLabel ShowQuestion;
    private JLabel Result;
    private JLabel VoteUp;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JButton ShowResult;

    VoteUpFrame() {
        super("VoteUp");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        ShowResult.setVisible(false);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  //Setting up connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voteup", "", "");

            System.out.println("** Connection has been established **");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ShowQuestion.setText("Should we eat pasta?"); //Question goes here

        YESButton.addActionListener(e -> { //Listener for YES button
            try {
                if (uid.getText().equals("") || name.getText().equals("") || email.getText().equals(""))
                    JOptionPane.showMessageDialog(mainPanel, "Please fill Data in all fields");
                else {
                    int input = JOptionPane.showOptionDialog(mainPanel, "Do you want to vote \"YES\"?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    if (input == JOptionPane.OK_OPTION) {
                        sqlQuery = "insert into votetable (uid,u_name,email,vote) values(?,?,?,?)";
                        PreparedStatement statement = con.prepareStatement(sqlQuery);
                        statement.setString(1, uid.getText());
                        statement.setString(2, name.getText());
                        statement.setString(3, email.getText());
                        statement.setString(4, "1");
                        statement.executeUpdate();
                        JOptionPane.showMessageDialog(mainPanel, "You have clicked on YES.");

                        clearFields();

                        ShowResult.setVisible(true);
                        statement.close();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        NOButton.addActionListener(e -> {  //Listener for NO button
            try {
                if (uid.getText().equals("") || name.getText().equals("") || email.getText().equals(""))
                    JOptionPane.showMessageDialog(mainPanel, "Please fill Data in all fields");
                else {
                    int input = JOptionPane.showOptionDialog(mainPanel, "Do you want to vote \"NO\"?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    if (input == JOptionPane.OK_OPTION) {
                        sqlQuery = "insert into votetable (uid,u_name,email,vote) values(?,?,?,?)";
                        PreparedStatement statement = con.prepareStatement(sqlQuery);
                        statement.setString(1, uid.getText());
                        statement.setString(2, name.getText());
                        statement.setString(3, email.getText());
                        statement.setString(4, "0");
                        statement.executeUpdate();
                        JOptionPane.showMessageDialog(mainPanel, "You have clicked on NO.");

                        clearFields();

                        ShowResult.setVisible(true);
                        statement.close();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        ShowResult.addActionListener(e -> {
            try {
                sqlQuery = "select (select count(*) from votetable where vote=1) as TrueVotes,\n" +
                        "(select count(*) from votetable where vote=0) as FalseVotes\n" +
                        "from dual;";
                ResultSet rs = con.prepareStatement(sqlQuery).executeQuery();
                rs.next();
                int yes = rs.getInt(1);
                int no = rs.getInt(2);
                rs.close();

                if (yes > no)
                    Result.setText("The answer is YES");
                else if (yes < no)
                    Result.setText("The answer is NO");
                else
                    Result.setText("It is a TIE!!!");
                //Timer
                new Timer(10000, e1 -> Result.setText("")).start();
                ShowResult.setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Conformation before exiting
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                String[] ObjButtons = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(mainPanel, "Are you sure you want to exit?", "Confirmation on Exit", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                if (PromptResult == JOptionPane.YES_OPTION) {
                    try {
                        con.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    System.out.println("** Connection has been closed **");
                    System.exit(0);
                }
            }
        });
    }

    public static void main(String[] args) {
        VoteUpFrame voteUpFrame = new VoteUpFrame();
        voteUpFrame.setVisible(true);
    }

    public void clearFields() {
        uid.setText("");
        name.setText("");
        email.setText("");
    }
}

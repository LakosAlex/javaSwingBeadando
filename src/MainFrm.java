import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainFrm extends JFrame {
    private JPanel pnlMain;
    private JTextField txtFldUsername;
    private JTextField txtFldFullname;
    private JPasswordField psswrdFildPassword;
    private JTextField txtFldAddress;
    private JComboBox cmbBxGender ;
    private JButton bttnRegistration;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblFullName;
    private JLabel lblAddress;
    private JLabel lblGender;

    public static void main(String[] args) {
        MainFrm mainFrm = new MainFrm();
        mainFrm.setContentPane(mainFrm.pnlMain);
        mainFrm.setTitle("Hello!!!!");
        mainFrm.setSize(400,500);
        mainFrm.setVisible(true);
        mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrm.setResizable(false);

    }
    public MainFrm(){

    }


}

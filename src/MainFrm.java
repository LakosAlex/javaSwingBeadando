import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrm extends JFrame {
    private JPanel pnlMain;
    private JTextField txtFldUsername;
    private JTextField txtFldFullname;
    private JPasswordField psswrdFildPassword;
    private JTextField txtFldAddress;
    private JComboBox cmbBxRole;
    private JButton bttnRegistration;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblFullName;
    private JLabel lblAddress;
    private JLabel lblRole;
    private JButton bttnDelete;
    private JButton bttnSave;
    private JButton bttnOpen;
    private JPanel pnlRegisteredUsers;
    private JPanel pnlRegistration;
    private JPanel pnlRegisteredUsersBttns;
    private JTextPane txtPn;
    private JScrollPane scrllPn;
    private JPanel pnlSearch;
    private JTextField txtFldSearch;
    private JButton bttnSearch;

    public static void main(String[] args) throws ClassNotFoundException {

        MainFrm mainFrm = new MainFrm();
        mainFrm.setContentPane(mainFrm.pnlMain);
        mainFrm.setTitle("Registration App");
        mainFrm.setSize(400,400);
        mainFrm.setVisible(true);
        mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrm.setResizable(false);
    }
    public MainFrm(){

        bttnRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] password = psswrdFildPassword.getPassword();
                int role = cmbBxRole.getSelectedIndex();
                DB db = new DB();
                db.insertUserIntoDB(txtFldUsername.getText(),password, role);
                resetControls();
            }
        });
    }

    public void resetControls(){

        txtFldUsername.setText("");
        psswrdFildPassword.setText("");
        txtFldFullname.setText("");
        txtFldAddress.setText("");
        cmbBxRole.setSelectedIndex(-1);
    }


}

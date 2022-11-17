import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

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
    private JButton bttnWrite;
    private JPanel pnlRegisteredUsers;
    private JPanel pnlRegistration;
    private JPanel pnlRegisteredUsersBttns;
    private JScrollPane scrllPn;
    private JPanel pnlSearch;
    private JButton bttnDelete;
    private JTextPane txtPn;
    private JComboBox cmbBxUsers;
    private JButton bttnEdit;
    private JButton bttnCancel;
    private JButton bttnSave;
    DB db;
    User user;

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

        fillTxtPnCmbBx();
        bttnRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] password = psswrdFildPassword.getPassword();
                int role = cmbBxRole.getSelectedIndex();
                db = new DB();
                db.insertUserIntoDB(txtFldUsername.getText(),password, txtFldFullname.getText(), txtFldAddress.getText(), role);
                resetControls();
                fillTxtPnCmbBx();
            }
        });
        bttnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db = new DB();
                db.deleteUserFromDB(Objects.requireNonNull(cmbBxUsers.getSelectedItem()).toString());
                resetControls();
                fillTxtPnCmbBx();
            }
        });
        bttnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db = new DB();
                user = db.getUser(cmbBxUsers.getSelectedItem().toString());
                txtFldUsername.setText(user.getUsername());
                txtFldAddress.setText(user.getAddress());
                txtFldFullname.setText(user.getFullName());
                cmbBxRole.setSelectedIndex(Integer.parseInt(user.getRole()) - 1);
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

    public void fillTxtPnCmbBx(){

        txtPn.setText(null);
        cmbBxUsers.removeAllItems();
        db = new DB();
        ArrayList<User> users = db.getUsers();
        for (User user : users){
            txtPn.setText(txtPn.getText() + "Username: " + user.getUsername() + " | Full Name: " + user.getFullName() + "\n");
            cmbBxUsers.addItem(user.getUsername());
        }
    }
}

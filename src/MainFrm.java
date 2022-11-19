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

        bttnSave.setEnabled(false);
        bttnCancel.setEnabled(false);
        txtPn.setEditable(false);
        fillTxtPnCmbBx();
        bttnRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] password = psswrdFildPassword.getPassword();
                db = new DB();
                db.insertUserIntoDB(txtFldUsername.getText(),password, txtFldFullname.getText(), txtFldAddress.getText(), cmbBxRole.getSelectedIndex());
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
                setComponentEnabledState(false, false, false, false, false, false, true, true, false );
                db = new DB();
                user = db.getUser(cmbBxUsers.getSelectedItem().toString());
                txtFldUsername.setText(user.getUsername());
                txtFldAddress.setText(user.getAddress());
                txtFldFullname.setText(user.getFullName());
                cmbBxRole.setSelectedIndex(Integer.parseInt(user.getRole()) - 1);
            }
        });
        bttnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setComponentEnabledState(true, true, true, true, true, true, false, false, true);
                resetControls();
            }
        });
        bttnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db = new DB();
                db.editUser(txtFldUsername.getText(), txtFldFullname.getText(), txtFldAddress.getText(), cmbBxRole.getSelectedIndex());
                setComponentEnabledState(true, true, true, true, true, true, false, false, true);
                resetControls();
                fillTxtPnCmbBx();
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

    public void setComponentEnabledState(boolean bttnDeleteEnabled, boolean bttnEditEnabled, boolean bttnWriteEnabled, boolean bttnRegistrationEnabled, boolean cmbBxUsersEnabled, boolean psswrdFildPasswordEnabled, boolean bttnSaveEnabled, boolean bttnCancelEnabled, boolean txtFldUsernameEnabled){

        bttnDelete.setEnabled(bttnDeleteEnabled);
        bttnEdit.setEnabled(bttnEditEnabled);
        bttnWrite.setEnabled(bttnWriteEnabled);
        bttnRegistration.setEnabled(bttnRegistrationEnabled);
        cmbBxUsers.setEnabled(cmbBxUsersEnabled);
        psswrdFildPassword.setEnabled(psswrdFildPasswordEnabled);
        bttnSave.setEnabled(bttnSaveEnabled);
        bttnCancel.setEnabled(bttnCancelEnabled);
        txtFldUsername.setEnabled(txtFldUsernameEnabled);
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

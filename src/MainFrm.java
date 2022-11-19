import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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
    private JPanel pnlUserOperations;
    private JButton bttnDelete;
    private JTextPane txtPn;
    private JComboBox cmbBxUsers;
    private JButton bttnEdit;
    private JButton bttnCancel;
    private JButton bttnSave;
    private JPanel pnlLoginChecker;
    private JTextField txtFldLoginUsername;
    private JButton bttnLogin;
    private JLabel lblLoginUsername;
    private JLabel lblLoginPassword;
    private JPasswordField psswrdFldLogin;
    DB db;
    User user;

    public static void main(String[] args) throws ClassNotFoundException {

        MainFrm mainFrm = new MainFrm();
        mainFrm.setContentPane(mainFrm.pnlMain);
        mainFrm.setTitle("Registration App");
        mainFrm.setSize(700,400);
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
                if (!txtFldUsername.getText().isEmpty() && !(psswrdFildPassword.getPassword().length == 0) && !txtFldFullname.getText().isEmpty() && !txtFldAddress.getText().isEmpty() && !(cmbBxRole.getSelectedIndex() == -1)){
                    db.insertUserIntoDB(txtFldUsername.getText(),password, txtFldFullname.getText(), txtFldAddress.getText(), cmbBxRole.getSelectedIndex());
                    resetControls();
                    fillTxtPnCmbBx();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Registration failed - missing information!");
                }
            }
        });
        bttnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null,"Sure? You want to delete the user?", "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION) {
                    db = new DB();
                    db.deleteUserFromDB(Objects.requireNonNull(cmbBxUsers.getSelectedItem()).toString());
                    resetControls();
                    fillTxtPnCmbBx();
                }
            }
        });
        bttnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setComponentEnabledState(false, false, false, false, false, false, true, true, false, false, false, false );
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
                setComponentEnabledState(true, true, true, true, true, true, false, false, true, true, true, true);
                resetControls();
            }
        });
        bttnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db = new DB();
                if (!txtFldFullname.getText().isEmpty() && !txtFldAddress.getText().isEmpty() && !(cmbBxRole.getSelectedIndex() == -1)){
                    db.editUser(txtFldUsername.getText(), txtFldFullname.getText(), txtFldAddress.getText(), cmbBxRole.getSelectedIndex());
                    setComponentEnabledState(true, true, true, true, true, true, false, false, true, true, true, true);
                    resetControls();
                    fillTxtPnCmbBx();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Updating user info failed failed - missing information!");
                }

            }
        });
        bttnWrite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = null;
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(null);
                if(option == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    try {
                        FileWriter fw = new FileWriter(file);
                        PrintWriter pw = new PrintWriter(fw);
                        db = new DB();
                        ArrayList<User> users = db.getUsers();
                        for (User user : users){
                            pw.print("Username: " + user.getUsername() + " | Full name:  "+ user.getFullName() + " | Address: " + user.getAddress() + " | Role: " + user.getRole() + "\n");
                        }
                        pw.close();
                        JOptionPane.showMessageDialog(null, "File was succesfully created at:\n" + file.getAbsolutePath());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        bttnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = null;
                char[] password = psswrdFldLogin.getPassword();
                db = new DB();
                if (!txtFldLoginUsername.getText().isEmpty() && !(psswrdFldLogin.getPassword().length == 0)){
                    user = db.loginCheck(txtFldLoginUsername.getText(),password);
                    if (user == null) {
                        JOptionPane.showMessageDialog(null, "Wrong credentials!");
                    } else{
                        JOptionPane.showMessageDialog(null, "Login Successful! \nUsername: "+ user.getUsername() + "\nFull name: " + user.getFullName() + "\nAddress: " + user.getAddress());
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Missing Username or Password!");
                }
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

    public void setComponentEnabledState(boolean bttnDeleteEnabled, boolean bttnEditEnabled, boolean bttnWriteEnabled, boolean bttnRegistrationEnabled, boolean cmbBxUsersEnabled, boolean psswrdFildPasswordEnabled, boolean bttnSaveEnabled, boolean bttnCancelEnabled, boolean txtFldUsernameEnabled, boolean txtFldLoginUsernameEnabled, boolean psswrdFldLoginEnabled, boolean bttnLoginEnabled){

        bttnDelete.setEnabled(bttnDeleteEnabled);
        bttnEdit.setEnabled(bttnEditEnabled);
        bttnWrite.setEnabled(bttnWriteEnabled);
        bttnRegistration.setEnabled(bttnRegistrationEnabled);
        cmbBxUsers.setEnabled(cmbBxUsersEnabled);
        psswrdFildPassword.setEnabled(psswrdFildPasswordEnabled);
        bttnSave.setEnabled(bttnSaveEnabled);
        bttnCancel.setEnabled(bttnCancelEnabled);
        txtFldUsername.setEnabled(txtFldUsernameEnabled);
        txtFldLoginUsername.setEnabled(txtFldLoginUsernameEnabled);
        psswrdFldLogin.setEnabled(psswrdFldLoginEnabled);
        bttnLogin.setEnabled(bttnLoginEnabled);
    }

    public void fillTxtPnCmbBx(){

        txtPn.setText(null);
        cmbBxUsers.removeAllItems();
        db = new DB();
        ArrayList<User> users = db.getUsers();
        for (User user : users){
            txtPn.setText(txtPn.getText() + "Username: " + user.getUsername() + "\n");
            cmbBxUsers.addItem(user.getUsername());
        }
    }
}

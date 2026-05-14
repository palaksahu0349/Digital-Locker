package com.digitalloker;
import com.digitallocker.dao.UserDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class RegisterFrame extends JFrame implements ActionListener {
    JTextField username;
    JPasswordField password;
    JButton register;
    //  DAO OBJECT
    UserDAO dao = new UserDAO();
    public RegisterFrame() {
        setTitle("Digital Locker - Register");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //  Gradient Background Panel
        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0,new Color(255, 20, 147),
                 getWidth(), getHeight(),new Color(65, 105, 225));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());}
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);
        //  TITLE
        JLabel title = new JLabel("Create Account");
        title.setBounds(120, 20, 300, 40);
        title.setFont(new Font("Poppins", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        backgroundPanel.add(title);
        //  USERNAME LABEL
        JLabel l1 = new JLabel("Username");
        l1.setBounds(70, 90, 120, 25);
        l1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        l1.setForeground(Color.WHITE);
        backgroundPanel.add(l1);
        //  USERNAME FIELD
        username = new JTextField();
        username.setBounds(70, 120, 340, 40);
        username.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        username.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        backgroundPanel.add(username);
        //  PASSWORD LABEL
        JLabel l2 = new JLabel("Password");
        l2.setBounds(70, 180, 120, 25);
        l2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        l2.setForeground(Color.WHITE);
        backgroundPanel.add(l2);
        // PASSWORD FIELD
        password = new JPasswordField();
        password.setBounds(70, 210, 340, 40);
        password.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        password.setBorder( BorderFactory.createLineBorder(Color.WHITE, 2));
        backgroundPanel.add(password);
        //  REGISTER BUTTON
        register = new JButton("Register");
        register.setBounds(140, 270, 180, 42);
        register.setFont(new Font("Segoe UI", Font.BOLD, 18));
        register.setBackground(new Color(98, 0, 255));
        register.setForeground(Color.WHITE);
        register.setFocusPainted(false);
        register.setBorderPainted(false);
        backgroundPanel.add(register);
        register.addActionListener(this);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        // GET USER INPUT
        String user = username.getText();
        String pass =String.valueOf(password.getPassword());
        // SAVE USER IN DATABASE
        boolean registerSuccess =dao.register(user, pass);
        // IF FAILED
        if (!registerSuccess) {
            JOptionPane.showMessageDialog(this,"Registration Failed");
            return;
        }
        // SUCCESS DIALOG
        JDialog dialog =new JDialog(this, "Success", true);
        dialog.setSize(360, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);
        //  LIGHT PURPLE GRADIENT PANEL
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0,new Color(186, 85, 211),
                getWidth(), getHeight(),new Color(147, 112, 219));
                g2d.setPaint(gp);
                g2d.fillRect(0,0,getWidth(),getHeight());}
        };
        panel.setLayout(null);
        //  SUCCESS TEXT
        JLabel success =new JLabel("Registered Successfully!");
        success.setFont(new Font("Segoe UI", Font.BOLD, 22));
        success.setForeground(new Color(255, 255, 255));
        success.setBounds(30, 25, 320, 30);
        panel.add(success);
        // USERNAME TEXT
        JLabel userLabel =new JLabel("Username: " + user );
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
        userLabel.setForeground(new Color(255, 255, 204));
        userLabel.setBounds(85, 75, 240, 25);
        panel.add(userLabel);
        // OK BUTTON
        JButton ok = new JButton("OK");
        ok.setBounds(120, 120, 110, 35);
        ok.setBackground(new Color(255, 255, 255));
        ok.setForeground(new Color(138, 43, 226));
        ok.setFocusPainted(false);
        ok.setBorderPainted(false);
        ok.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(ok);
        ok.addActionListener(a -> dialog.dispose());
        dialog.setContentPane(panel);
        dialog.setVisible(true); }
}
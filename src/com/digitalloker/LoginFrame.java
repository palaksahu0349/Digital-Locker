package com.digitalloker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.digitallocker.dao.UserDAO;
public class LoginFrame extends JFrame implements ActionListener {
    JTextField username;
    JPasswordField password;
    JButton login, register;
    UserDAO dao = new UserDAO();
    public LoginFrame() {
        setTitle("Digital Locker - Login");
        // WINDOW SIZE
        setSize(1000, 600);
        setLayout(null);
        //  BACKGROUND PANEL 
        JPanel backgroundPanel = new JPanel() {
        protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0, 0,new Color(255, 20, 147),getWidth(), getHeight(),
        new Color(65, 105, 225) );
         g2d.setPaint(gp);
         g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);
        //  DIGITAL LOCKER IMAGE
        ImageIcon img = new ImageIcon("locker.png.png");
        Image image = img.getImage();
        Image scaledImage = image.getScaledInstance(780, 780,Image.SCALE_SMOOTH);
        img = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(img);
        imageLabel.setBounds(20, 90, 620, 420);
        backgroundPanel.add(imageLabel);
        // RIGHT LOGIN PANEL 
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(600, 70, 330, 420);
        panel.setBackground(Color.WHITE);
        panel.setBorder(
        BorderFactory.createLineBorder(new Color(230, 230, 250), 2));
        backgroundPanel.add(panel);
        
        //  TITLE 
        JLabel title = new JLabel("Digital Locker");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(new Color(15, 23, 70));
        title.setBounds(35, 20, 280, 40);
        panel.add(title);
        //  SUBTITLE 
        JLabel sub =new JLabel("Login to access your documents");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        sub.setForeground(new Color(110, 110, 140));
        sub.setBounds(35, 60, 250, 30);
        panel.add(sub);
        //  USERNAME LABEL 
        JLabel l1 = new JLabel("Username");
        l1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        l1.setForeground(new Color(60, 60, 90));
        l1.setBounds(35, 120, 120, 25);
        panel.add(l1);
        //  USERNAME FIELD 
        username = new JTextField();
        username.setBounds(35, 150, 260, 42);
        username.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        username.setBorder(BorderFactory.createLineBorder(
        new Color(220, 220, 240), 2));
        panel.add(username);
        //  PASSWORD LABEL 
        JLabel l2 = new JLabel("Password");
        l2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        l2.setForeground(new Color(60, 60, 90));
        l2.setBounds(35, 210, 120, 25);
        panel.add(l2);
        //  PASSWORD FIELD 
        password = new JPasswordField();
        password.setBounds(35, 240, 260, 42);
        password.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        password.setBorder(
        BorderFactory.createLineBorder(new Color(220, 220, 240), 2)
        );
        panel.add(password);
        //  LOGIN BUTTON 
        login = new JButton("Login");
        login.setBounds(35, 320, 260, 48);
        login.setBackground(new Color(98, 0, 255));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Segoe UI", Font.BOLD, 18));
        login.setFocusPainted(false);
        login.setBorderPainted(false);
        panel.add(login);
        //  REGISTER BUTTON 
        register = new JButton("Register");
        register.setBounds(95, 380, 140, 30);
        register.setFont(new Font("Segoe UI", Font.BOLD, 15));
        register.setForeground(new Color(98, 0, 255));
        register.setBackground(Color.WHITE);
        register.setBorderPainted(false);
        register.setFocusPainted(false);
        panel.add(register);
        //  ACTIONS 
        login.addActionListener(this);
        register.addActionListener(this);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e) {
        String user = username.getText();
        String pass =String.valueOf(password.getPassword());
        dispose();
        //  LOGIN 
        if (e.getSource() == login) {
            // EMPTY FIELD CHECK
            if (user.isEmpty() || pass.isEmpty()) {
                JDialog dialog =new JDialog(this, "Error", true);
                dialog.setSize(350, 180);
                dialog.setLocationRelativeTo(this);
                JPanel panel = new JPanel() {
                protected void paintComponent(Graphics g) {
               super.paintComponent(g);
          Graphics2D g2d =(Graphics2D) g;
          GradientPaint gp =new GradientPaint(0, 0,new Color(255, 99, 132),getWidth(),getHeight(),
             new Color(220, 20, 60));
             g2d.setPaint(gp);
             g2d.fillRect(0,0, getWidth(),getHeight()); }
                };
                panel.setLayout(null);
                JLabel msg =new JLabel("Enter Username & Password");
                msg.setFont(new Font("Segoe UI",Font.BOLD,18));
                msg.setForeground(Color.WHITE);
                msg.setBounds(40, 35, 280, 30);
                panel.add(msg);
                JButton ok = new JButton("OK");
                ok.setBounds(120, 90, 90, 32);
                ok.setBackground(Color.WHITE);
                ok.setForeground(new Color(220, 20, 60));
                ok.setFocusPainted(false);
                ok.setBorderPainted(false);
                ok.setFont(new Font("Segoe UI",Font.BOLD,14));
                panel.add(ok);
                ok.addActionListener(
                        a -> dialog.dispose()
                );
                dialog.setContentPane(panel);
                dialog.setVisible(true);
                return;
            }
            // DATABASE LOGIN CHECK
            boolean success =dao.login(user, pass);
            // SUCCESS LOGIN
            if (success) {
                AppData.currentUser = user;
                new DashboardFrame();
                dispose();
            }

            // INVALID LOGIN
            else {
                JDialog dialog =new JDialog(this,"Login Failed",true);
                dialog.setSize(370, 190);
                dialog.setLocationRelativeTo(this);
                JPanel panel = new JPanel() {
                  protected void paintComponent(Graphics g) {
                  super.paintComponent(g);
                  Graphics2D g2d =(Graphics2D) g;
                  GradientPaint gp = new GradientPaint(0, 0,new Color(255, 120, 120),
                        getWidth(),getHeight(),new Color(178, 34, 34) );
                        g2d.setPaint(gp);
                        g2d.fillRect( 0,  0,getWidth(),getHeight());}
                  };
                panel.setLayout(null);
                JLabel msg =new JLabel("Invalid Username or Password");
                msg.setFont(new Font("Segoe UI",Font.BOLD,19));
                msg.setForeground(Color.WHITE);
                msg.setBounds(28, 38, 320, 30);
                panel.add(msg);
                JButton ok = new JButton("OK");
                ok.setBounds(130, 100, 90, 32);
                ok.setBackground(Color.white);
                ok.setForeground(new Color(178, 34, 34));
                ok.setFocusPainted(false);
                ok.setBorderPainted(false);
                ok.setFont(new Font("Segoe UI",Font.BOLD,20 ));
                panel.add(ok);
                ok.addActionListener(a -> dialog.dispose() );
                dialog.setContentPane(panel);
                dialog.setVisible(true);}
            }
        if (e.getSource() == register) {     //  REGISTER 
            new RegisterFrame(); }
    }
}
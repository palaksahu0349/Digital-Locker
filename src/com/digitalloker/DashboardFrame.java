package com.digitalloker;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.awt.Desktop;
import com.digitallocker.dao.DocumentDAO;
public class DashboardFrame extends JFrame {
    JTable table;
    DefaultTableModel model;
    DocumentDAO dao = new DocumentDAO();
    public DashboardFrame() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            // PURPLE THEME
            UIManager.put("control", new Color(245, 220, 255));
            UIManager.put("info", new Color(245, 220, 255));
            UIManager.put("nimbusBase", new Color(138, 43, 226));
            UIManager.put("nimbusAlertYellow", new Color(255, 20, 147));
            UIManager.put("nimbusFocus", new Color(255, 105, 180));
            UIManager.put("nimbusLightBackground", new Color(255, 240, 250));
            UIManager.put("text", Color.BLACK);  }
           catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Dashboard - " + AppData.currentUser);
        setSize(920, 600);
        setLayout(null);
        //  BACKGROUND PANEL 
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            Color color1 = new Color(255, 105, 180);
            Color color2 = new Color(120, 81, 255);
            GradientPaint gp = new GradientPaint(0, 0, color1,0, getHeight(), color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight()); }
                  };
        panel.setLayout(null);
        setContentPane(panel);
        //  SEARCH FIELD 
        JTextField searchField = new JTextField();
        searchField.setBounds(180, 20, 250, 35);
        searchField.setFont(new Font("Poppins", Font.PLAIN, 14));
        panel.add(searchField);
        //  SEARCH ICON 
        ImageIcon searchIcon = new ImageIcon("search1.png");
        Image simg = searchIcon.getImage();
        Image scaledSimg =simg.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(scaledSimg);
        JLabel searchIconLabel = new JLabel(searchIcon);
        searchIconLabel.setBounds(450, 5, 60, 60);
        panel.add(searchIconLabel);
        //  SEARCH BUTTON 
        JButton search = new JButton("Search");
        search.setBounds(510, 20, 120, 35);
        search.setBackground(new Color(88, 101, 242));
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Poppins", Font.BOLD, 15));
        search.setFocusPainted(false);
        panel.add(search);
        //  UPLOAD ICON 
        ImageIcon uploadIcon = new ImageIcon("upload1.png");
        Image uimg = uploadIcon.getImage();
        Image scaledUimg = uimg.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        uploadIcon = new ImageIcon(scaledUimg);
        JLabel uploadIconLabel = new JLabel(uploadIcon);
        uploadIconLabel.setBounds(70, 70, 60, 60);
        panel.add(uploadIconLabel);
        //  UPLOAD BUTTON 
        JButton upload = new JButton("Upload File");
        upload.setBounds(30, 130, 150, 35);
        upload.setBackground(new Color(157, 78, 221));
        upload.setForeground(Color.WHITE);
        upload.setFont(new Font("Poppins", Font.BOLD, 15));
        upload.setFocusPainted(false);
        panel.add(upload);
        // REFRESH ICON 
        ImageIcon refreshIcon = new ImageIcon("refresh11.png");
        Image rimg = refreshIcon.getImage();
        Image scaledRimg =rimg.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        refreshIcon = new ImageIcon(scaledRimg);
        JLabel refreshIconLabel = new JLabel(refreshIcon);
        refreshIconLabel.setBounds(230, 70, 60, 60);
        panel.add(refreshIconLabel);
        //  REFRESH BUTTON 
        JButton refresh = new JButton("Refresh");
        refresh.setBounds(190, 130, 150, 35);
        refresh.setBackground(new Color(157, 78, 221));
        refresh.setForeground(Color.WHITE);
        refresh.setFont(new Font("Poppins", Font.BOLD, 15));
        refresh.setFocusPainted(false);
        panel.add(refresh);
        //  DELETE ICON 
        ImageIcon deleteIcon = new ImageIcon("delete1.png");
        Image dltimg = deleteIcon.getImage();
        Image scaledDeleteImg = dltimg.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        deleteIcon = new ImageIcon(scaledDeleteImg);
        JLabel deleteIconLabel = new JLabel(deleteIcon);
        deleteIconLabel.setBounds(390, 70, 60, 60);
        panel.add(deleteIconLabel);
        //  DELETE BUTTON 
        JButton delete = new JButton("Delete");
        delete.setBounds(350, 130, 120, 35);
        delete.setBackground(new Color(157, 78, 221));
        delete.setForeground(Color.WHITE);
        delete.setFont(new Font("Poppins", Font.BOLD, 15));
        delete.setFocusPainted(false);
        panel.add(delete);
        //  DOWNLOAD ICON
        ImageIcon downloadIcon = new ImageIcon("download1.png");
        Image dimg = downloadIcon.getImage();
        Image scaledDimg = dimg.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        downloadIcon = new ImageIcon(scaledDimg);
        JLabel downloadIconLabel = new JLabel(downloadIcon);
        downloadIconLabel.setBounds(530, 70, 60, 60);
        panel.add(downloadIconLabel);
        // DOWNLOAD BUTTON 
        JButton downloadBtn = new JButton("Download");
        downloadBtn.setBounds(490, 130, 130, 35);
        downloadBtn.setBackground(new Color(157, 78, 221));
        downloadBtn.setForeground(Color.WHITE);
        downloadBtn.setFont(new Font("Poppins", Font.BOLD, 15));
        downloadBtn.setFocusPainted(false);
        panel.add(downloadBtn);
        //  VIEW ICON 
        ImageIcon viewIcon = new ImageIcon("view2.png");
        Image vimg = viewIcon.getImage();
        Image scaledVimg =
                vimg.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        viewIcon = new ImageIcon(scaledVimg);
        JLabel viewIconLabel = new JLabel(viewIcon);
        viewIconLabel.setBounds(680, 70, 60, 60);
        panel.add(viewIconLabel);
        //  VIEW BUTTON 
        JButton viewBtn = new JButton("View");
        viewBtn.setBounds(640, 130, 120, 35);
        viewBtn.setBackground(new Color(157, 78, 221));
        viewBtn.setForeground(Color.WHITE);
        viewBtn.setFont(new Font("Poppins", Font.BOLD, 15));
        viewBtn.setFocusPainted(false);
        panel.add(viewBtn);
        //Back button
        JButton Back = new JButton("Back");
        Back.setBounds(780, 20, 100, 35);
        Back.setBackground(new Color(88, 101, 242));
        Back.setForeground(Color.WHITE);
        Back.setFont(new Font("Poppins", Font.BOLD, 15));
        Back.setFocusPainted(false);
        panel.add(Back);
        //  TABLE 
        model = new DefaultTableModel(new String[]{"File Name", "Path"}, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(40, 200, 820, 300);
        panel.add(sp);
        //  UPLOAD ACTION 
        upload.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                boolean success = dao.upload(
                        AppData.currentUser,
                        selectedFile.getName(),
                        selectedFile.getAbsolutePath());
                if (success) {
                    JOptionPane.showMessageDialog(this,"File Uploaded Successfully");
                    refresh.doClick(); } 
                else {
                    JOptionPane.showMessageDialog(this, "Upload Failed");}
            }
        });
        //  SEARCH ACTION 
        search.addActionListener(e -> {
            String keyword = searchField.getText();
            model.setRowCount(0);
            ArrayList<String[]> list =dao.searchDocuments(AppData.currentUser,keyword);
            for (String[] row : list) {
                model.addRow(row); }
        });
        // DOWNLOAD ACTION 
        downloadBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JDialog dialog =new JDialog(this, "Warning", true);
                dialog.setSize(360, 190);
                dialog.setLocationRelativeTo(this);
                JPanel warningPanel = new JPanel() {
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2d = (Graphics2D) g;
                        GradientPaint gp = new GradientPaint(0, 0,new Color(255, 94, 98),
                        	getWidth(), getHeight(),new Color(255, 195, 113));
                        g2d.setPaint(gp);
                        g2d.fillRect(0,0,getWidth(),getHeight()); }
                  };
                warningPanel.setLayout(null);
                JLabel msg =new JLabel("Select a file for download ");
                msg.setFont(new Font("Poppins", Font.BOLD, 23));
                msg.setForeground(Color.WHITE);
                msg.setBounds(55, 35, 260, 30);
                warningPanel.add(msg);
                JButton ok = new JButton("OK");
                ok.setBounds(125, 95, 100, 35);
                ok.setBackground(Color.WHITE);
                ok.setForeground(new Color(255, 94, 98));
                ok.setFocusPainted(false);
                warningPanel.add(ok);
                ok.addActionListener(a -> dialog.dispose());
                dialog.setContentPane(warningPanel);
                dialog.setVisible(true);
                return; }
            String path =(String) model.getValueAt(row, 1);
            File file = new File(path);
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File(file.getName()));
            if (chooser.showSaveDialog(this)== JFileChooser.APPROVE_OPTION) {
                try {
                    Files.copy(file.toPath(),chooser.getSelectedFile().toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
                   JOptionPane.showMessageDialog(this, "File Downloaded");} 
                catch (IOException ex) { ex.printStackTrace(); }  }
        });
        //  DELETE ACTION 
        delete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JDialog dialog =new JDialog(this, "Warning", true);
                dialog.setSize(360, 190);
                dialog.setLocationRelativeTo(this);
                JPanel warningPanel = new JPanel() {
                    protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                        //  DELETE WARNING GRADIENT
                     GradientPaint gp = new GradientPaint(0, 0,new Color(255, 81, 47),
                     getWidth(), getHeight(),new Color(221, 36, 118));g2d.setPaint(gp);
                     g2d.fillRect(0,0,getWidth(),getHeight());}
                };
                warningPanel.setLayout(null);
                JLabel msg =new JLabel("Select a file to delete");
                msg.setFont(new Font("Poppins", Font.BOLD, 23));
                msg.setForeground(Color.WHITE);
                msg.setBounds(55, 35, 260, 30);
                warningPanel.add(msg);
                JButton ok = new JButton("OK");
                ok.setBounds(125, 95, 100, 35);
                ok.setBackground(Color.WHITE);
                // OK BUTTON TEXT COLOR
                ok.setForeground(new Color(221, 36, 118));
                ok.setFocusPainted(false);
                ok.setFont(new Font("Poppins", Font.BOLD, 15));
                warningPanel.add(ok);
                ok.addActionListener(a -> dialog.dispose());
                dialog.setContentPane(warningPanel);
                dialog.setVisible(true);
                return;}
            String path = (String) model.getValueAt(row, 1);
            if (dao.delete(path)) {
                JOptionPane.showMessageDialog(this,"File Deleted");
                refresh.doClick(); }
        });
        // VIEW ACTION 
        viewBtn.addActionListener(e -> {
            int row =table.getSelectedRow();
            if (row == -1) {
                JDialog dialog =new JDialog(this, "Warning", true);
                dialog.setSize(360, 190);
                dialog.setLocationRelativeTo(this);
                JPanel warningPanel = new JPanel() {
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2d = (Graphics2D) g;
                        // VIEW WARNING GRADIENT
                        GradientPaint gp = new GradientPaint(0, 0,new Color(0, 198, 255),
                        getWidth(), getHeight(),new Color(0, 114, 255));
                        g2d.setPaint(gp);
                        g2d.fillRect(0,0,getWidth(),getHeight()); }
                };
                warningPanel.setLayout(null);
                JLabel msg =new JLabel("Select a file to view");
                msg.setFont(new Font("Poppins", Font.BOLD, 23));
                msg.setForeground(Color.WHITE);
                msg.setBounds(55, 35, 260, 30);
                warningPanel.add(msg);
                JButton ok = new JButton("OK");
                ok.setBounds(125, 95, 100, 35);
                ok.setBackground(Color.WHITE);
                // 🔥 OK BUTTON TEXT COLOR
                ok.setForeground(new Color(0, 114, 255));
                ok.setFocusPainted(false);
                ok.setFont(new Font("Poppins", Font.BOLD, 15));
                warningPanel.add(ok);
                ok.addActionListener(a -> dialog.dispose());
                dialog.setContentPane(warningPanel);
                dialog.setVisible(true);
                return; }
            try {
                String path = (String) model.getValueAt(row, 1);
                Desktop.getDesktop().open(new File(path)); } 
               catch (Exception ex) {ex.printStackTrace(); }
                  });
        Back.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });
        //  REFRESH ACTION 
        refresh.addActionListener(e -> {
            model.setRowCount(0);
            ArrayList<String[]> list = dao.getDocuments(AppData.currentUser);
            for (String[] row : list) {
                model.addRow(row); }
        });
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
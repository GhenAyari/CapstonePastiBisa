/*
 * Created by JFormDesigner on Wed Oct 29 13:00:36 SGT 2025
 */

package app.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import app.controller.AuthController;

/**
 * @author Asus
 */
public class Login extends JFrame {
    public Login() {
        initComponents();
        wireEvents();
    }

    // ======== METHOD BARU BUAT EVENT BUTTON ==========
    private void wireEvents() {
        TombLogin.addActionListener(e -> onLoginClicked());
        TombRegister.addActionListener(e -> onRegisterClicked());
    }


    private void onLoginClicked() {
        String role = (String) TombRole.getSelectedItem();   // Admin / Teacher / Student
        String username = InputUsernameLogin.getText().trim();
        String password = InputPasswordLogin.getText().trim();

        try {
            if ("Admin".equalsIgnoreCase(role)) {
                int adminId = app.controller.AuthController.loginAdmin(username, password);
                JOptionPane.showMessageDialog(this, "Selamat datang Admin #" + adminId);
                new app.view.AdminDashboard().setVisible(true);
                dispose();
                return;
            }

            if ("Teacher".equalsIgnoreCase(role)) {
                int userId = app.controller.AuthController.loginUser("TEACHER", username, password);
                JOptionPane.showMessageDialog(this, "Login Teacher berhasil (id=" + userId + ")");
                new app.view.TeacherDashboard(userId).setVisible(true);
                dispose();
                return;
            }
            if ("Student".equalsIgnoreCase(role)) {
                int userId = app.controller.AuthController.loginUser("STUDENT", username, password);
                JOptionPane.showMessageDialog(this, "Login Student berhasil (id=" + userId + ")");
                new app.view.StudentDashboard(userId).setVisible(true);
                dispose();
                return;
            }


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Login Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ketika tombol register ditekan (sementara pesan info dulu)
    private void onRegisterClicked() {
        String role = (String) TombRole.getSelectedItem();     // Admin / Teacher / Student
        String name = InputNamaLogin.getText().trim();
        String username = InputUsernameLogin.getText().trim();
        String password = InputPasswordLogin.getText().trim();

        try {
            if ("Admin".equalsIgnoreCase(role)) {
                JOptionPane.showMessageDialog(this, "Admin tidak bisa registrasi di sini.");
                return;
            }
            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama, username, dan password wajib diisi.");
                return;
            }

            if ("Teacher".equalsIgnoreCase(role)) {
                AuthController.registerTeacher(name, username, password);
            } else {
                // kalau nanti mau aktifkan student, ini sudah siap
                AuthController.registerStudent(name, username, password);
            }

            JOptionPane.showMessageDialog(this, "Registrasi berhasil. Menunggu verifikasi admin.");
            // optional: kosongkan field
            InputNamaLogin.setText("");
            InputUsernameLogin.setText("");
            InputPasswordLogin.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Ghendida
        SelamatDatangLogin = new JTextField();
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        InputUsernameLogin = new JTextField();
        InputNamaLogin = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        InputPasswordLogin = new JTextField();
        TombLogin = new JButton();
        TombRegister = new JButton();
        TombRole = new JComboBox<>();

        //======== this ========
        var contentPane = getContentPane();

        //---- SelamatDatangLogin ----
        SelamatDatangLogin.setText("Selamat Datang");
        SelamatDatangLogin.setBackground(new Color(0x0099cc));
        SelamatDatangLogin.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        SelamatDatangLogin.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        SelamatDatangLogin.setForeground(new Color(0x333333));

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x006666));
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
            swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border
            .TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog"
            ,java.awt.Font.BOLD,12),java.awt.Color.red),panel1. getBorder
            ()));panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java
            .beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException
            ();}});

            //======== panel2 ========
            {
                panel2.setBackground(new Color(0x999999));

                //---- label1 ----
                label1.setText("Masukkan Username");
                label1.setBackground(Color.black);
                label1.setForeground(Color.black);

                //---- InputUsernameLogin ----
                InputUsernameLogin.setBackground(new Color(0x009999));

                //---- InputNamaLogin ----
                InputNamaLogin.setBackground(new Color(0x009999));

                //---- label2 ----
                label2.setText("Masukkan Nama");
                label2.setBackground(Color.black);
                label2.setForeground(Color.black);

                //---- label3 ----
                label3.setText("Masukkan Password");
                label3.setBackground(Color.black);
                label3.setForeground(Color.black);

                //---- InputPasswordLogin ----
                InputPasswordLogin.setBackground(new Color(0x009999));

                //---- TombLogin ----
                TombLogin.setText("Login");

                //---- TombRegister ----
                TombRegister.setText("Register");

                //---- TombRole ----
                TombRole.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Admin",
                    "Teacher",
                    "Student"
                }));

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel2Layout.createParallelGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                                .addComponent(InputUsernameLogin, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                                .addComponent(InputNamaLogin, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                                .addComponent(InputPasswordLogin, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(TombLogin)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(TombRegister)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(TombRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(30, Short.MAX_VALUE))
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(InputUsernameLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label2)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(InputNamaLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(InputPasswordLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(TombLogin)
                                .addComponent(TombRegister)
                                .addComponent(TombRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(60, Short.MAX_VALUE))
                );
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap(124, Short.MAX_VALUE)
                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(150, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(SelamatDatangLogin, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(SelamatDatangLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Ghendida
    private JTextField SelamatDatangLogin;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel label1;
    private JTextField InputUsernameLogin;
    private JTextField InputNamaLogin;
    private JLabel label2;
    private JLabel label3;
    private JTextField InputPasswordLogin;
    private JButton TombLogin;
    private JButton TombRegister;
    private JComboBox<String> TombRole;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

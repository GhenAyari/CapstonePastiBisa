/*
 * Created by JFormDesigner on Wed Oct 29 13:00:36 SGT 2025
 */

package app.view;


import app.controller.AuthController;

import javax.swing.*;
import java.awt.*;


public class Login extends JFrame {
    private final AuthController controller = new AuthController();
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

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan password wajib diisi.",
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }


        try {
            if ("Admin".equalsIgnoreCase(role)) {
                int adminId = controller.loginAdmin(username, password);
                JOptionPane.showMessageDialog(this, "Selamat datang Admin #" + adminId);
                new app.view.AdminDashboard().setVisible(true);
                dispose();
                return;
            }

            if ("Teacher".equalsIgnoreCase(role)) {
                int userId = controller.loginUser("TEACHER", username, password);
                JOptionPane.showMessageDialog(this, "Login Teacher berhasil (id=" + userId + ")");
                new app.view.TeacherDashboard(userId).setVisible(true);
                dispose();
                return;
            }
            if ("Student".equalsIgnoreCase(role)) {
                int userId = controller.loginUser("STUDENT", username, password);
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
                controller.registerTeacher(name, username, password);
            } else {
                // kalau nanti mau aktifkan student, ini sudah siap
                controller.registerStudent(name, username, password);
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
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
            EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing
            . border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ),
            java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
            { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName () ))
            throw new RuntimeException( ); }} );

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
                TombRegister.setText("Daftar");

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
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(InputUsernameLogin, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                                        .addComponent(InputNamaLogin)
                                        .addComponent(InputPasswordLogin))
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(TombLogin)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(TombRegister, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(TombRole, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                                    .addGap(43, 43, 43))))
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
                            .addContainerGap(151, Short.MAX_VALUE))
                );
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(258, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(143, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(SelamatDatangLogin, GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
                .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

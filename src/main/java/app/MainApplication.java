package app;
import app.view.Login;

public class MainApplication {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}

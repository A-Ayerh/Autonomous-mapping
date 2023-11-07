import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class BezierCurveApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGui();
        });
    }

    private static void createAndShowGui() {
    JFrame frame = new JFrame("Bezier Curve Drawer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new BezierCurveDrawer());
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(null);

    // Create a menu bar
    JMenuBar menuBar = new JMenuBar();
    frame.setJMenuBar(menuBar);

    // Create a menu
    JMenu menu = new JMenu("Options");
    menuBar.add(menu);

    // Create a menu item for settings
    JMenuItem settingsItem = new JMenuItem("Settings");
    settingsItem.addActionListener(e -> {
        SettingsDialog settingsDialog = new SettingsDialog(frame);
        settingsDialog.setVisible(true);
    });
    menu.add(settingsItem);

    // Now the settings can be accessed via the "Options" menu
    frame.setVisible(true);
}

}

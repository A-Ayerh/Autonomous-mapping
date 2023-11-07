import javax.swing.JFrame;
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
        frame.setVisible(true);
    }
}

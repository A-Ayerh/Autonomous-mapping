package java_gui.beziercurvedrawing;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class PathManagementWindow extends JFrame {
    private final BezierCurveDrawer drawer;
    private final List<JRadioButton> showHideButtons = new ArrayList<>();
    private final List<JButton> editButtons = new ArrayList<>();
    private final List<JButton> deleteButtons = new ArrayList<>();

    public PathManagementWindow(BezierCurveDrawer drawer) {
        this.drawer = drawer;
        setTitle("Your Paths");
        setSize(300, 200); // Set the size of the window
        setLocationRelativeTo(null); // Center the window

        updatePathList();
    }

    public void updatePathList() {
        getContentPane().removeAll(); // Clear the existing content
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (int i = 0; i < drawer.getListOfControlPointLists().size(); i++) {
            final int index = i;
            JPanel pathPanel = new JPanel(new FlowLayout());

            JRadioButton showHideButton = new JRadioButton("Show", true);
            showHideButton.addActionListener(this::togglePathVisibility);
            showHideButtons.add(showHideButton);

            JButton editButton = new JButton("Edit");
            editButton.addActionListener(e -> editPath(index));
            editButtons.add(editButton);

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> deletePath(index));
            deleteButtons.add(deleteButton);

            pathPanel.add(new JLabel("Path " + (i + 1)));
            pathPanel.add(showHideButton);
            pathPanel.add(editButton);
            pathPanel.add(deleteButton);

            panel.add(pathPanel);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        getContentPane().add(scrollPane);

        revalidate();
        repaint();
    }

    private void togglePathVisibility(ActionEvent e) {
        // Implementation will be provided later
    }

    private void editPath(int index) {
        // Implementation will be provided later
    }

    private void deletePath(int index) {
        // Ask the user if they are sure
        int result = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete Path " + (index + 1) + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {
            // Delete the path
            drawer.getListOfControlPointLists().remove(index);
            drawer.repaint();
            updatePathList(); // Update the list of paths
        }
    }

    // Getters and setters if needed
}

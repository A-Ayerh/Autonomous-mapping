// Trying again to write the Java code for the SettingsDialog class to a file

// Define the Java code as a string
//settings_dialog_code = """
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingsDialog extends JDialog {

    private JCheckBox showStartEndPointsCheckBox;
    private JCheckBox showDirectionArrowsCheckBox;
    private JSpinner gridSpinner;
    private JButton colorButton;
    private JButton notesButton;
    private JButton markersButton;
    private JButton applyButton;
    private JButton cancelButton;
    private Color selectedColor = Color.BLACK; // Default color
    private Frame owner; // Add this line to declare the owner field

    public SettingsDialog(Frame owner) {
        super(owner, "Settings", true);
        this.owner = owner; // Assign the passed owner to your field
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        
        // Panel for settings components
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        
        // Initialize components
        showStartEndPointsCheckBox = new JCheckBox("Show Start/End Points");
        showDirectionArrowsCheckBox = new JCheckBox("Show Direction Arrows");
        gridSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 100, 1)); // Example values
        colorButton = new JButton("Choose Curve Color");
        notesButton = new JButton("Edit Notes");
        markersButton = new JButton("Place Markers");
        applyButton = new JButton("Apply");
        cancelButton = new JButton("Cancel");

        // Add components to settings panel
        settingsPanel.add(showStartEndPointsCheckBox);
        settingsPanel.add(showDirectionArrowsCheckBox);
        settingsPanel.add(new JLabel("Grid Size:"));
        settingsPanel.add(gridSpinner);
        settingsPanel.add(colorButton);
        settingsPanel.add(notesButton);
        settingsPanel.add(markersButton);

        // Action listeners for buttons
        colorButton.addActionListener(this::openColorChooser);
        notesButton.addActionListener(this::editNotes);
        markersButton.addActionListener(this::placeMarkers);
        applyButton.addActionListener(this::applySettings);
        cancelButton.addActionListener(e -> this.setVisible(false));

        // Panel for action buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(applyButton);
        buttonPanel.add(cancelButton);

        // Add panels to dialog
        add(settingsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
    }

    private void openColorChooser(ActionEvent e) {
        Color newColor = JColorChooser.showDialog(
            this,
            "Choose Curve Color",
            selectedColor
        );
        if (newColor != null) {
            selectedColor = newColor;
        }
    }

    private void editNotes(ActionEvent e) {
        // Placeholder for notes editing logic
    }

    private void placeMarkers(ActionEvent e) {
        // Placeholder for marker placement logic
    }

    private void applySettings(ActionEvent e) {
        // Placeholder for applying settings logic
        // Update application settings based on user input here
        this.setVisible(false);
    }

    // Getters for settings values (to be used by the main application)
    public boolean shouldShowStartEndPoints() {
        return showStartEndPointsCheckBox.isSelected();
    }

    public boolean shouldShowDirectionArrows() {
        return showDirectionArrowsCheckBox.isSelected();
    }

    public int getGridSize() {
        return (Integer) gridSpinner.getValue();
    }

    public Color getSelectedColor() {
        return selectedColor;
    }
    
    // Other getters and setters as needed
}




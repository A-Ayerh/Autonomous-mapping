package java_gui.beziercurvedrawing;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class BezierCurveDrawer extends JPanel {

    private final List<List<Point>> listOfControlPointLists = new ArrayList<>();
    private List<Point> currentControlPoints = new ArrayList<>();
    private final Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.BLACK}; // Colors for each curve
    private int colorIndex = 0;

    private final JComboBox<String> drawoutSelector = new JComboBox<>();
    private final JButton btnNewDrawout = new JButton("New Drawout");

    private final JButton btnSave = new JButton("Save");
    private final JButton btnYourPaths = new JButton("Your Paths");

    private List<Drawout> drawouts = new ArrayList<>();
    private Drawout currentDrawout;

    // Member variable to keep track of the selected control point
    private Point selectedPoint;

    public BezierCurveDrawer() {
         // Setup the save button
         currentDrawout = new Drawout("Drawout " + (drawouts.size() + 1));

         // Setup the save button
         btnSave.addActionListener(e -> saveCurrentCurve());
         btnSave.setEnabled(false); // Initially disabled until a path is drawable
 
         // Setup the 'Your Paths' button
         btnYourPaths.addActionListener(e -> showPathManagementWindow());
 
         // Add buttons to the panel
         this.setLayout(new BorderLayout());
         JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
         buttonPanel.add(btnSave);
         buttonPanel.add(btnYourPaths);
         this.add(buttonPanel, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add a control point where the user clicks
                if (currentControlPoints.isEmpty() && !listOfControlPointLists.isEmpty()) {
                    // Start the new curve with the last point of the previous one
                    List<Point> lastCurve = listOfControlPointLists.get(listOfControlPointLists.size() - 1);
                    currentControlPoints.add(lastCurve.get(lastCurve.size() - 1));
                }
                currentControlPoints.add(e.getPoint());
                if (currentControlPoints.size() == 4) {
                    // If four points are added (including the starting point from the previous curve),
                    // add them as a complete curve
                    listOfControlPointLists.add(new ArrayList<>(currentControlPoints));
                    // Start a new set of control points, initially empty
                    currentControlPoints = new ArrayList<>();
                    // Change the color index for the next curve
                    colorIndex = (colorIndex + 1) % colors.length;
                }
                repaint(); // Repaint the panel to show the new point and update the curves
            }
            @Override
        public void mousePressed(MouseEvent e) {
            for (List<Point> curve : listOfControlPointLists) {
                for (Point controlPoint : curve) {
                    if (isNearby(e.getPoint(), controlPoint)) {
                        selectedPoint = controlPoint; // Select the point for dragging
                        repaint();
                        return;
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            selectedPoint = null; // Deselect the control point
            repaint();
        }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedPoint != null) {
                    selectedPoint.setLocation(e.getPoint());
                    repaint(); // Repaint to show the updated curve
                }
            }
        });

        
        currentDrawout = new Drawout("Drawout " + (drawouts.size() + 1));

        // Initialize the drawout selector
        drawoutSelector.addItem(currentDrawout.getName());
        drawoutSelector.addActionListener(e -> selectDrawout(drawoutSelector.getSelectedIndex()));

        // Setup the new drawout button
        btnNewDrawout.addActionListener(e -> createNewDrawout());

        // Add the selector and new drawout button to the panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Select Drawout:"));
        topPanel.add(drawoutSelector);
        topPanel.add(btnNewDrawout);
        this.add(topPanel, BorderLayout.NORTH);
    }
// Method to check if a click is near a control point
private boolean isNearby(Point click, Point controlPoint) {
    final int THRESHOLD = 10; // Pixel threshold for selecting a control point
    return click.distance(controlPoint) < THRESHOLD;
}

    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setStroke(new BasicStroke(2));

    // Draw the grid
    drawGrid(g2d);

    // Draw all curves in the current drawout
    for (int i = 0; i < currentDrawout.getCurves().size(); i++) {
        drawBezierCurve(g2d, currentDrawout.getCurves().get(i), colors[i % colors.length]);
    }

    // Draw the currently active curve with the current color
    if (!currentControlPoints.isEmpty()) {
        drawBezierCurve(g2d, currentControlPoints, colors[currentDrawout.getCurves().size() % colors.length]);
    }
    btnSave.setEnabled(currentControlPoints.size() == 4);
}




private void drawGrid(Graphics2D g2d) {
    int scaleFactor = 3; // 3 pixels per centimeter
    int gridSize = 10 * scaleFactor; // Grid size representing 10 centimeters

    // Assume the field is square, use the smaller of width or height
    int fieldSizeInPixels = Math.min(getWidth(), getHeight());
    int numOfGridLines = fieldSizeInPixels / gridSize;

    g2d.setColor(Color.LIGHT_GRAY);
    for (int i = 0; i <= numOfGridLines; i++) {
        int pos = i * gridSize;
        g2d.drawLine(pos, 0, pos, fieldSizeInPixels); // vertical line
        g2d.drawLine(0, pos, fieldSizeInPixels, pos); // horizontal line
    }
}

    private void drawBezierCurve(Graphics2D g2d, List<Point> controlPoints, Color color) {
        // Draw only if we have a complete set of points for the cubic curve
        if (controlPoints.size() == 4) {
            Point p0 = controlPoints.get(0);
            Point p1 = controlPoints.get(1);
            Point p2 = controlPoints.get(2);
            Point p3 = controlPoints.get(3);

            Path2D path = new Path2D.Double();
            path.moveTo(p0.x, p0.y);
            for (double t = 0; t <= 1; t += 0.01) {
                double x = Math.pow(1 - t, 3) * p0.x +
                           3 * Math.pow(1 - t, 2) * t * p1.x +
                           3 * (1 - t) * t * t * p2.x +
                           t * t * t * p3.x;
                double y = Math.pow(1 - t, 3) * p0.y +
                           3 * Math.pow(1 - t, 2) * t * p1.y +
                           3 * (1 - t) * t * t * p2.y +
                           t * t * t * p3.y;
                path.lineTo(x, y);
            }
            g2d.setColor(color);
            g2d.draw(path);
        }

        // Draw the control points
        for (Point point : controlPoints) {
            g2d.setColor(Color.BLACK);
            g2d.fillOval(point.x - 3, point.y - 3, 6, 6);
        }
    }

    private void selectDrawout(int index) {
        if (index >= 0 && index < drawouts.size()) {
            currentDrawout = drawouts.get(index);
            // TODO: Update the drawing area to reflect the selected drawout
            repaint();
        }
    }

    private void createNewDrawout() {
        Drawout newDrawout = new Drawout("Drawout " + (drawouts.size() + 1));
        drawouts.add(newDrawout);
        currentDrawout = newDrawout;
        drawoutSelector.addItem(newDrawout.getName());
        drawoutSelector.setSelectedIndex(drawouts.size() - 1);
        repaint();
    }



    private void saveCurrentCurve() {
        if (currentControlPoints.size() == 4) {
            // Save the current curve to the current drawout
            currentDrawout.addCurve(new ArrayList<>(currentControlPoints));
            currentControlPoints.clear();
            btnSave.setEnabled(false); // Disable the save button until a new curve is drawable
            repaint();
        }
    }
    
    
    public void saveCurrentDrawout() {
        if (!currentDrawout.getCurves().isEmpty()) {
            // Prompt for a name for the drawout
            String name = JOptionPane.showInputDialog(this, "Enter a name for this drawout:", currentDrawout.getName());
            if (name != null && !name.trim().isEmpty()) {
                currentDrawout.setName(name.trim());
                drawouts.add(currentDrawout);
                currentDrawout = new Drawout("Drawout " + (drawouts.size() + 1));
                // TODO: Update UI to reflect new drawout

                drawoutSelector.removeItemAt(drawouts.size() - 1);
        drawoutSelector.addItem(currentDrawout.getName());
        drawoutSelector.setSelectedIndex(drawouts.size() - 1);
            }
        }
    }
    

    private PathManagementWindow pathManagementWindow;

private void showPathManagementWindow() {
    if (pathManagementWindow == null || !pathManagementWindow.isDisplayable()) {
        // Initialize the window if it hasn't been already or if it was closed
        pathManagementWindow = new PathManagementWindow(this);
    }
    // Update the path list in case new paths were added since last opened
    pathManagementWindow.updatePathList();
    // Make the window visible
    pathManagementWindow.setVisible(true);
}

public List<List<Point>> getListOfControlPointLists() {
    return listOfControlPointLists;
}
    // main method remains the same
}

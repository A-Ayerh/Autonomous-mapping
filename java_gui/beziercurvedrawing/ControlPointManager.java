package java_gui.beziercurvedrawing;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ControlPointManager {
    private List<Point> controlPoints = new ArrayList<>();
    private Point selectedPoint = null;

    // Threshold for how close a click must be to a control point to select it
    private static final int SELECTION_THRESHOLD = 10;

    public ControlPointManager() {
    }

    public void addControlPoint(Point point) {
        controlPoints.add(point);
    }

    public List<Point> getControlPoints() {
        return controlPoints;
    }

    public boolean isNearby(Point click, Point controlPoint) {
        return click.distance(controlPoint) < SELECTION_THRESHOLD;
    }

    public void selectControlPoint(Point point) {
        for (Point controlPoint : controlPoints) {
            if (isNearby(point, controlPoint)) {
                selectedPoint = controlPoint;
                break;
            }
        }
    }

    public void moveSelectedPoint(Point point) {
        if (selectedPoint != null) {
            selectedPoint.setLocation(point);
        }
    }

    public void releaseSelectedPoint() {
        selectedPoint = null;
    }

    public Point getSelectedPoint() {
        return selectedPoint;
    }

    // Draws the control points on the panel
    public void draw(Graphics2D g2d) {
        for (Point point : controlPoints) {
            g2d.setColor(Color.BLACK);
            g2d.fillOval(point.x - 3, point.y - 3, 6, 6);
        }
    }
}

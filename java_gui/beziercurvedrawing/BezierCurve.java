package java_gui.beziercurvedrawing;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.List;

public class BezierCurve {
    private List<Point> controlPoints;
    private Color color;

    public BezierCurve(List<Point> controlPoints, Color color) {
        this.controlPoints = controlPoints;
        this.color = color;
    }

    public Path2D calculatePath() {
        Path2D path = new Path2D.Double();
        if (controlPoints.size() == 4) {
            Point p0 = controlPoints.get(0);
            Point p1 = controlPoints.get(1);
            Point p2 = controlPoints.get(2);
            Point p3 = controlPoints.get(3);

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
        }
        return path;
    }

    public void draw(Graphics2D g2d) {
        Path2D path = calculatePath();
        g2d.setColor(color);
        g2d.draw(path);

        // Draw control points
        for (Point point : controlPoints) {
            g2d.setColor(Color.BLACK);
            g2d.fillOval(point.x - 3, point.y - 3, 6, 6);
        }
    }
}

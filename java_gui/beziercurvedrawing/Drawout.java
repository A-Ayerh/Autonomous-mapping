package java_gui.beziercurvedrawing;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Drawout {
    private String name;
    private List<List<Point>> curves = new ArrayList<>();
    private List<Boolean> visibilityFlags = new ArrayList<>();

    public Drawout(String name) {
        this.name = name;
    }

    public void addCurve(List<Point> curve) {
        curves.add(curve);
        visibilityFlags.add(true);  // By default, curves are visible
    }

    public boolean isCurveVisible(int index) {
        return visibilityFlags.get(index);
    }

    public void setCurveVisibility(int index, boolean isVisible) {
        visibilityFlags.set(index, isVisible);
    }

    public List<List<Point>> getCurves() {
        return curves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

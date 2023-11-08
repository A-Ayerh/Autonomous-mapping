package java_gui.beziercurvedrawing;

import java.util.ArrayList;
import java.util.List;

public class DrawoutManager {
    private List<Drawout> drawouts;
    private Drawout currentDrawout;

    public DrawoutManager() {
        drawouts = new ArrayList<>();
        // Initialize with a default drawout
        currentDrawout = new Drawout("Default Drawout");
        drawouts.add(currentDrawout);
    }

    public void addDrawout(Drawout drawout) {
        drawouts.add(drawout);
        currentDrawout = drawout; // Optionally set the newly added drawout as current
    }

    public void removeDrawout(Drawout drawout) {
        drawouts.remove(drawout);
        // Handle the situation where the current drawout is removed
        if (drawout.equals(currentDrawout) && !drawouts.isEmpty()) {
            currentDrawout = drawouts.get(0); // Select another drawout as current
        } else if (drawouts.isEmpty()) {
            currentDrawout = null; // No current drawout available
        }
    }

    public Drawout getCurrentDrawout() {
        return currentDrawout;
    }

    public void setCurrentDrawout(Drawout drawout) {
        if (drawouts.contains(drawout)) {
            currentDrawout = drawout;
        }
    }

    public List<Drawout> getDrawouts() {
        return drawouts;
    }
}

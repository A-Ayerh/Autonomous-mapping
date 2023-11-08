package java_gui.beziercurvedrawing;

public class BezierCurveDrawer {
    // Main class that coordinates everything
    private UIManager uiManager;
    private DrawoutManager drawoutManager;
    private ControlPointManager controlPointManager;
    private DrawingPanel drawingPanel;
    private PathManagementWindow pathManagementWindow;

    public BezierCurveDrawer() {
        drawoutManager = new DrawoutManager();
        controlPointManager = new ControlPointManager();
        drawingPanel = new DrawingPanel(controlPointManager, drawoutManager);
        uiManager = new UIManager(drawingPanel);
        pathManagementWindow = new PathManagementWindow(drawoutManager);

        // Link the managers to UI components if necessary
        // Example: uiManager.setDrawoutManager(drawoutManager);
        // Example: drawingPanel.setPathManagementWindow(pathManagementWindow);

        // Register listeners or callbacks between components
        // Example: drawoutManager.addDrawoutChangeListener(drawingPanel::repaint);
    }

    public void launch() {
        // This method would create the main application window and make it visible.
        // It would probably also set up the initial state, such as loading any existing paths.
        // Example: uiManager.initialize();
        // Example: uiManager.showWindow();
    }

    public static void main(String[] args) {
        BezierCurveDrawer bezierCurveDrawer = new BezierCurveDrawer();
        bezierCurveDrawer.launch();
    }
}
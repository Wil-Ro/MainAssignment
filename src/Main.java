import java.util.Arrays;

/**
 * Basic main class, runs app
 * @version 1.0
 * @author Rosia E Evans
 */
public class Main {

    /**
     * Main method
     * @param args args
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("GUI")) {
            GUIApp app = new GUIApp();
            GUIApp.run();
        }
        App app = new App();
        app.run();
    }
}

// todo name this package


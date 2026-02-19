package sample.java.project;

/**
 * Simple standalone Java application.
 * No external dependencies.
 */
public class SampleJavaProject implements Runnable {

    private static final long PRINT_DELAY = 1000L;

    private String name = "world";
    private boolean loop = false;

    public SampleJavaProject() {
    }

    public SampleJavaProject(String name, boolean loop) {
        this.name = name;
        this.loop = loop;
    }

    public static void main(String[] args) {

        String name = "world";
        boolean loop = false;

        // Simple manual argument parsing
        for (int i = 0; i < args.length; i++) {
            if ("--name".equals(args[i]) && i + 1 < args.length) {
                name = args[i + 1];
                i++;
            } else if ("--loop".equals(args[i])) {
                loop = true;
            } else if ("-h".equals(args[i]) || "--help".equals(args[i])) {
                printHelp();
                return;
            }
        }

        SampleJavaProject app = new SampleJavaProject(name, loop);
        app.run();
    }

    private static void printHelp() {
        System.out.println("Usage:");
        System.out.println("  --name <value>   Set the user's name");
        System.out.println("  --loop           Print endlessly");
        System.out.println("  -h, --help       Show help");
    }

    public void sayHello() {
        System.out.printf("Hello, %s!%n", name);
    }

    @Override
    public void run() {
        do {
            sayHello();
            try {
                Thread.sleep(PRINT_DELAY);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        } while (loop);
    }
}

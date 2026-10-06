public class PlatformInfo {

    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();

        System.out.println("Java Version      : " + System.getProperty("java.version"));
        System.out.println("OS Name           : " + System.getProperty("os.name"));
        System.out.println("Available CPUs    : " + runtime.availableProcessors());
        System.out.println("Max Heap Memory   : " + runtime.maxMemory() / (1024 * 1024) + " MB");
        System.out.println("Free Heap Memory  : " + runtime.freeMemory() / (1024 * 1024) + " MB");
    }
}
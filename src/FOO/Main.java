package FOO;

/**
 * Name: Jerome Sparnaay
 * Date: April 9th, 2022
 * Description: introduction to a multithreaded program
 */


/**
 * tests multithreading class
 */
public class Main {

    /**
     * Creates a cook object and makes it perform multiple of its tasks simultaneously
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Cook cook = new Cook();
        cook.start();
    }
}

import com.google.common.util.concurrent.Monitor;
import java.util.HashMap;
import java.util.Map;

/**
 * a program that calculates the average, minimum, maximum and most
 * frequent value for the last 30 numbers of an infinite stream of numbers produced by “n”
 * threads that generate random numbers between the values of 1 to 10.
 */

public class CalcProcess {
    private static final int MAX_SIZE = 30;
    private final Map<Integer, Integer> map = new HashMap<>();
    private final Monitor monitor = new Monitor();
    private int countNumber = 0;

    //this function checks the condition of the count of numbers generated.
    private final Monitor.Guard listBelowCapacity = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            if (countNumber >= MAX_SIZE) {
                displayResult();
                return false;
            }
            return true;
        }
    };

    //this function adds a new randomnumber to the map and it uses Monitor class from guava to interact between threads
    public void addToList(int randomNum) throws InterruptedException {
        int count = 1;
        try {
            monitor.enterWhen(listBelowCapacity);
            if (map.containsKey(randomNum)) {
                count = map.get(randomNum) + 1;
            }
            map.put(randomNum, count);

        } finally {
            countNumber++;
            monitor.leave();
        }
    }

    //this function calculates and prints the result in the screen
    public void displayResult() {
        int max = 0;
        int min = 10;
        int avg;
        int mostFreqValue = 0;
        int mostFreqCount = 0;
        int total = 0;
        for (Integer key : map.keySet()) {
            int value = map.get(key);
            total = total + key * value;
            if (mostFreqCount < value) {
                mostFreqValue = key;
                mostFreqCount = value;
            }
            if (max < key) {
                max = key;
            }
            if (min > key) {
                min = key;
            }
        }
        avg = total / MAX_SIZE;
        System.out.println("Average: " + avg);
        System.out.println("Maximum: " + max);
        System.out.println("Minimum: " + min);
        System.out.println("Frequent: " + mostFreqValue);
        System.exit(0);
    }


}

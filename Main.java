import java.util.ArrayList;
import java.util.Random;

public class Main {
    public final static int simulationSize = 1000;
    public final static int avgTimePerRequest = 30;

    private static final ArrayList<Request> originalRequests = new ArrayList<>();

    public static void main(String[] args) {
        generateRequestQueue();

        Algorithm algorithm = new FCFS();

        algorithm.startSimulation(originalRequests);
    }

    private static void generateRequestQueue() {
        for (int i = 0; i < simulationSize; i++) {
            originalRequests.add(generateRequest());
        }
    }

    private static Request generateRequest() {
        int timeToComplete;
        int arrivalTime;
        Random random = new Random();

        //Generating timeToComplete
        double p = Math.random();

        if (p < 0.6) {
            //Short request
            timeToComplete = random.nextInt(1, 10);
        } else if (p < 0.9) {
            //Medium request
            timeToComplete = random.nextInt(10, 80);
        } else {
            // Long request
            timeToComplete = random.nextInt(80, 150);
        }

        //Generating arrivalTime
        arrivalTime = random.nextInt(1, simulationSize * avgTimePerRequest);

        return new Request(timeToComplete, arrivalTime);
    }
}
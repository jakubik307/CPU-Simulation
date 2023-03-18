import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Main {
    public final static int simulationSize = 100000;// TODO: 16/03/2023 modifiable
    public final static int avgTimePerRequest = 40;// TODO: 16/03/2023 modifiable
    public final static int timeInterval = 10;// TODO: 16/03/2023 modifiable

    private static final ArrayList<Request> originalRequests = new ArrayList<>();

    public static void main(String[] args) {
        generateRequestQueue();

        Algorithm algorithm = new FCFS();
        algorithm.startSimulation(originalRequests);

        algorithm = new SJF();
        algorithm.startSimulation(originalRequests);

        algorithm = new RR();
        ((RR) algorithm).setTimeInterval(timeInterval);
        algorithm.startSimulation(originalRequests);
    }

    private static void generateRequestQueue() {
        for (int i = 0; i < simulationSize; i++) {
            originalRequests.add(generateRequest());
        }
        originalRequests.sort(Comparator.comparing(Request::getArrivalTime));
    }

    private static Request generateRequest() {
        int timeToComplete;
        int arrivalTime;
        Random random = new Random();

        //Generating timeToComplete
        double p = Math.random();

        // TODO: 16/03/2023 modifiable
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

// TODO: 16/03/2023 ilość zagłodzonych procesów 
import java.util.ArrayList;
import java.util.Comparator;

public abstract class Algorithm {
    protected int currentTime;
    protected int requestSwaps;
    protected int finishedRequests;
    protected ArrayList<Request> simulationQueue;
    protected ArrayList<Request> activeQueue;
    protected Request activeRequest;

    public Algorithm() {
        initialState();
    }

    protected void copyQueue(ArrayList<Request> originalRequests) {
        simulationQueue = new ArrayList<>();
        for (Request request : originalRequests) {
            simulationQueue.add(new Request(request.getTimeToComplete(), request.getArrivalTime()));
        }
    }

    protected void addToActiveQueue() {
        for (Request request : simulationQueue) {
            if (currentTime == request.getArrivalTime()) {
                activeQueue.add(request);
            }
        }
    }

    protected void calculateWaitingTimeInQueue() {
        for (Request request : activeQueue) {
            request.setWaitingTime(request.getWaitingTime() + 1);
        }
    }

    public void printResults() {
        long totalWaitingTime = 0;
        int maxWaitingTime = 0;
        long totalPickUpTime = 0;
        int maxPickUpTime = 0;

        simulationQueue.sort(Comparator.comparing(Request::getArrivalTime));

        for (Request request : simulationQueue) {
            totalWaitingTime += request.getWaitingTime();
            totalPickUpTime += request.getPickUpTime();


            if (request.getWaitingTime() > maxWaitingTime) {
                maxWaitingTime = request.getWaitingTime();
            }
            if (request.getPickUpTime() > maxPickUpTime) {
                maxPickUpTime = request.getPickUpTime();
            }
        }

        System.out.println("Average waiting time: " + totalWaitingTime / simulationQueue.size());
        System.out.println("Max waiting time: " + maxWaitingTime);
        System.out.println("Average pick up time: " + totalPickUpTime / simulationQueue.size());
        System.out.println("Max pick up time: " + maxPickUpTime);
        System.out.println("No. of request swaps: " + requestSwaps);
    }

    public void initialState() {
        this.currentTime = 0;
        this.requestSwaps = 0;
        this.finishedRequests = 0;
        this.simulationQueue = new ArrayList<>();
        this.activeQueue = new ArrayList<>();
        this.activeRequest = null;
    }

    public abstract void startSimulation(ArrayList<Request> requests);
}

import java.util.ArrayList;

public abstract class Algorithm {
    protected int currentTime;
    protected int requestSwaps;
    protected int finishedRequests;
    protected ArrayList<Request> simulationQueue;
    protected ArrayList<Request> activeQueue;
    protected Request activeRequest;

    public Algorithm() {
        this.currentTime = 0;
        this.requestSwaps = 0;
        this.finishedRequests = 0;
        this.simulationQueue = new ArrayList<>();
        this.activeQueue = new ArrayList<>();
        this.activeRequest = null;
    }

    protected void copyQueue(ArrayList<Request> originalRequests) {
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
        int totalWaitingTime = 0;
        int maxWaitingTime = 0;

        for (Request request : simulationQueue) {
            totalWaitingTime += request.getWaitingTime();
            if (request.getWaitingTime() > maxWaitingTime) {
                maxWaitingTime = request.getWaitingTime();
            }
        }

        System.out.println("Average waiting time: " + totalWaitingTime / simulationQueue.size());
        System.out.println("Max waiting time: " + maxWaitingTime);
        System.out.println("No. of request swaps: " + requestSwaps);
    }

    public abstract void startSimulation(ArrayList<Request> requests);
}

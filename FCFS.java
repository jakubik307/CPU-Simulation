import java.util.ArrayList;

public class FCFS extends Algorithm {
    @Override
    public void startSimulation(ArrayList<Request> requests) {

        initialState();
        copyQueue(requests);

        while (finishedRequests < Main.simulationSize) {
            for (Request request : simulationQueue) {
                currentTime = Math.max(request.getArrivalTime(), previousRefreshTime);

                request.setWaitingTime(currentTime - request.getArrivalTime());
                request.setPickUpTime(request.getWaitingTime());

                previousRefreshTime = currentTime + request.getTimeToComplete();

                request.setTimeToComplete(0);

                requestSwaps++;
                finishedRequests++;
            }
        }

        System.out.println("FCFS");
        printResults();
        System.out.println("----------");
    }
}
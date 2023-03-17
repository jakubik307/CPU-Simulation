import java.util.ArrayList;

public class FCFS extends Algorithm {
    @Override
    public void startSimulation(ArrayList<Request> requests) {

        initialState();
        copyQueue(requests);

        while (finishedRequests < Main.simulationSize) {
            addToActiveQueue();

            //Add request from queue to processing
            if (activeQueue.size() != 0 && activeRequest == null) {
                activeRequest = activeQueue.get(0);
                activeQueue.remove(activeRequest);
                requestSwaps++;
            }

            //Processing active request
            if (activeRequest != null) {
                if (activeRequest.getPickUpTime() == 0) {
                    activeRequest.setPickUpTime(currentTime - activeRequest.getArrivalTime());
                }

                activeRequest.setTimeToComplete(activeRequest.getTimeToComplete() - 1);

                if (activeRequest.getTimeToComplete() == 0) {
                    activeRequest = null;
                    finishedRequests++;
                }
            }

            calculateWaitingTimeInQueue();

            currentTime++;
        }

        System.out.println("FCFS");
        printResults();
        System.out.println("----------");
    }
}

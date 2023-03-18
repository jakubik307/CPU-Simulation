import java.util.ArrayList;
import java.util.Comparator;

public class SJF extends Algorithm {
    @Override
    public void startSimulation(ArrayList<Request> requests) {

        initialState();
        copyQueue(requests);

        while (finishedRequests < Main.simulationSize) {
            cycleTime = 1;

            addToActiveQueue();
            previousRefreshTime = currentTime;

            //Add request from queue to processing
            if (activeQueue.size() > 0) {
                activeQueue.sort(Comparator.comparing(Request::getTimeToComplete));

                if (activeRequest != activeQueue.get(0)) {
                    requestSwaps++;
                }

                activeRequest = activeQueue.get(0);

                //PickUp time
                if (activeRequest.getPickUpTime() == 0) {
                    activeRequest.setPickUpTime(currentTime - activeRequest.getArrivalTime());
                }

                if (activeRequest != null) {
                    activeRequest.setTimeToComplete(activeRequest.getTimeToComplete() - 1);
                    if (activeRequest.getTimeToComplete() == 0) {
                        activeQueue.remove(activeRequest);
                        finishedRequests++;
                    }
                }
            }

            calculateWaitingTimeInQueue(1);

            currentTime++;
        }

        System.out.println("SJF");
        printResults();
        System.out.println("----------");
    }
}


import java.util.ArrayList;
import java.util.Comparator;

public class SJF extends Algorithm {
    @Override
    public void startSimulation(ArrayList<Request> requests) {
        copyQueue(requests);

        while (finishedRequests < Main.simulationSize) {

            addToActiveQueue();

            //Add request from queue to processing
            if (activeQueue.size() != 0) {
                activeQueue.sort(Comparator.comparing(Request::getTimeToComplete));

                if (activeRequest != activeQueue.get(0)) {
                    requestSwaps++;
                }

                activeRequest = activeQueue.get(0);

                if (activeRequest != null) {
                    activeRequest.setTimeToComplete(activeRequest.getTimeToComplete() - 1);
                    if (activeRequest.getTimeToComplete() == 0) {
                        activeQueue.remove(activeRequest);
                        finishedRequests++;
                    }
                }
            }

            calculateWaitingTimeInQueue();

            currentTime++;
        }

        System.out.println("SJF");
        printResults();
        System.out.println("----------");
    }


}


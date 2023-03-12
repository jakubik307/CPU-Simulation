import java.util.ArrayList;
import java.util.Comparator;

public class SJF extends Algorithm {
    @Override
    public void startSimulation(ArrayList<Request> requests) {
        copyQueue(requests);
        simulationQueue.sort(Comparator.comparing(Request::getArrivalTime));//TODO delete?

        while (finishedRequests < Main.simulationSize) {

            addToActiveQueue();

            //Add request from queue to processing
            if (activeQueue.size() != 0 && activeRequest == null) {
                activeQueue.sort(Comparator.comparing(Request::getTimeToComplete));
                activeRequest = activeQueue.get(0);
                activeQueue.remove(activeQueue.get(0));
                requestSwaps++;
            }

            //Processing active request
            if (activeRequest != null) {
                activeRequest.setTimeToComplete(activeRequest.getTimeToComplete() - 1);

                if (activeRequest.getTimeToComplete() == 0) {
                    activeRequest = null;
                    finishedRequests++;
                } else {

                }
            }

            calculateWaitingTime();


            currentTime++;
        }

        System.out.println("SJF");
        printResults();
        System.out.println("----------");
    }


}


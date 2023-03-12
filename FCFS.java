import java.util.ArrayList;
import java.util.Comparator;

public class FCFS extends Algorithm {
    @Override
    public void startSimulation(ArrayList<Request> requests) {
        copyQueue(requests);
        simulationQueue.sort(Comparator.comparing(Request::getArrivalTime));//TODO delete?

        while (finishedRequests < Main.simulationSize) {

            addToActiveQueue();

            //Add request from queue to processing
            if (activeQueue.size() != 0 && activeRequest == null) {
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
                }
            }

            //Calculate waiting time
            for (Request request : activeQueue) {
                request.setWaitingTime(request.getWaitingTime() + 1);
            }


            currentTime++;
        }

        System.out.println("FCFS");
        printResults();
        System.out.println("----------");
    }


}

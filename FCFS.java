import java.util.ArrayList;

public class FCFS extends Algorithm {
    @Override
    public void startSimulation(ArrayList<Request> requests) {
        copyQueue(requests);

        while (finishedRequests < Main.simulationSize) {

            addToActiveQueue();

            //Add request from queue to processing
            if (activeQueue.size() != 0 && activeRequest == null) {
                activeRequest = activeQueue.get(0);
                activeQueue.remove(activeQueue.get(0));
                requestSwaps++;
            }

            //Processing active request
            else if (activeRequest != null) {
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

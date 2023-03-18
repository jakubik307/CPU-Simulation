import java.util.ArrayList;

public class FCFS extends Algorithm {
    @Override
    public void startSimulation(ArrayList<Request> requests) {

        initialState();
        copyQueue(requests);

        while (finishedRequests < Main.simulationSize) {
            cycleTime = 1;

            addToActiveQueue();
            previousRefreshTime = currentTime;

            //Add request from queue to processing
            if (activeQueue.size() > 0 && activeRequest == null) {
                activeRequest = activeQueue.get(0);
                activeQueue.remove(activeRequest);
                requestSwaps++;
            }

            //Processing active request
            if (activeRequest != null) {
                //PickUpTime
                if (activeRequest.getPickUpTime() == 0) {
                    activeRequest.setPickUpTime(currentTime - activeRequest.getArrivalTime());
                }

                cycleTime = activeRequest.getTimeToComplete();
                activeRequest.setTimeToComplete(0);
                activeRequest = null;
                finishedRequests++;
            }

            calculateWaitingTimeInQueue(cycleTime);

            currentTime += cycleTime;
        }

        System.out.println("FCFS");
        printResults();
        System.out.println("----------");
    }
}
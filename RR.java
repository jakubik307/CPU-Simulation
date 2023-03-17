import java.util.ArrayList;

public class RR extends Algorithm {
    private int timeInterval;
    private int previousRefreshTime = 0;

    @Override
    public void startSimulation(ArrayList<Request> requests) {

        initialState();
        copyQueue(requests);

        while (finishedRequests < Main.simulationSize) {
            int cycleTime = 0;

            addToActiveQueue();
            previousRefreshTime = currentTime;

            if (activeQueue.size() > 0) {

                requestSwaps += activeQueue.size();

                for (int i = 0; i < activeQueue.size(); i++) {

                    activeRequest = activeQueue.get(i);
                    activeRequest.setWaitingTime(activeRequest.getWaitingTime() + cycleTime);

                    if (activeRequest.getPickUpTime() == 0) {
                        activeRequest.setPickUpTime(currentTime - activeRequest.getArrivalTime() + cycleTime);
                    }

                    cycleTime += Math.min(activeRequest.getTimeToComplete(), timeInterval);

                    if (activeRequest.getTimeToComplete() <= timeInterval) {
                        //Process is going to finish
                        activeRequest.setTimeToComplete(0);
                        activeQueue.remove(i);
                        i--;
                        finishedRequests++;
                    } else {
                        //Process needs to take more rounds
                        activeRequest.setTimeToComplete(activeRequest.getTimeToComplete() - timeInterval);
                    }
                }
            }
            currentTime += Math.max(cycleTime, 1);
        }

        System.out.println("RR");
        printResults();
        System.out.println("----------");
    }

    @Override
    protected void addToActiveQueue() {
        for (Request request : simulationQueue) {
            if (request.getArrivalTime() > previousRefreshTime && request.getArrivalTime() <= currentTime) {
                activeQueue.add(request);
                request.setWaitingTime(currentTime - request.getArrivalTime());
            }
        }
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }
}

public class Request {

    private int timeToComplete;
    private final int arrivalTime;
    private int pickUpTime;//TODO look if you need it
    private int waitingTime;

    public Request(int timeToComplete, int arrivalTime) {
        this.timeToComplete = timeToComplete;
        this.arrivalTime = arrivalTime;
        this.pickUpTime = 0;
        this.waitingTime = 0;
    }

    public int getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(int timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(int pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }
}

package lift;

import java.util.ArrayList;

public class Elevator {
    
    private final int CAPACITY;
    private final int MAXFLOOR;
    private final int MINFLOOR;
    private int currentFloor=0;
    private boolean isBusy=false;
    private ElevatorState state;
    private ArrayList<Integer> requests=new ArrayList<>();
    private int numberOfPerson=0;
    private int singleRequest;
    
    public Elevator(int CAPACITY, int MAXFLOOR, int MINFLOOR) {
        this.CAPACITY=CAPACITY;
        this.MAXFLOOR=MAXFLOOR;
        this.MINFLOOR=MINFLOOR;
    }
    
     public void addFloorRequest(int stopRequest) {
        if(stopRequest>MAXFLOOR) {
            System.out.println("Request: "+stopRequest+" is over the maximum floor. So your "
                    + "request was changed to 5.");
            requests.add(MAXFLOOR);
        }
        else if(stopRequest<MINFLOOR) {
            System.out.println("Request: "+stopRequest+" is under the minimum floor. So your "
                    + "request was changed to 0.");
            requests.add(MINFLOOR);
        }
        else {
            System.out.println("Request: "+stopRequest);
            requests.add(stopRequest);
        }
    }
    
    public void startEngine() {
       Integer[] requestsArray=new Integer[requests.size()];
       requestsArray=requests.toArray(requestsArray);
       
       requestsArray=sortArray(requestsArray);
       
       System.out.println("");
       System.out.println("You are on the "+currentFloor+"th floor.");
       
       for(int i=0;i<requestsArray.length;i++) {
            while(currentFloor-requestsArray[i]!=0) {
                if((currentFloor-requestsArray[i])>0) goDown();
                else if((currentFloor-requestsArray[i])<0) goUp();
            }
            System.out.println("Elevator stopped.");
        }
        
       stopEngine();
    }
    
    public void stopEngine() {
        System.out.println("Engine has stopped.");
        state=ElevatorState.Idle;
    }
    
    public Integer[] sortArray(Integer[] requestsArray) {
        Integer[] sortedArray=new Integer[requestsArray.length];
        int distance, min=Math.abs(requestsArray[0]-currentFloor), before=-1, floor=0;
        sortedArray[0]=requestsArray[0];
        
        for(int j=0;j<requestsArray.length;j++) {
            for(int i=0;i<requestsArray.length;i++) {
                distance=Math.abs(requestsArray[i]-currentFloor);
                if(distance<min&&distance>before) {
                    min=distance;
                    floor=requestsArray[i];
                }
            }
            sortedArray[j]=floor;
            before=sortedArray[j];
            min=100;
        }
        
        return sortedArray;
    }
    
    public void getOnPerson() {
        if(numberOfPerson<CAPACITY) {
            numberOfPerson++;
        }
        else System.out.println("Maximum number of allowed passenger is reached!");
    }
    
    public void getOffPerson() {
        if(numberOfPerson>0) numberOfPerson--;
        else System.out.println("There is no passenger in the elevator!");
    }
    
    public void tellTheFloor() {
        System.out.println(state+" to "+currentFloor);
    }
    
    public void goUp() {
        if(currentFloor<MAXFLOOR) {
            currentFloor++;
            state=ElevatorState.GoingUp;
            isBusy=true;
            tellTheFloor();
        }
        else System.out.println("You can not go up further!");
    }
    
    public void goDown() {
        if(currentFloor>MINFLOOR) {
            currentFloor--;
            state=ElevatorState.GoingDown;
            isBusy=true;
            tellTheFloor();
        }
        else System.out.println("You can not go down further!");
    }
    
}
package lift;

public class Tester {
    public static void main(String[] args) {
        Elevator myElevator=new Elevator(4, 7, 0);
        
        myElevator.addFloorRequest(6);
	myElevator.addFloorRequest(2);
	myElevator.addFloorRequest(4);
	myElevator.addFloorRequest(7);
        myElevator.addFloorRequest(10);
        
	myElevator.startEngine();
        
        
    }
}

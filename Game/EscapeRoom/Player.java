import java.util.Stack;
import java.util.ArrayList;


public class Player {
    private Stack<Room> moveHistory;
    private ArrayList<Item> inverntory;
    private Room currentRoom;

    //methods
    public void moveTo(Room r){
        moveHistory.push(r);
    }

    public void goBack(){
        moveHistory.pops();
    } //pops from stack

    public void pickupItem(String name){
        inverntory.add(name);
    }

    public boolean hasKey(String keyName){
        
    }
}

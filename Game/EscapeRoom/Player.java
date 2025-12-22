import java.util.Stack;
import java.util.ArrayList;


public class Player {
    private Stack<Room> moveHistory;
    private ArrayList<Item> inverntory;
    private Room currentRoom;

    //methods
    void moveTo(Room r){
        moveHistory.push(r);
    }

    void goBack(){
        moveHistory.pops();
    } //pops from stack

    void pickupItem(String name){
        inverntory.add(name);
    }

    boolean hasKey(String keyName){
        
    }
}

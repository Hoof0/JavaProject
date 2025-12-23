import java.util.Stack;
import java.util.ArrayList;


public class Player {
    private Stack<Room> moveHistory;
    private ArrayList<Item> inventory;
    private Room currentRoom;

    Player(Stack<Room> moveHistory, ArrayList<Item> inventory, Room currentRoom){
        this.moveHistory = moveHistory;
        this.inventory = inventory;
        this.currentRoom = currentRoom;
    }

    //methods
    public void moveTo(Room r){
        moveHistory.push(r);
        currentRoom = r;
    }

    public void goBack(){
        moveHistory.pop();
    } //pops from stack

    public void pickupItem(String name){
        
        inventory.add();
    }

    public boolean hasKey(String keyName){
        for (Item i : inventory)
        {
            if (i.getName() == keyName)
            {
                return true;
            }
        }
        return false;
    }
}

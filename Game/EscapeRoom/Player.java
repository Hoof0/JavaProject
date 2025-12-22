package Game.EscapeRoom;

import java.util.Stack;
import java.util.ArrayList;


public class Player {
    private Stack<Room> moveHistory;
    private ArrayList<Item> inventory;
    private Room currentRoom;

    //methods
    void moveTo(Room r){
        moveHistory.push(r);
    }

    void goBack(){
        moveHistory.pops();
    } //pops from stack

    void pickupItem(String name){
        inventory.add(name);
    }

    boolean hasKey(String keyName){
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

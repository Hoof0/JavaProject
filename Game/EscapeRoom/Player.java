import java.util.Stack;
import java.util.ArrayList;


public class Player {
    private Stack<Room> moveHistory;
    private ArrayList<Item> inventory;
    private Room currentRoom;

    Player(Room currentRoom){
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }


    public ArrayList<Item> getInventory(){
        return inventory;
    }


    //methods
    public void moveTo(Room r){
        
        ArrayList<Room> temp = currentRoom.getConnectedRoom();
        for (int i = 0; i < temp.size(); i++){
            if(r == temp.get(i)){
                moveHistory.push(r);
                currentRoom = r;
            }
            else {
                System.err.println("Room is not connected");
            }
        }

    }

    public void goBack(){
        if (!moveHistory.isEmpty())
        {
            currentRoom = moveHistory.pop();
        }
        else
        {
            System.err.println("Cannot go back.");
        }
    } //pops from stack

    public void pickupItem(Item i){
        inventory.add(i);
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

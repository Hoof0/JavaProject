import java.util.ArrayList;
import java.util.HashSet;

public class Room 
{
    private ArrayList<GameComponent> contents;
    private ArrayList<Room> connectedRooms;
    private boolean isExit;
    private String roomName;


    public Room(String roomName)
    {
        this.contents = new ArrayList<>();
        this.connectedRooms = new ArrayList<>();
        this.roomName = roomName;
        isExit = false;
    }

    public Room(String name, boolean exit)
    {
        this.contents = new ArrayList<>();
        this.connectedRooms = new ArrayList<>();
        this.roomName = name;
        this.isExit = exit;
    }

    public boolean getExit()
    {
        return isExit;
    }

    public ArrayList<Room> getConnectedRoom(){
        return connectedRooms;
    }

    public String getRoomName()
    {
        return roomName;
    }

    public ArrayList<GameComponent> getContents()
    {
        return contents;
    }

    public void setExit(boolean exit)
    {
        isExit = exit;
    }

    public void setRoomName(String name)
    {
        roomName = name;
    }

    public void addConnectedRoom(Room r)
    {
        getConnectedRoom().add(r);
    }

    private void exploreRecursiveHelp(int depth, HashSet<Room> visited){
        if (visited.contains(this)){
            return;
        }

        visited.add(this);

        System.out.println("Room info:\n" + this.toString());

        if(depth == 0){
            return;
        }

        for(int i = 0; i < connectedRooms.size(); i++)
        {
            connectedRooms.get(i).exploreRecursiveHelp(depth - 1, visited);
        }
    } 
    
    public void exploreRecursive(int depth)
    {
        exploreRecursiveHelp(depth, new HashSet<Room>());

    }

    public boolean containsItemRecursive(String itemName) {
    return containsItemRecursiveHelper(itemName, new HashSet<Room>());
    }

    private boolean containsItemRecursiveHelper(String itemName, HashSet<Room> visited) {

    if (visited.contains(this)) {
        return false;
    }
    
    visited.add(this);
    
    for(int i = 0; i < contents.size(); i++) {
        if(contents.get(i).getName().toLowerCase().equals(itemName)) {
            return true;
        }
    }

    for(int i = 0; i < connectedRooms.size(); i++) {
        if(connectedRooms.get(i).containsItemRecursiveHelper(itemName, visited)) {
            return true;
        }
    }
    
    return false;        
    }

    private int maxDepthRecursiveHelp(HashSet<Room> visted){
        if (visted.contains(this)){
            return 0;
        }

        visted.add(this);

        int maxRoomDepth = 0;

        for(int i = 0; i < connectedRooms.size(); i++)
        {
            maxRoomDepth = Math.max(maxRoomDepth, connectedRooms.get(i).maxDepthRecursive());
        }
        return maxRoomDepth + 1;
    }

    public int maxDepthRecursive()
    {
        return maxDepthRecursiveHelp(new HashSet<>());
    }

    public String toString()
    {
        String string = "";
        string += "Contents:\n";

        System.out.println("====" + getRoomName() + "====");

        if(contents.isEmpty())
        {
            string += "- Nothing in this room.";
        }
        else
        {
            string += "- ";
            for (int i = 0; i < contents.size(); i++) 
            {
                string += contents.get(i).getName();
                if(i < contents.size() - 1) // the last one will not have ","
                {
                    string += ", ";
                }
            }
        }

        string += "\nConnected Rooms:\n";
        if(connectedRooms.isEmpty())
        {
            string += "- This room is not connected to any room.";
        }
        else
        {
            string += "- ";
            for (int i = 0; i < connectedRooms.size(); i++) 
            {
                string += connectedRooms.get(i).getRoomName();
                if(i < connectedRooms.size() - 1) 
                {
                    string += ", ";
                }
            }
        }

        if(isExit)
        {
            string += "\n The room is an exit";
        }
        else
        {
            string += "\nRoom is not the exit";
        }
        return string;
    }
}

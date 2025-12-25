import java.util.ArrayList;

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
        roomName = name;
        isExit = exit;
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
    
    public void exploreRecursive(int depth)
    {
        if(depth == 0)
        {
            System.out.println("Room info:\n " + this.toString()); // Example: print room details
            return;
        }
        if(connectedRooms.isEmpty())
        {
            System.out.println("Room info:\n" + this.toString());
        }
        for(int i = 0; i < connectedRooms.size(); i++)
        {
            connectedRooms.get(i).exploreRecursive(depth - 1);
        }
    }

    public boolean containsItemRecursive(String itemName) // check if the room and its subroom contain an item
    {
        for(int i = 0; i < contents.size(); i++)
        {
            if(contents.get(i).getName().equals(itemName))
            {
                return true;
            }
        }

        for(int i = 0; i < connectedRooms.size(); i++)
        {
            if(connectedRooms.get(i).containsItemRecursive(itemName))
            {
                return true;
            }
        }
        return false;        
    }

    public int maxDepthRecursive()
    {
        int maxRoomDepth = 0;
        for(int i = 0; i < connectedRooms.size(); i++)
        {
            maxRoomDepth = Math.max(maxRoomDepth, connectedRooms.get(i).maxDepthRecursive());
        }
        return maxRoomDepth + 1;
    }

    public String toString()
    {
        String string = "";
        string += "Contents:\n";

        if(contents.isEmpty())
        {
            string += "- Nothing in this room.";
        }
        else
        {
            ArrayList<Item> items = new ArrayList<Item>(); //separate items from puzzles to prevent the program from confusing
            ArrayList<Puzzle> puzzles = new ArrayList<Puzzle>();
            for (int i = 0; i < contents.size(); i++) 
            {
                if (contents.get(i) instanceof Item)
                {
                    items.add((Item)contents.get(i));
                }
                else if (contents.get(i) instanceof Puzzle)
                {
                    puzzles.add((Puzzle)contents.get(i));
                }
            }

            if (!items.isEmpty()) //display items
            {
                string += "Items: ";
                for (int i = 0; i < items.size(); i++)
                {
                    string += items.get(i).getName();
                    if (i < items.size() - 1)
                    {
                        string += ", "; //the last one will not have ,
                    }
                }
                string += "\n";
            }

            if (!puzzles.isEmpty()) //display puzzles
            {
                string += "Puzzles: ";
                for (int i = 0; i < puzzles.size(); i++)
                {
                    string += puzzles.get(i).getName();
                    if (i < puzzles.size() - 1)
                    {
                        string += ", "; //the last one will not have ,
                    }
                }
                string += "\n";
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

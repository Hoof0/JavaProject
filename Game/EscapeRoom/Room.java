import java.util.ArrayList;

public class Room 
{
    private ArrayList<GameComponent> contents;
    private ArrayList<Room> connectedRooms;
    private boolean isExit;
    
    public void exploreRecursive(int depth)
    {
        exploreRecursive(depth);
        for(int i = 0; i < connectedRooms.size(); i++)
        {
            exploreRecursive(depth);
        }
    }

    public boolean containsItemRecursive(String itemName)
    {
        
    }

    public int maxDepthRecursive()
    {
        int maxRoomDepth = 0;
        for(int i = 0; i < connectedRooms.size(); i++)
        {
            int connectedRoomDepth = maxDepthRecursive();
            int maxConnectedDepth = Math.max(maxRoomDepth, connectedRoomDepth);
        }

        return maxConnectedDepth + 1;
    }
}

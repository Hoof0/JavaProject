

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class GameEngine {
    private boolean Win = false;
    private ArrayList<Room> map;
    private Player player;
    Queue<String> hint; 

    void start(){
        while (Win != true){

        }
    }

    void processCommand(String cmd){
        Scanner scanner = new Scanner(System.in);
        cmd = cmd.toLowerCase();
        Room curr = player.getCurrentRoom();
        switch (cmd) {
            case "look":
                curr.toString();
                break;
            case "move":
                ArrayList<Room> connectRoom = curr.getConnectedRoom();
                for(int i = 0; i < connectRoom.size(); i++){
                    System.out.println(connectRoom.get(i).toString());
                }
                System.out.println("Where would you like to move to");
                String destination = scanner.nextLine();
                destination = destination.toLowerCase();
                for(int i = 0; i < connectRoom.size(); i++){
                    if (destination == connectRoom.get(i).getRoomName().toLowerCase()){
                        player.moveTo(connectRoom.get(i));
                    }
                }
                scanner.close();
                break;
            case "back":
                player.goBack();
                break;
            case "pickup":
                ArrayList<GameComponent> roomContents = curr.getContents();
                if(roomContents.isEmpty())
                {
                    System.out.println("No items in this room.");
                }
                else
                {
                    System.out.println("Items to pick up: ");
                    for(int i = 0; i < roomContents.size(); i++)
                    {
                        System.out.println(i + "." + roomContents.get(i).getName());
                    }
                    System.out.print("Enter the name of the item you want to pick up: ");
                    String itemName = scanner.nextLine().trim().toLowerCase();
                    boolean found = false;

                    for(int i = 0; i < roomContents.size(); i++)
                    {
                        if(roomContents.get(i).getName().toLowerCase().equals(itemName))
                        {
                            player.pickupItem((Item)roomContents.get(i));
                        }
                        roomContents.remove(i);
                        System.out.println("Picked up successfully.");
                        found = true;
                        break;
                    }

                    if(!found)
                    {
                        System.out.println("Item not found in this room.");
                    }
                }
                break;
            default:
                break;
        }
    }
}

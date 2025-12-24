

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;


public class GameEngine {
    private boolean Win = false;
    private Player player;
    Queue<String> hint;
    private Scanner scanner;

    public GameEngine(Player player){
        this.player = player;
        this.scanner = new Scanner(System.in);
    }

    void start(){
        while (Win != true){
            String input = scanner.next();
            processCommand(input);
        }
    }


    private void helpMap(Room room, HashSet<Room> visited, int depth){
        if (visited.contains(room)){
            return;
        }
        visited.add(room);

        String space = " ".repeat(depth);
        System.out.println(space + "- " + room.getRoomName() + 
                          (room == player.getCurrentRoom() ? " (YOU ARE HERE)" : "") +
                          (room.getExit() ? " [EXIT]" : ""));
    }

    public void processCommand(String cmd){
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
                String destination = scanner.next();
                destination = destination.toLowerCase();
                for(int i = 0; i < connectRoom.size(); i++){
                    if (destination.equals(connectRoom.get(i).getRoomName().toLowerCase())){
                        player.moveTo(connectRoom.get(i));
                    }
                }
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
                    String itemName = scanner.next().trim().toLowerCase();
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
            case "inventory":
                ArrayList<Item> tempIven = player.getInventory();
                System.out.println("Iventory: ");
                for (int i = 0; i < tempIven.size(); i ++){
                    System.out.println(tempIven.get(i).getName());
                }
                break;
            case "solve":
                System.out.println("Name of puzzle you want to solve: ");
                String puzzleName = scanner.next();
                System.out.println("Enter your answer: ");
                String answer = scanner.next();
                GameComponent currContent;
                
                for (int i = 0; i < curr.getContents().size(); i++){
                    currContent = curr.getContents().get(i);
                    
                    if(currContent.getName().equals(puzzleName)){
                        Puzzle puzzle = (Puzzle) currContent;
                        if (puzzle.attemptSolve(answer)){
                            System.out.println("Your answer is correct");
                        }
                        else {
                            System.out.println("Your answer is incorect");
                        }
                    }
                else {
                    System.out.println("No item of that name found");
                }
                }
                break;
            case "map":
                System.out.println("Map: ");
                HashSet<Room> visted = new HashSet<>();
                helpMap(curr, visted, 0);
                break;
            default:
                System.out.println("Invalid comand");
                break;
        }
    }
}

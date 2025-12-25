

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class GameEngine {
    private boolean Win = false;
    private Player player;
    Queue<String> hint;
    private Scanner scanner;
    private int failTries = 0;

    public GameEngine(Player player){
        this.player = player;
        this.scanner = new Scanner(System.in);
        this.hint = new LinkedList<>();
    }

    void start(){
        System.out.println("Text-based escape room game.\nCommands: look, move, back, pickup, inventory, solve, map, quit.\nFind the exit room to win.");
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

        String space = " ".repeat(depth * 2);
        System.out.println(space + "- " + room.getRoomName() + 
                          (room == player.getCurrentRoom() ? " (YOU ARE HERE)" : "") +
                          (room.getExit() ? " [EXIT]" : ""));

        //display connected rooms
        for (Room connected : room.getConnectedRoom())
        {
            helpMap(connected, visited, depth + 1);
        }
    }

    public void processCommand(String cmd){
        cmd = cmd.toLowerCase();
        Room curr = player.getCurrentRoom();
        switch (cmd) {
            case "look":
                System.out.println(curr.toString());
                break;
            case "move":
                ArrayList<Room> connectRoom = curr.getConnectedRoom();
                if (connectRoom.isEmpty())
                {
                    System.out.println("No connected rooms");
                    break;
                }
                for(int i = 0; i < connectRoom.size(); i++){
                    System.out.println(connectRoom.get(i).getRoomName());
                }
                System.out.println("Where would you like to move to?");
                String destination = scanner.nextLine().trim().toLowerCase();
                boolean moved = false;
                for(int i = 0; i < connectRoom.size(); i++){
                    if (destination.equals(connectRoom.get(i).getRoomName().toLowerCase())){
                        player.moveTo(connectRoom.get(i));
                        System.out.println("Moved.");
                        moved = true;
                        break;
                    }
                }

                if (!moved)
                {
                    System.out.println("Invalid room name");
                }
                break;
            case "back":
                player.goBack();
                System.out.println("Went back.");
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
                            roomContents.remove(i);
                            System.out.println("Picked up successfully.");
                            found = true;
                            break;
                        }
                    }

                    if(!found)
                    {
                        System.out.println("Item not found in this room.");
                    }
                }
                break;
            case "inventory":
                ArrayList<Item> tempIven = player.getInventory();
                System.out.println("Inventory: ");
                for (int i = 0; i < tempIven.size(); i ++){
                    System.out.println(tempIven.get(i).getName());
                }
                break;
            case "solve":
                System.out.println("Name of puzzle you want to solve: ");
                String puzzleName = scanner.nextLine().trim().toLowerCase();
                System.out.println("Enter your answer: ");
                String answer = scanner.nextLine().trim();
                boolean found = false;

                
                for (int i = 0; i < curr.getContents().size(); i++){
                    GameComponent currContent = curr.getContents().get(i);
                    String currName = currContent.getName().toLowerCase();
                    
                    if(currName.equals(puzzleName)){
                        found = true;
                        Puzzle puzzle = (Puzzle) currContent;
                        // solve

                        if (puzzle.attemptSolve(answer)){
                            System.out.println("Your answer is correct");
                            if (puzzle.getReward() != null){
                            player.pickupItem(puzzle.getReward());
                            System.out.println("You received: " + puzzle.getReward().getName());
                            }
                            if (puzzle.getHint() != null && !puzzle.getHint().isEmpty()){
                                hint.add(puzzle.getHint());
                                System.out.println("Hint added.");
                            }
                        }
                        else {
                            System.out.println("Your answer is incorrect");
                            failTries++;
                        }
                        
                        if (failTries % 3 == 0 && !hint.isEmpty()){
                            System.out.println("Hint: " + hint.poll());
                        }
                        break;
                    }
                }

                if(!found){
                    System.out.println("No item found of that name");
                }
                break;
            case "map":
                System.out.println("Map: ");
                HashSet<Room> visted = new HashSet<>();
                helpMap(curr, visted, 0);
                break;
            default:
                System.out.println("Invalid command");
                break;
        }
    }
}

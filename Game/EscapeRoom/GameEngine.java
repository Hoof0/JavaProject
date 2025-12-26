

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class GameEngine {
    private boolean Win = false;
    private Player player;
    private Queue<String> hint;
    private Scanner scanner;
    private static int puzzleSolved = 0;
    private final int ALLPUZZLESSOLVED = 2;

    public GameEngine(Player player){
        this.player = player;
        this.scanner = new Scanner(System.in);
        this.hint = new LinkedList<>();
    }

    void start(){
        System.out.println("Text-based escape room game.\nCommands: look, move, back, pickup, inventory, solve, map, quit.\nFind the exit room to win.");
        while (Win != true){
            String input = scanner.nextLine().trim();
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
                String destination = scanner.nextLine();
                destination = destination.toLowerCase().trim();
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
                ArrayList<Item> availableItems = new ArrayList<Item>(); //collect all items in the room to another array list to prevent the game confusing items with puzzles
                for (int i = 0; i < roomContents.size(); i++)
                {
                    if (roomContents.get(i) instanceof Item)
                    {
                        availableItems.add((Item) roomContents.get(i));
                    }
                }
                
                if(availableItems.isEmpty())
                {
                    System.out.println("No items in this room.");
                }
                else
                {
                    System.out.println("Items to pick up: ");
                    for(int i = 0; i < availableItems.size(); i++)
                    {
                        System.out.println(i + "." + availableItems.get(i).getName());
                    }
                    System.out.print("Enter the name of the item you want to pick up: ");
                    String itemName = scanner.nextLine().trim().toLowerCase();
                    boolean found = false;

                    for(int i = 0; i < roomContents.size(); i++)
                    {
                        if(roomContents.get(i).getName().toLowerCase().equals(itemName) && roomContents.get(i) instanceof Item)
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
                String puzzleName = scanner.nextLine().trim();
                boolean puzzleFound = false;

                for (int i = 0; i < curr.getContents().size(); i++){
                    GameComponent currContent = curr.getContents().get(i);
                    
                    if(currContent.getName().equals(puzzleName) && currContent instanceof Puzzle){
                        puzzleFound = true;
                        Puzzle puzzle = (Puzzle) currContent;
                        if (puzzle.isSolved()){
                            System.out.println("This puzzle has already been solved.");
                            break;
                        }

                        puzzle.inspect(); //show the puzzle
                        System.out.println("Enter your answer: ");
                        String answer = scanner.nextLine().trim();
                        int count = 0;

                        if (puzzle.attemptSolve(answer))
                        {
                            System.out.println("You are correct.");
                            puzzleSolved++;
                            if (puzzle.getReward() != null){
                                player.pickupItem(puzzle.getReward());
                                System.out.println("You received: " + puzzle.getReward().getName());
                            }
                            if (puzzle.getHint() != null && !puzzle.getHint().isEmpty()){
                                hint.add(puzzle.getHint());
                                System.out.println("Hint added.");
                            }
                            count = 0;
                        }
                        else {
                            System.out.println("Your answer is incorrect");
                            count++;
                            if (count % 3 == 0 && !hint.isEmpty()){
                                System.out.println("Hint: " + hint.peek());
                                hint.remove();
                            }
                        }
                        break;
                    }
                }
                if (!puzzleFound)
                {
                    System.out.println("Puzzle not found.");
                }
                break;
            case "map":
                System.out.println("Map: ");
                HashSet<Room> visted = new HashSet<>();
                helpMap(curr, visted, 0);
                break;
            case "quit":
                System.out.println("Game over.");
                Win = true;
                break;
            default:
                System.out.println("Invalid command. Try look, move, back, pickup, inventory, solve, map, quit.");
                break;
        }
    }
}

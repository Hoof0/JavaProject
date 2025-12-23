

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
        cmd = cmd.toLowerCase();
        switch (cmd) {
            case "look":
                Room curr = player.getCurrentRoom();
                curr.toString();
                break;
            case "move":
                Room curr = player.getCurrentRoom();
                ArrayList<Room> connectRoom = curr.getConnectedRoom();
                for(int i = 0; i < connectRoom.size(); i++){
                    System.out.println(connectRoom.get(i).toString());
                }
                Scanner scanner = new Scanner(System.in);
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
                break;
            case "pickup":
                break;
            default:
                break;
        }
    }
}

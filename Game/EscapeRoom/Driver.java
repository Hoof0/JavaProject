public class Driver {
    public static void main(String[] args){
        //create rooms
        Room entrance = new Room("Entrance");
        Room hallway = new Room("Hallway");
        Room library = new Room("Library");
        Room treasureRoom = new Room("Treasure Room");
        Room exitRoom = new Room("Exit", true);
        
        //connect rooms
        entrance.getConnectedRoom().add(hallway);
        hallway.getConnectedRoom().add(entrance);
        hallway.getConnectedRoom().add(library);
        hallway.getConnectedRoom().add(treasureRoom);
        library.getConnectedRoom().add(hallway);
        treasureRoom.getConnectedRoom().add(hallway);
        treasureRoom.getConnectedRoom().add(exitRoom);
        exitRoom.getConnectedRoom().add(treasureRoom);
        
        //create items
        Item key = new Item("Golden Key", 100, "KEY");
        Item map = new Item("Ancient Map", 50, "CLUE");
        Item torch = new Item("Torch", 30, "TOOL");
        Item diamond = new Item("Diamond", 500, "TREASURE");

        //create puzzles
        CodePuzzle codePuzzle = new CodePuzzle(
            "A numeric lock with 4 digits. Try to guess the code.",
            "Safe Lock",
            5,
            diamond,
            "1234",
            "The code is the first four counting numbers"
        );
        RiddlePuzzle riddlePuzzle = new RiddlePuzzle(
            "I speak without a mouth and hear without ears. I have no body, but come alive with wind. What am I?",
            "Ancient Riddle",
            key,
            3,
            "echo",
            "Think about sound bouncing back"
        );
        
        //add items and puzzles to rooms
        entrance.getContents().add(torch);
        library.getContents().add(riddlePuzzle);
        library.getContents().add(map);
        treasureRoom.getContents().add(codePuzzle);
        
        //create player and start game
        Player player = new Player(entrance);
        GameEngine game = new GameEngine(player);

        game.start();
    }
}
public class Driver {
    public static void main(String[] args) {
        // Create rooms
        Room entrance = new Room("Entrance");
        Room library = new Room("Library");
        Room laboratory = new Room("Laboratory");
        Room vault = new Room("Vault");
        Room exitRoom = new Room("Exit", true);
        
        // Connect rooms (you'll need to add this method to Room class)
        entrance.addConnectedRoom(library);
        entrance.addConnectedRoom(laboratory);
        library.addConnectedRoom(vault);
        laboratory.addConnectedRoom(vault);
        vault.addConnectedRoom(exitRoom);
        
        // Create items
        Item key = new Item("Golden Key", 100, "KEY");
        Item magnifyingGlass = new Item("Magnifying Glass", 50, "TOOL");
        Item clue = new Item("Ancient Scroll", 30, "CLUE");
        
        // Create puzzles
        RiddlePuzzle entranceRiddle = new RiddlePuzzle(
            "I speak without a mouth and hear without ears. I have no body, but come alive with wind. What am I?",
            "Entrance Riddle",
            magnifyingGlass,
            2,
            "echo",
            "Think about sound and caves"
        );
        
        CodePuzzle vaultCode = new CodePuzzle(
            "Enter the 4-digit code to unlock the vault",
            "Vault Lock",
            3,
            key,
            "1234",
            "The code is written on the scroll"
        );
        
        // Add content to rooms (you'll need to add this method to Room class)
        entrance.addContent(entranceRiddle);
        entrance.addContent(clue);
        library.addContent(vaultCode);
        
        // Create player and start game
        Player player = new Player(entrance);
        GameEngine game = new GameEngine(player);
        
        System.out.println("=== ESCAPE ROOM GAME ===");
        System.out.println("You wake up in a mysterious entrance hall...");
        System.out.println("Your goal: Find the exit room!\n");
        
        game.start();
    }
}
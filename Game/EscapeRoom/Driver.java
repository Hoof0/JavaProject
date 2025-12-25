public class Driver {
    public static void main(String[] args){
        Room check = new Room("string");
        Player player = new Player(check);
        GameEngine game = new GameEngine(player);
        game.start();
    }
}

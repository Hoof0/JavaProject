package Game.EscapeRoom;

public abstract class Puzzle extends GameComponent implements Comparable<Puzzle> {
    protected int difficulty;
    protected boolean solved;

    
    public void inspect(){

    }

    public String getName(){
        return name;
    }

    //method
    public abstract boolean attemptSolve(String answer) throws InvalidPuzzleException;

    @Override
    public int compareTo(Puzzle o) {  
        return Integer.compare(this.difficulty, o.difficulty);
    }
}

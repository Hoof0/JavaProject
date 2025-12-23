
public abstract class Puzzle extends GameComponent implements Comparable<Puzzle> {
    protected int difficulty;
    protected boolean solved;

    Puzzle(String name, int difficulty){
        super(name);
        this.difficulty = difficulty;
        solved = false;
    }

    public void inspect(){
        
    }

    public String getName(){
        return name;
    }

    //method
    public abstract boolean attemptSolve();

    @Override
    public int compareTo(Puzzle o) {  
        return Integer.compare(this.difficulty, o.difficulty);
    }
}

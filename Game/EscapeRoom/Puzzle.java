
public abstract class Puzzle extends GameComponent implements Comparable<Puzzle> {
    protected int difficulty;
    protected boolean solved;
    protected String description;

    Puzzle(String description, String name, int difficulty){
        super(name);
        this.difficulty = difficulty;
        solved = false;
        this.description = description;
    }

    public void inspect(){
        System.out.println("Puzzle: " + name + "\nDescription: " + description + "\nDifficulty: " + difficulty);
    }

    public String getName(){
        return name;
    }

    //method
    public abstract boolean attemptSolve(String answer) /* throws InvalidPuzzleAnswerException */;

    @Override
    public int compareTo(Puzzle o) {  
        return Integer.compare(this.difficulty, o.difficulty);
    }
}


public class RiddlePuzzle extends Puzzle{
    private String pass;

    public RiddlePuzzle(String name, int difficulty, boolean solved)
    {
        super(name, difficulty);
    }
    
    public boolean attemptSolve(String answer) throws InvalidPuzzleException{
        if (answer.equals(pass)) solved = true;
        else solved = false;
    }

    
    
}

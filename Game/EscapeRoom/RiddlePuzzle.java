
public class RiddlePuzzle extends Puzzle{
    private String pass;

    public RiddlePuzzle(String description, String name, int difficulty, String pass)
    {
        super(description, name, difficulty);
        this.pass = pass;
    }
    
    public boolean attemptSolve(String answer)/* throws InvalidPuzzleAnswerException */{
        if (answer.equals(pass)) solved = true;
        else solved = false;
        return solved;
    }

    
    
}

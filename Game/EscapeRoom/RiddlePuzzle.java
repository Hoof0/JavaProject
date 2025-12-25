
public class RiddlePuzzle extends Puzzle{
    private String pass;

    public RiddlePuzzle(String description, String name, Item Reward, int difficulty, String pass, String hint)
    {
        super(description, name, difficulty, Reward, hint);
        this.pass = pass;
    }
    
    public boolean attemptSolve(String answer)/* throws InvalidPuzzleAnswerException */{
        if (answer.equals(pass)) solved = true;
        else solved = false;
        return solved;
    }

    
    
}

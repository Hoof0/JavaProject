

public class CodePuzzle extends Puzzle{
    
    private String pass;

    CodePuzzle(String description, String name, int difficulty, Item Reward, String pass, String hint){
        super(description, name, difficulty, Reward, hint);
        this.pass = pass;
    }


    
    public boolean attemptSolve(String answer)/* throws InvalidPuzzleAnswerException /*Implement*/{
        if (answer.equals(pass)) solved = true;
        else solved = false;
        return solved;
    }
}



public class CodePuzzle extends Puzzle{
    
    private String pass;

    CodePuzzle(String description, String name, int difficulty, String pass){
        super(description, name, difficulty);
        this.pass = pass;
    }


    
    public boolean attemptSolve(String answer)/* throws InvalidPuzzleAnswerException /*Implement*/{
        if (answer.equals(pass)) solved = true;
        else solved = false;
        return solved;
    }


}



public class CodePuzzle extends Puzzle{
    
    private int pass;

    CodePuzzle(String name, int difficulty, int pass){
        super(name, difficulty);
        this.pass = pass;
    }


    
    public boolean attemptSolve(int answer) throws InvalidPuzzleException {
        if (answer == pass) solved = true;
        else solved = false;
    }
}

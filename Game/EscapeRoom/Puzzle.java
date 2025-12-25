
public abstract class Puzzle extends GameComponent implements Comparable<Puzzle> {
    protected int difficulty;
    protected boolean solved;
    protected String description;
    protected Item Reward;
    protected String hint;

    Puzzle(String description, String name, int difficulty, Item Reward, String hint){
        super(name);
        this.difficulty = difficulty;
        solved = false;
        this.description = description;
        this.Reward = Reward;
        this.hint = hint;
    }

    public void inspect(){
        System.out.println("Puzzle: " + name + "\nDescription: " + description + "\nDifficulty: " + difficulty);
    }

    public String getName(){
        return name;
    }

    public Item getReward(){
        if (solved){
            return Reward;
        }
        return null;
    }

    public String getHint(){
        return hint;
    }

    //method
    public abstract boolean attemptSolve(String answer) /* throws InvalidPuzzleAnswerException */;

    @Override
    public int compareTo(Puzzle o) {  
        return Integer.compare(this.difficulty, o.difficulty);
    }
}

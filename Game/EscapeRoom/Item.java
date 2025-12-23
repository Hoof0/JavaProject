

public class Item extends GameComponent implements Collectible, Comparable<Item> {
    private int value;
    private String itemType; //KEY, TOOL, CLUE

    public Item(String name, int value, String itemType)
    {
        super(name);
        this.value = value;
        this.itemType = itemType;
    }

    @Override
    public int compareTo(Item i) {
        if (this.value < i.value)
        {
            return -1;
        }
        else if (this.value > i.value)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public void collect(Player p)
    {
        
    }

    public void inspect()
    {
        System.out.println(itemType);
    }
}
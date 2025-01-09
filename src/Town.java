/**
 * The Town Class is where it all happens.
 * The Town is designed to manage all of the things a Hunter can do in town.
 */
public class Town
{
    //instance variables
    private Hunter hunter;
    private Shop shop;
    private Terrain terrain;
    private String printMessage;
    private boolean easyTown;
    private boolean regularTown;
    private boolean toughTown;
    private boolean crazyTown;
    private int totalCasino;
    private static int count;
    private double percent;

    //Constructor
    /**
     * The Town Constructor takes in a shop and the surrounding terrain, but leaves the hunter as null until one arrives.
     * @param s The town's shoppe.
     * @param t The surrounding terrain.
     */
    public Town(Shop shop, double toughness)
    {
        this.shop = shop;
        this.terrain = getNewTerrain();

        // the hunter gets set using the hunterArrives method, which
        // gets called from a client class
        hunter = null;

        printMessage = "";

        // higher toughness = more likely to be a tough town
        if (toughness == 0.4) {
            regularTown = true;
        }
        else if (toughness == 0.75) {
            toughTown = true;
        }
        else if (toughness == 0.2) {
            easyTown = true;
        }
        else if (toughness < 1) {
            crazyTown = true;
        }
    }

    public String getLatestNews()
    {
        return printMessage;
    }

    /**
     * Assigns an object to the Hunter in town.
     * @param h The arriving Hunter.
     */
    public void hunterArrives(Hunter hunter)
    {
        this.hunter = hunter;
        printMessage = "Welcome to town, " + hunter.getHunterName() + ".";

        if (toughTown)
        {
            printMessage += "\nIt's pretty rough around here, so watch yourself.";
        }
        else if (regularTown)
        {
            printMessage += "\nWe're just a sleepy little town with mild mannered folk.";
        }
        else if (easyTown) {
            printMessage += "\nIt's a quiet town with a polite community.";
        }

        else if (crazyTown) {
            printMessage += "\nHave an easy breezy time.";
        }
    }

    /**
     * Handles the action of the Hunter leaving the town.
     * @return true if the Hunter was able to leave town.
     */
    public boolean leaveTown()
    {
        boolean canLeaveTown = terrain.canCrossTerrain(hunter);
        if (canLeaveTown)
        {
            String item = terrain.getNeededItem();
            printMessage = "You used your " + item + " to cross the " + terrain.getTerrainName() + ".";
            if (checkItemBreak())
            {
                hunter.removeItemFromKit(item);
                printMessage += "\nUnfortunately, your " + item + " broke.";
            }

            return true;
        }

        printMessage = "You can't leave town, " + hunter.getHunterName() + ". You don't have a " + terrain.getNeededItem() + ".";
        return false;
    }

    public void enterShop(String choice)
    {
        shop.enter(hunter, choice);
    }

    /**
     * Gives the hunter a chance to fight for some gold.<p>
     * The chances of finding a fight and winning the gold are based on the toughness of the town.<p>
     * The tougher the town, the easier it is to find a fight, and the harder it is to win one.
     */
    public void lookForTrouble()
    {
        double noTroubleChance;
        if (toughTown)
        {
            noTroubleChance = 0.66;
        }
        else if (easyTown)
        {
            noTroubleChance = 0.15;
        }
        else if (crazyTown) {
            noTroubleChance = 0;
        }
        else {
            noTroubleChance = 0.33;
        }

        if (Math.random() > noTroubleChance)
        {
            printMessage = "You couldn't find any trouble";
        }
        else
        {
            printMessage = "You want trouble, stranger!  You got it!\nOof! Umph! Ow!\n";
            int goldDiff = (int)(Math.random() * 10) + 1;
            if (Math.random() > noTroubleChance)
            {
                printMessage += "Okay, stranger! You proved yer mettle. Here, take my gold.";
                printMessage += "\nYou won the brawl and receive " +  goldDiff + " gold.";
                hunter.changeGold(goldDiff);
            }
            else if (crazyTown) {
                goldDiff = 100;
                printMessage += "Dang, you must be real special stranger! I'm honored to fight a gem like you. Here, take all my dice and skedaddle.";
                printMessage += "\nYou won the brawl and receive " +  goldDiff + " gold.";
                hunter.changeGold(goldDiff);
            }
            else if (easyTown) {
                goldDiff = ((int)(Math.random() * 10) + 1) * 3;
                printMessage += "Alrighty stranger! Yer tuff huh. Here, I'm feelin' a bit nice today.";
                printMessage += "\nYou won the brawl and receive " +  goldDiff + " gold.";
                hunter.changeGold(goldDiff);
            }
            else
            {
                printMessage += "That'll teach you to go lookin' fer trouble in MY town! Now pay up!";
                printMessage += "\nYou lost the brawl and pay " +  goldDiff + " gold.";
                hunter.changeGold(-1 * goldDiff);
            }
        }
    }

    public String huntForTreasure() {
        double chance = Math.random();
        double percent = Math.random();
        int amtGold = (int) (Math.random() * 10) + 1;
        System.out.println(percent);
        if (percent > 0.5) {
            return "You didn't find any treasure! \n ***";
        }
        else {
            if (chance > 0.5) {
                hunter.changeGold(amtGold);
                return "You found " + amtGold + " gold!";
            }
            else {
                return "You found an item!";
            }
        }
    }

    public String toString()
    {
        return "This nice little town is surrounded by " + terrain.getTerrainName() + ".";
    }

    /**
     * Determines the surrounding terrain for a town, and the item needed in order to cross that terrain.
     *
     * @return A Terrain object.
     */
    private Terrain getNewTerrain()
    {
        double rnd = Math.random();
        if (rnd < .2)
        {
            return new Terrain("Mountains", "Rope");
        }
        else if (rnd < .4)
        {
            return new Terrain("Ocean", "Boat");
        }
        else if (rnd < .6)
        {
            return new Terrain("Plains", "Horse");
        }
        else if (rnd < .8)
        {
            return new Terrain("Desert", "Water");
        }
        else
        {
            return new Terrain("Jungle", "Machete");
        }
    }

    /**
     * Determines whether or not a used item has broken.
     * @return true if the item broke.
     */
    private boolean checkItemBreak()
    {
        double rand = Math.random();
        return (rand < 0.5);
    }

    public String LuckyDice(int n, int amt) {
            int one = (int) ((Math.random() * 6) + 1);
            int two = (int) ((Math.random() * 6) + 1);
            int num = one + two;

            if (num == n) {
                amt *= 2;
                hunter.changeGold(amt);
                totalCasino += amt;
                return "You guessed correctly. Your gold has been doubled!";
            }

            else if (Math.abs(num - n) == 2) {
                hunter.changeGold(amt);
                totalCasino += amt;
                return "Your number was " + num + "\nYou got back the gold you wagered!";
            }

            else if (Math.abs(num - n) > 2) {
                hunter.changeGold(-amt);
                return "Your number was " + num + "\nYou lost all your gold!";
            }

            if (amt == 10) {
                count++;
            }
            else if (amt == -10) {
                count--;
            }
            return "Error!";
        }

        public int Luck() {
            int luck = count * 2;
            double modifier = 1.01;
            percent = count * modifier;
            return luck;
        }

        public double getPercent() {
            return percent;
        }
    }

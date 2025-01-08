/**
 * This class is responsible for controlling the Treasure Hunter game.<p>
 * It handles the display of the menu and the processing of the player's choices.<p>
 * It handles all of the display based on the messages it receives from the Town object.
 *
 */
import java.util.Scanner;

public class TreasureHunter
{
    //Instance variables
    private Town currentTown;
    private Hunter hunter;
    private Treasure treasure;
    private static boolean hardMode;
    private static boolean easyMode;
    private static boolean regularMode;
    public TreasureHunter treasureHunter;
    Scanner scanner = new Scanner(System.in);
    //Constructor
    /**
     * Constructs the Treasure Hunter game.
     */
    public TreasureHunter()
    {
        // these will be initialized in the play method
        currentTown = null;
        hunter = null;
        treasure = null;
        hardMode = false;
        easyMode = false;
        regularMode = false;
    }

    // starts the game; this is the only public method
    public void play ()
    {
        welcomePlayer();
        enterTown();
        showMenu();
    }

    /**
     * Creates a hunter object at the beginning of the game and populates the class member variable with it.
     */
    private void welcomePlayer()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to TREASURE HUNTER!");
        System.out.println("Going hunting for the big treasure, eh?");
        System.out.print("What's your name, Hunter? ");
        String name = scanner.nextLine();

        // set hunter instance variable

        System.out.print("Easy, hard, or regular mode? (e/h/r): ");
        String mode = scanner.nextLine();
        if (mode.equalsIgnoreCase("e"))
        {
            easyMode = true;
        }
        else if (mode.equalsIgnoreCase("h"))
        {
            hardMode = true;
        }
        else if (mode.equalsIgnoreCase("r")) {
            regularMode = true;
        }

        hunter = new Hunter(name, 15);
        if (easyMode) {
            hunter = new Hunter(name, 20);
        }
        else if (hardMode) {
            hunter = new Hunter(name, 10);
        }

    }

    public static boolean isHardMode() {
        return hardMode;
    }

    public static boolean isEasyMode() {
        return easyMode;
    }

    public static boolean isRegularMode() {
        return regularMode;
    }

    /**
     * Creates a new town and adds the Hunter to it.
     */
    private void enterTown()
    {
        double markdown = 0.25;
        double toughness = 0.4;

        if (hardMode) {
            // in hard mode, you get less money back when you sell items
            markdown = 0.5;

            // and the town is "tougher"
            toughness = 0.75;
        }

        else if (easyMode){
            markdown = 0.15;
            toughness = 0.2;
        }

        // note that we don't need to access the Shop object
        // outside of this method, so it isn't necessary to store it as an instance
        // variable; we can leave it as a local variable
        Shop shop = new Shop(markdown);

        // creating the new Town -- which we need to store as an instance
        // variable in this class, since we need to access the Town
        // object in other methods of this class
        currentTown = new Town(shop, toughness);

        // calling the hunterArrives method, which takes the Hunter
        // as a parameter; note this also could have been done in the
        // constructor for Town, but this illustrates another way to associate
        // an object with an object of a different class
        currentTown.hunterArrives(hunter);

        treasure = new Treasure();

    }

    /**
     * Displays the menu and receives the choice from the user.<p>
     * The choice is sent to the processChoice() method for parsing.<p>
     * This method will loop until the user chooses to exit.
     */
    private void showMenu()
    {

        String choice = "";

        while (!(choice.equals("X") || choice.equals("x")))
        {
            System.out.println();
            System.out.println(currentTown.getLatestNews());
            System.out.println("***");
            System.out.println(hunter);
            System.out.println(currentTown);
            System.out.println("(B)uy something at the shop.");
            System.out.println("(S)ell something at the shop.");
            System.out.println("(M)ove on to a different town.");
            System.out.println("(L)ook for trouble!");
            System.out.println("(H)unt for treasure!");
            System.out.println("(C)asino!");
            System.out.println("Give up the hunt and e(X)it.");
            System.out.println();
            System.out.print("What's your next move? ");
            choice = scanner.nextLine();
            choice = choice.toUpperCase();
            processChoice(choice);
        }
    }

    /**
     * Takes the choice received from the menu and calls the appropriate method to carry out the instructions.
     * @param choice The action to process.
     */
    private void processChoice(String choice)
    {
        if (choice.equalsIgnoreCase("b") || choice.equalsIgnoreCase("s"))
        {
            currentTown.enterShop(choice);
        }
        else if (choice.equalsIgnoreCase("m"))
        {
            if (currentTown.leaveTown())
            {
                //This town is going away so print its news ahead of time.
                System.out.println(currentTown.getLatestNews());
                enterTown();
            }
        }
        else if (choice.equalsIgnoreCase("l"))
        {
            currentTown.lookForTrouble();
        }
        else if (choice.equalsIgnoreCase("x"))
        {
            System.out.println("Fare thee well, " + hunter.getHunterName() + "!");
        }
        else if (choice.equalsIgnoreCase("h")) {
            treasure.congrats();

        }
        else if (choice.equalsIgnoreCase("c")) {
            int n = hunter.getGold();
            if (n > 1) {
                currentTown.casino();
            }
            else {
                System.out.println("You do not have enough money!");
            }
        }
        else
        {
            System.out.println("Yikes! That's an invalid option! Try again.");
        }
    }
}

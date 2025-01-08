public class Treasure {

    private int aTreasure;
    private int bTreasure;
    private int cTreasure;
    private int t = 0;
    private boolean treasureFound;
    private String treasure;

    private Hunter inv;

    public Treasure() {
        this.aTreasure = 0;
        this.bTreasure = 0;
        this.cTreasure = 0;
        this.treasureFound = false;
        this.treasure = "Treasure Chest";

    }

    public int amtOfTreasure() {
        return t;
    }

    public boolean lookingForTreasure() {


        int chance = (int) (1 + Math.random() * 2);
        if (chance == 1) {

            treasureFound = false;

        }

        if (chance == 2) {

            treasureFound = true;
            t++;
            int whichOne = (int) (1 + Math.random() * 3);

            if (whichOne == 1) {
                aTreasure = 1;
                treasureFound = false;
            }

            if (whichOne == 2) {
                bTreasure = 1;
                treasureFound = false;
            }

            if (whichOne == 3) {
                cTreasure = 1;
                treasureFound = false;

            }


        }

        return treasureFound;
    }

    public void addToInventory(String item)
    {


    }


    public void congrats() {

        if  (aTreasure == 1) {
            System.out.println("You have found this unique treasure. It contains: ." + "\n" +
                    " Total amount of treasure found: " + t);
            aTreasure = 0;
        }
        if  (bTreasure == 1) {
            System.out.println("You have found this unique treasure. It contains: ." + "\n" +
                    " Total amount of treasure found: " + t);
            bTreasure = 0;
        }
        if  (cTreasure == 1) {
            System.out.println("You have found this unique treasure. It contains: ." + "\n" +
                    " Total amount of treasure found: " + t);
            cTreasure = 0;
        }
        if (t == 3) {
            System.out.println("You have found all three unique treasures! Congratulations!");
        }

    }
}

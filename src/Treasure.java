public class Treasure {

    private int aTreasure;
    private int bTreasure;
    private int cTreasure;
    private int t = 0;
    private boolean treasureFound;

    public Treasure() {
        this.aTreasure = 0;
        this.bTreasure = 0;
        this.cTreasure = 0;
        this.treasureFound = false;

    }

    public int amtOfTreasure() {
        return t;
    }

    public boolean lookingForTreasure() {


        int chance;
        if (Math.random() < .5) {

            chance = 1;

        } else {

            chance = 2;

        }

        if (chance == 1){

            treasureFound = false;

        }
        if (chance == 2){

            treasureFound = true;
            t++;
            int whichOne = (int) (1 + Math.random() * 3);

            if (whichOne == 1) {
                aTreasure = 1;
            }

            if (whichOne == 2) {
                bTreasure = 1;
            }

            if (whichOne == 3) {
                cTreasure = 1;
            }
        }

        return false;
    }

    public boolean treasureFound() {
        return treasureFound;
    }

    public String congrats() {
        String str = "";
       if  (aTreasure == 1 && treasureFound) {
           return str = "You have found this unique treasure. It contains: ." + "\n" +
                   " Total amount of treasure found: " + t;
       }
       if  (bTreasure == 1 && treasureFound) {
           return str = "You have found this unique treasure. It contains: ." + "\n" +
                   " Total amount of treasure found: " + t;
       }
       if  (cTreasure == 1 && treasureFound) {
           return str = "You have found this unique treasure. It contains: ." + "\n" +
                   " Total amount of treasure found: " + t;
       }
       if (t == 3) {
           return str = "You have found all three unique treasures! Congratulations!";
       }
        return str;
    }
}

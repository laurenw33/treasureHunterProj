public class Treasure {

    private String aTreasure;
    private String bTreasure;
    private String cTreasure;
    private int treasureChance;
    private boolean t;


    public Treasure(String royal, String strong, String unique) {
        this.aTreasure = royal;
        this.bTreasure = strong;
        this.cTreasure = unique;

    }

    public void lookingForTreasure() {


        if (Math.random() < .5) {
            treasureChance = 1;
        } else {
            treasureChance = 2;
        }

        if (treasureChance == 1){
            t = false;
        }
        if (treasureChance == 2){
            t = true;

        }

    }

    public boolean treasureFound() {
        return t;
    }

    public String congrats() {
        return "You have found all three unique treasures! Congratulations!";
    }
}


public class Archer extends MilitaryUnit {
    public int arrows;
// Constructs archer and sets arrows at 5
    public Archer(Tile position, double hp, String faction) {
        super(position, hp, 2, faction, 15.0, 2, 0);
        this.arrows = 5;
    }

// Checks for number of arrows, and takes action if there is enough.
    public void takeAction(Tile n) {
        if (this.arrows<=0){
            this.arrows=5;
        }
        else {
            this.arrows-=1;
            super.takeAction(n);
        }
    }

// Checks for number of arrows, if it is an archer, and super(equals)
    public boolean equals(Object obj) {
        if (super.equals(obj)){
            if (obj instanceof Archer){
                return ((Archer) obj).arrows == this.arrows;
            }
        }
        return false;
    }


}

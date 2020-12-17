
public class Settler extends Unit {


    public Settler(Tile position, double hp, String faction) {
        super(position, hp, 2, faction);
    }

    @Override
    public void takeAction(Tile n) {
        if (this.getPosition().equals(n) && !this.getPosition().isCity()){
            this.getPosition().foundCity();
            this.getPosition().removeUnit(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)){
            return obj instanceof Settler;
        }
        return false;
    }
}

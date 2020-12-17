


public abstract class Unit {
    private Tile position;
    private double hp;
    private int movementRange;
    private String faction;

    public Unit(Tile position, double hp, int movementRange, String faction) {
        this.position = position;
        this.hp = hp;
        this.movementRange = movementRange;
        this.faction = faction;
        if(!(position.addUnit(this))){   //make sure this runs when evaluating
            throw new IllegalArgumentException();
        }
    }

    public final Tile getPosition() {
        return this.position;
    }

    public final double getHP() {
        return this.hp;
    }

    public final String getFaction() {
        return this.faction;
    }


    public boolean moveTo(Tile n){
        if (Tile.getDistance(this.position,n)<=this.movementRange){
            if (n.addUnit(this)){
                this.position.removeUnit(this);
                this.position = n;
                return true;
            }
        }
        return false;
    }

    public void receiveDamage(double dmg){
        if (this.position.isCity()){
            this.hp-=0.9*dmg;
        } else {
            this.hp-=dmg;
        }
        if (this.hp<=0){
            this.position.removeUnit(this);
        }
    }

    public abstract void takeAction(Tile n);

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Unit){
            Unit unitObj = (Unit) obj;
            return  (unitObj.getHP()==this.getHP() && unitObj.getFaction().equals(this.getFaction()) && unitObj.getPosition().equals(this.getPosition()));
        }
        return false;
    }
}

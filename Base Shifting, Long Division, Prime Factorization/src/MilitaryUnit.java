

public abstract class MilitaryUnit extends Unit {
    private double atkDmg;
    private int atkRange;
    private int armor;

    public MilitaryUnit(Tile position, double hp, int movementRange, String faction, double atkDmg, int atkRange, int armor) {
        super(position, hp, movementRange, faction);
        this.atkDmg = atkDmg;
        this.atkRange = atkRange;
        this.armor = armor;
    }

    @Override
    public void takeAction(Tile n) {
        if (Tile.getDistance(this.getPosition(),n)<=this.atkRange+1 ){
            Unit defender = n.selectWeakEnemy(this.getFaction());
            if (defender!=null){
                defender.receiveDamage(this.atkDmg);
                if (this.getPosition().isImproved()){
                    defender.receiveDamage(this.atkDmg*0.05);
                }
            }
        }
    }


    @Override
    public void receiveDamage(double dmg) {
        double armorDmg = dmg*(100/(100+this.armor));
        super.receiveDamage(armorDmg);
    }
}

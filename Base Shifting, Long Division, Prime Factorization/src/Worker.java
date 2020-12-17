


public class Worker extends Unit {
    private int jobsDone;

    public Worker(Tile position, double hp, String faction) {
        super(position, hp, 2, faction);
        this.jobsDone=0;
    }

    @Override
    public void takeAction(Tile n) {
        if (this.getPosition().equals(n) && !this.getPosition().isImproved()){
            this.getPosition().buildImprovement();
            this.jobsDone+=1;
            if (this.jobsDone>=10) {
                this.getPosition().removeUnit(this);
            }
        }

    }

    public boolean equals(Object obj) {
        if (super.equals(obj)){
            if (obj instanceof Worker){
                return ((Worker) obj).jobsDone == this.jobsDone;

            }
        }
        return false;
    }

}

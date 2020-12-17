



public class Tile {
    private int xCoord;
    private int yCoord;
    private boolean isCity;
    private boolean isImproved;
    private ListOfUnits unitList;



    public Tile(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.isCity = false;
        this.isImproved = false;
        unitList = new ListOfUnits();

    }

    public int getX() {
        return xCoord;
    }

    public int getY() {
        return yCoord;
    }


    public boolean isCity() {
        return isCity;
    }

    public boolean isImproved() {
        return isImproved;
    }

    public void foundCity() {

        if (!isCity) {
            this.isCity = true;
        }
    }

    public void buildImprovement() {
        if (!isImproved) {
            this.isImproved = true;
        }
    }

    public static double getDistance(Tile tile1, Tile tile2){
        return Math.sqrt(Math.pow(tile1.getX() - tile2.getX(), 2) + (Math.pow(tile1.getY() - tile2.getY(), 2)));
    }

//    Adds unit to list, if military unit it checks for a unit of opposite faction
    public boolean addUnit(Unit n){
        if (n instanceof MilitaryUnit){
            MilitaryUnit[] tempList = this.unitList.getArmy();
            for (int i=0; i<tempList.length; i++){
                if (!n.getFaction().equals(tempList[i].getFaction())){
                    return false;
                }
            }
        }
        this.unitList.add(n);
        return true;
        }

    public boolean removeUnit(Unit n){
        return this.unitList.remove(n);
    }


    public Unit selectWeakEnemy (String faction){
        Unit weakestLink=null;
        double lowestHp = 100;
        for (int i=0; i<this.unitList.size(); i++){
            if (this.unitList.getUnits()[i].getFaction().equals(faction)){
                continue;
            }
            if (this.unitList.getUnits()[i].getHP()<lowestHp){
                weakestLink=this.unitList.getUnits()[i];
                lowestHp = this.unitList.getUnits()[i].getHP();
            }
        }
        return weakestLink;
    }











    }





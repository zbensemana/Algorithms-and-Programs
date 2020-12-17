

public class ListOfUnits {
    private Unit unitList[];
    private int listSize;

    public ListOfUnits() {
        Unit[] tempList = new Unit[10];
        this.unitList = tempList;
        this.listSize = 0;
    }

    public int size (){
        int count=0;
        for (int i=0; i<this.unitList.length; i++){
            if (this.unitList[i] == null){
                count=i;
                break;
            }
        }
        this.listSize=count;
        return this.listSize;
    }

    public Unit[] getUnits(){
        Unit[] tempList = new Unit[this.size()];
        for (int i=0; i<size(); i++){
            tempList[i] = this.unitList[i];
        }
        return tempList;
    }

    public Unit get(int n){
        Unit tempUnit;
        try{
            tempUnit = this.unitList[n];
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("Array out of Bounds");
        }

        if (tempUnit == null){
            throw new  ArrayIndexOutOfBoundsException("Array out of Bounds");
        }
        else {
            return tempUnit;
        }
    }


    public void add(Unit newGuy){
        if (this.size() == this.unitList.length-1){
            this.unitList=resize(this.unitList);
        }
        this.unitList[this.size()] = newGuy;
    }


    private Unit[] resize(Unit[] x){
        Unit[] tempList = new Unit[x.length + x.length/2];
        for (int i=0; i<x.length; i++){
            tempList[i] = x[i];
        }
        return tempList;
    }

    public int indexOf(Unit n){
       int index=-1;
       for (int i=0; i< this.size(); i++){
           if (this.unitList[i].equals(n)){
               index=i;
               break;
           }
       }
       return index;
    }

    public boolean remove (Unit n){
        int index = indexOf(n);
        if (index==-1){
            return false;
        }
        Unit[] tempList = new Unit[this.unitList.length-1];
        for (int i=0; i<index; i++){
            tempList[i] = this.unitList[i];
        }
        for (int i=index+1; i<this.unitList.length; i++){
            tempList[i-1] = this.unitList[i];
        }
        this.unitList = tempList;
        return true;
    }

    public MilitaryUnit[] getArmy(){
        MilitaryUnit[] tempList = new MilitaryUnit[this.size()];
        int count = 0;
        for (int i=0; i<this.size(); i++) {
            if (this.unitList[i] instanceof MilitaryUnit) {
                tempList[count] = (MilitaryUnit) this.unitList[i];
                count++;
            }
        }
        int reSizer=0;
        for (int i=0; i<tempList.length; i++){
            if (tempList[i] == null){
                reSizer=i;
                break;
            }
        }
        MilitaryUnit[] tempList2 = new MilitaryUnit[reSizer];
        for (int i=0; i<reSizer; i++){
            tempList2[i] = tempList[i];
        }
        return tempList2;

    }

}

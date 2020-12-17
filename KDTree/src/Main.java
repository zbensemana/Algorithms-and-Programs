

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println();
        boolean meh=false;
        int[] x= {3,4,5};
        int[] y= {4,4,5};
        List<Datum> list = new ArrayList<Datum>();
        Datum queryPoint = new Datum(x);
        Datum queryPoint2 = new Datum(y);
        Datum queryPoint3 = new Datum(y);
        Datum queryPoint4 = new Datum(y);
        Datum queryPoint5 = new Datum(y);
        list.add(queryPoint2);
        list.add(queryPoint3);
        list.add(queryPoint4);
        list.add(queryPoint);

        for (int i=0;i<list.size();i++){
            int same=0;
            for (int j=0;j<queryPoint.x.length; j++){
                if (list.get(i).x[j]==queryPoint.x[j]){
                    same++;
                }

            }
            if (same>=queryPoint.x.length){
                meh = true;
            }
        }

        System.out.println(meh);


    }


    public boolean isItInList(Datum queryPoint, List<Datum> list){
        for (int i=0;i<list.size();i++){
            int same=0;
            for (int j=0;j<queryPoint.x.length; j++){
                if (list.get(i).x[j]==queryPoint.x[j]){
                    same++;
                }
            }
            if (same>=queryPoint.x.length){
                return true;
            }
        }

        return false;
    }
}

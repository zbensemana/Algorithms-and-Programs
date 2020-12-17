
public class Main {

    public static void main(String[] args) {


        Tile t11 = new Tile(1,1);
        Tile t12 = new Tile(1,2);
        Tile t21 = new Tile(2,1);
        Tile t22 = new Tile(2,2);


        // clan Waterbender
        Unit []waterBenders = new Unit[4];
        // clan Firebender
        Unit []fireBenders = new Unit[4];

            waterBenders[0] = new Worker(t11, 50, "Waterbender");
            waterBenders[1] = new Settler(t11, 20, "Waterbender");
            waterBenders[2] = new Archer(t11, 40, "Waterbender");
            waterBenders[3] = new Warrior(t11, 100, "Waterbender");

//            fireBenders[0] = new Worker(t22, 55, "Firebender");
//            fireBenders[1] = new Settler(t22, 15, "Firebender");
//            fireBenders[2] = new Archer(t22, 45, "Firebender");
//            fireBenders[3] = new Warrior(t22, 90, "Firebender");




//        Tile n = new Tile(5,6);
//        Tile f = new Tile(5,7);
//
//    Archer achilles = new Archer(n,2.0,"Monotheism");
//    Warrior hector = new Warrior(f, 2.0, "Judaism");
//
//    achilles.takeAction(f);


    }
}

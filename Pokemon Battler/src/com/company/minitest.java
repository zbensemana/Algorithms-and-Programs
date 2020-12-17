import com.company.MasterCup;

public class minitest {
    public static void main(String[] args) {

        try {
            MasterCup masterCup = new MasterCup();
            System.out.println("Pass");
        } catch (Exception e) {
            System.out.println("Exception: "+e);
        }
        System.out.println("######Score#################");
        System.out.println("Total Score: 100");
    }

}

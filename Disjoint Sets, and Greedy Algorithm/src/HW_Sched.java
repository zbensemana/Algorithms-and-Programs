import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//No Collaborators
// Zachary Bensemana
// 260863976

class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 * Return -1 if a1 > a2
	 * Return 1 if a1 < a2
	 * Return 0 if a1 = a2 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		// TODO Implement this

//        Compared by weight
		if (a1.weight>a2.weight){
			return -1;
		} else if (a2.weight>a1.weight){
			return 1;
		} else {
			return 0;
		}

	}
}

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
	
	
	/**
	 * 
	 * @return Array where output[i] corresponds to the assignment 
	 * that will be done at time i.
	 */
	public int[] SelectAssignments() {
		//TODO Implement this
		
		//Sort assignments
		//Order will depend on how compare function is implemented
		Collections.sort(Assignments, new Assignment());

		int[] schedule = new int[lastDeadline];

//		Sets all elements to -1
		for (int i = 0; i<schedule.length; i++){
		    schedule[i] = -1;
        }



		int count = schedule.length;


//		Puts the highest weight in the day before its deadline. If that spot is full, checks deadline ahead and repeat

		for(int i = 0; i<Assignments.size(); i++) {
            Assignment temp = Assignments.get(i);
            for (int j = temp.deadline - 1; j>=0; j--){
                if (schedule[j]==-1){
//                    System.out.println("schedule " + Arrays.toString(schedule) + " weight = " + temp.weight + " deadline " + temp.deadline);
//                    System.out.println("temp is " + temp.number);
                    schedule[j]= temp.number;

                    count--;
                    break;
                }
            }

//            only allows Assignments to be added until schedule is full
			if (count<0){
				break;
			}

        }

//        System.out.println("schedule FINAL is " + Arrays.toString(schedule));
		
		return schedule;
	}
}
	




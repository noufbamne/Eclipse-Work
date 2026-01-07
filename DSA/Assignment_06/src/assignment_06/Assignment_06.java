package assignment_06;
import java.util.*;

class Job{
	char id;
	int deadline;
	int profit;
	
	public Job(char id, int deadline, int profit) {
		this.id= id;
		this.deadline =deadline;
		this.profit = profit;
	}
}

public class Assignment_06 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		System.out.print("Enter number of jobs: ");
		int n = sc.nextInt();
		
		ArrayList <Job> jobs = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			System.out.print("Enter job id: ");
			char id = sc.next().charAt(0);
			
			System.out.print("Enter deadline: ");
			int deadline = sc.nextInt();
			
			System.out.print("Enter profit: ");
			int profit = sc.nextInt();
			
			jobs.add(new Job(id, deadline, profit));
		}
		
		jobs.sort((j1, j2) -> j2.profit - j1.profit);
		
		int maxdeadline = 0;
		for(Job job: jobs) {
			maxdeadline = Math.max(maxdeadline, job.deadline);
		}
		
		boolean [] slots = new boolean[maxdeadline];
		char [] result = new char[maxdeadline];
		int totalprofit = 0;
		
		for(Job j: jobs) {
			for(int i = j.deadline -1; i >= 0; i++) {
				if(!slots[i]) {
					slots[i] = true;
					result[i] = j.id;
					totalprofit = totalprofit + j.profit;
					break;
				}
			}
		}
		
		System.out.println("Job Sequence: ");
		for(char c : result) {
			if(c != '\0') {
				System.out.print(c + " ");
			}
		}
		
		System.out.println("Maximum Profit: " + totalprofit);
		
	}

}

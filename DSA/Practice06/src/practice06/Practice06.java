package practice06;
import java.util.*;

import assignment_06.Job;

class Job{
	char id;
	int deadline;
	int profit;
	
	public Job(int id, int deadline, int profit) {
		this.id = id;
		this.deadline = deadline;
		this.profit = profit;
	}
}
public class Practice06 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
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
		for(Job job : jobs) {
			maxdeadline = Math.max(maxdeadline, job.deadline);
		}
		
		char[] result = new char[maxdeadline];
		int[] slots = new int[maxdeadline];
		
		int totalcost =0;
		
		for(Job j : jobs) {
			for(int i = j.deadline -1; i >=0; i++) {
				if(!slots[i]) {
					slots[i] = true;
					
				}
			}
		}
		
	}

}

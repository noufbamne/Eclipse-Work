package assignment_06_pq;
import java.util.*;

class Job{
	char id;
	int profit, deadline;
	
	public Job(char id, int deadline, int profit) {
		this.id = id;
		this.deadline = deadline;
		this. profit= profit;
	}
}
public class Assignment_06_pq {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter number of jobs: ");
		int n = sc.nextInt();
		
		Job[] jobs = new Job[n];
		
		for(int i = 0; i < n; i++) {
			System.out.print("Enter job id: ");
			char id = sc.next().charAt(0);
			
			System.out.print("Enter deadline: ");
			int deadline = sc.nextInt();
			
			System.out.print("Enter profit: ");
			int profit = sc.nextInt();
			
			jobs[i] = new Job(id, deadline, profit);
		}
		
		Arrays.sort(jobs, (j1, j2) -> j1.deadline - j2.deadline);
		
		int maxdeadline = 0;
		for(Job job: jobs) maxdeadline = Math.max(maxdeadline, job.deadline);

		
		PriorityQueue<Job> maxHeap = new PriorityQueue<>((j1, j2) -> j2.profit - j1.profit);
		List<Character> result = new ArrayList<>();
		int i = n-1;
		int totalcost =0;
		
		for(int slot = maxdeadline; slot > 0; slot--) {
			while(i >= 0 && jobs[i].deadline >= slot) {
				maxHeap.add(jobs[i]);
				totalcost = totalcost+ jobs[i].profit;
				i--;
			}
		
			if(!maxHeap.isEmpty()) {
				result.add(maxHeap.poll().id);
			}
		}
		
		Collections.reverse(result);
		
		System.out.print("Job Sequence: ");
		for(char c : result) {
			if(c != '\0') {
				System.out.print(c + " ");
			}
		}
		System.out.println("Maximum Profit: " + totalcost);
	}
}

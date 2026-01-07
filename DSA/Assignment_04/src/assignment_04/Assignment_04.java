package assignment_04;
import java.util.*;

public class Assignment_04 {

	
	public static void subsetSumAll(int[]arr, int index, int target, List<Integer> current, List<List<Integer>> allResults) {
		
		if(target == 0) {
			allResults.add(new ArrayList<>(current));
			return;
		}
		
		if(index == arr.length || target < 0) {
			return;
		}
		
		//1.include
		current.add(arr[index]);
		subsetSumAll(arr, index + 1, target - arr[index], current, allResults);
		current.remove(current.size()-1);
		
		//2.exclude
		subsetSumAll(arr, index + 1, target, current, allResults);
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		
		System.out.print("Enter the size of the array: ");
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		System.out.print("Enter the elements of the array: ");
		for(int i =0; i < n; i++) {
			arr[i]= sc.nextInt();
		}
		
		System.out.print("Enter the target sum: ");
		int target = sc.nextInt();
		
		List<Integer> current = new ArrayList<>();
		List<List<Integer>> allResults = new ArrayList<>();
		
		subsetSumAll(arr, 0, target, current, allResults);
		
		if(allResults.isEmpty()) {
			System.out.print("No subset found");
		}else {
			System.out.println("Subset that sums to "+ target+ " : ");
			for(int i = 0; i < allResults.size(); i++) {
				System.out.println(allResults.get(i));
			}
		}
	}
}

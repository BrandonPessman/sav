package sorts;

public class BubbleSort implements SortInterface {
	boolean isDone = false;
	String name = "Bubble Sort";
	
	public int[] sort(int[] numbersToSort) {
		isDone = true;
		for (int i = 0; i < numbersToSort.length; i++) {
			if (i != numbersToSort.length - 1) {
				if (numbersToSort[i] > numbersToSort[i + 1]) {
					int temp = numbersToSort[i];
					numbersToSort[i] = numbersToSort[i + 1];
					numbersToSort[i + 1] = temp;
					isDone = false;
				}
			}
		}
		
		return numbersToSort;
	}
	
	public boolean checkIfDone() {
		if (isDone) {
			isDone = false;
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return name;
	}
}

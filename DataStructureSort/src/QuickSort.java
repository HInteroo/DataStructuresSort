
import java.util.Random;


public class QuickSort <T extends Comparable<? super T>> extends TestTimes implements SortInterface<T> {

	private T Pivot;
	PivotType pivotType = PivotType.FirstElement;
	
	@Override
	public void sort(T[] arrayToStart) {
		long startTime = System.nanoTime();
		quickSort(arrayToStart, 0, arrayToStart.length - 1);
//		showArray(arrayToStart, 0, arrayToStart.length - 1);
		long endTime = System.nanoTime();
		long testTime = endTime - startTime;
		addTestTime(testTime);
	}
	
	public QuickSort.PivotType getPivotType(){
		return pivotType;
	}
	
	public void setPivotType(QuickSort.PivotType pivotType){
		this.pivotType = pivotType;
	}
	
	public void choosePivot(T[] array, int first, int last) {

		T temp;
		int mid = (first + last) / 2;

		switch(this.pivotType){
		case FirstElement:
			break;
		case RandomElement:
			int rand = new Random().nextInt(array.length);
			temp = array[first];
			array[first] = array[rand];
			array[rand] = temp;
			break;
		case MidOfFirstMidLastElement:
//			System.out.println(array[first]+""+array[mid]+""+array[last]);
			if (array[first].compareTo(array[mid])>0) {
				temp = array[first];
				array[first] = array[mid];
				array[mid] = temp;
			}
			if(array[mid].compareTo(array[last])>0) {
				temp = array[mid];
				array[mid] = array[last];
				array[last] = temp;
				
			}
			if(array[first].compareTo(array[mid])>0) {
				temp = array[first];
				array[first] = array[mid];
				array[mid] = temp;
			}
			temp = array[first];
			array[first] = array[mid];
			array[mid] = temp;
			break;
		}
//		System.out.println(array[first]+""+array[mid]+""+array[last]);
	}

	/**************************************************/
	/*                private methods                 */
	/**************************************************/

	private void quickSort(T[] array, int first, int last) {
		int pivotIndex;
		if (first < last) {
			pivotIndex = partition(array, first, last);
			quickSort(array, first, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, last);
		}
	}

	private int partition(T[] array, int first, int last) {
		this.choosePivot(array, first, last); 
		T tempItem;
		int firstUnknown;
		int lastS1 = first;
		Pivot = array[first];
//		System.out.println(Pivot);
		for ( firstUnknown = first + 1 ; firstUnknown <= last ; firstUnknown++ ) {
			if (array[firstUnknown].compareTo(Pivot) < 0) {
				lastS1++;
				tempItem = array[firstUnknown];
				array[firstUnknown] = array[lastS1];
				array[lastS1] = tempItem;
			}
		}
		
		tempItem = array[first];
		array[first] = array[lastS1];
		array[lastS1] = tempItem;
		
//		showArray(array, first, last);
		
		return lastS1;
	}
	
	
	public static enum PivotType {
		FirstElement, RandomElement, MidOfFirstMidLastElement};
	
//	private void showArray(T[] array, int first, int last) {
//		System.out.print("Array (" + first + ", " + last + ") = [");
//		for ( int i = first ; i <= last ; i++ ) {
//			if (i != last) {
//				System.out.print(array[i] + ", ");
//			} else {
//				System.out.print(array[i]);
//			}
//		}
//		System.out.println("]");
//	}
}

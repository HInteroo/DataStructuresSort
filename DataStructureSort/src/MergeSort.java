
public class MergeSort<T extends Comparable<? super T>> extends TestTimes implements SortInterface<T> {
	@Override
	public void sort(T[] arrayToSort) {
		long startTime = System.nanoTime();
		Object[] tempArray = new Object[arrayToSort.length];
		mergeSort(arrayToSort, tempArray, 0, arrayToSort.length - 1);
		long endTime = System.nanoTime();
		long testTime = endTime - startTime;
		addTestTime(testTime);
	}

	/**************************************************/
	/*                private methods                 */
	/**************************************************/
	
	private void mergeSort(T[] array, 
						   Object[] tempArray, 
						   int first, 
						   int last) {
		if (first < last) {
			int mid = (first + last) / 2;
			mergeSort(array, tempArray, first, mid);
			mergeSort(array, tempArray, mid + 1, last);
			merge(array, tempArray, first, mid, last);
//			showArray(array, first, last);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private void merge(T[] array, 
			           Object[] tempArray, 
			           int first, 
			           int mid, 
			           int last) {
		
		int first1 = first;
		int first2 = mid + 1;
		
		int last1 = mid;
		int last2 = last;
		
		int index = first;
		
		
		while ((first1 <= last1) && (first2 <= last2)) {
			if (array[first1].compareTo(array[first2]) < 0) {
				tempArray[index++] = array[first1++];
			} else {
				tempArray[index++] = array[first2++];
			}
		}
		
		while (first1 <= last1) {
			tempArray[index++] = array[first1++];
		}
		
		while (first2 <= last2) {
			tempArray[index++] = array[first2++];
		}
		
		for ( int i = first ; i <= last ; i++ ) {
			array[i] = (T) tempArray[i];
		}
	}
	
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

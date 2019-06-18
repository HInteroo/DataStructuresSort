import java.util.Random;

public class Driver implements DriverInterface {

	public static void main(String[] args) {
		Driver d = new Driver();

		
		d.runSorts(ArrayType.Equal, 1000, 10);
		d.runSorts(ArrayType.Random, 1000, 10);
		d.runSorts(ArrayType.Increasing, 1000, 10);
		d.runSorts(ArrayType.Decreasing, 1000, 10);
		d.runSorts(ArrayType.IncreasingAndRandom, 1000, 10);
		d.runSorts(ArrayType.Equal, 10000, 10);
		d.runSorts(ArrayType.Random, 10000, 10);
		d.runSorts(ArrayType.Increasing, 10000, 10);
		d.runSorts(ArrayType.Decreasing, 10000, 10);
		d.runSorts(ArrayType.IncreasingAndRandom, 10000, 10);
	}

	@Override
	public Integer[] createArray(ArrayType arrayType, int arraySize) {

		Integer[] array = new Integer[arraySize];
		Random rand = new Random();

		switch (arrayType) {
		case Equal:
			for (int i = 0; i < array.length; i++) {
				array[i] = new Integer(1);
			}

			break;
		case Increasing:
			for (int i = 0; i < array.length; i++) {
				array[i] = new Integer(i);
			}

			break;
		case Random:
			for (int i = 0; i < array.length; i++) {
				array[i] = new Integer(rand.nextInt());
			}
			break;
		case Decreasing:
			for (int i = 0; i < array.length; i++) {// 0,1,2,3,4,5..,9999
				array[i] = new Integer(array.length - (i + 1));
			}
			break;
		case IncreasingAndRandom:
			int i;
			for (i = 0; i < array.length * 0.9; i++) {
				array[i] = new Integer(i);
			}
			for (i = (int) (array.length * 0.9); i < array.length; i++) {
				array[i] = new Integer(rand.nextInt());
			}
			break;
		}

		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TestTimes[] runSorts(ArrayType arrayType, int arraySize, int numberOfTimes) {
		QuickSort<Integer> QS = new QuickSort<Integer>();
		QuickSort<Integer> QSRN = new QuickSort<Integer>();
		QuickSort<Integer> QSM = new QuickSort<Integer>();
		QS.setPivotType(QS.pivotType.FirstElement);
		QSRN.setPivotType(QSRN.pivotType.RandomElement);
		QSM.setPivotType(QSM.pivotType.MidOfFirstMidLastElement);
		TestTimes.MemoryUnits mu = TestTimes.MemoryUnits.KiloBytes;
		TestTimes.TimeUnits ms = TestTimes.TimeUnits.MicroSeconds;
		TestTimes[] arr = new TestTimes[5];
		arr[0] = new MergeSort<Integer>();
		arr[1] = QS;
		arr[2] = QSRN;
		arr[3] = QSM;
		arr[4] = new HeapSort<Integer>();
		String ss;
		for (int x = 0; x < 5; x++) {
			TestTimes obj = arr[x];
			for (int i = 0; i < numberOfTimes; i++) {
				if (obj instanceof MergeSort) {
					((MergeSort<Integer>) obj).sort((this.createArray(arrayType, arraySize)));
				} else if (obj instanceof HeapSort) {
					((HeapSort<Integer>) obj).sort((this.createArray(arrayType, arraySize)));
				} else if (obj instanceof QuickSort) {
					((QuickSort<Integer>) obj).sort((this.createArray(arrayType, arraySize)));
				}
			}
		}
		String[] s = { "MergeSort", "QuickSort.FirstElement", "QuickSort.RandomElement",
				"QuickSort MidOfFirstMidLastElement", "HeapSort" };

		for (int i = 0; i < 5; i++) {
			arr[i].setMemoryUnits(mu);
		}

		for (int i = 0; i < 5; i++) {
			arr[i].setTimeUnits(ms);
		}
		System.out.println("KilloBytes\n");
		for (int i = 0; i < 5; i++) {
			System.out.println(s[i] + "\n");
			System.out.println(arr[i].getMemoryUsages());
			System.out.println("\nAverage: \n"+arr[i].getAverageMemoryUsage());
			System.out.println();
		}

		System.out.println();
		System.out.println("TestTimes: \n");

		for (int i = 0; i < 5; i++) {
			System.out.println(s[i] + "\n");
			System.out.println(arr[i].getTestTimes());
			System.out.println("\nAverage: \n"+arr[i].getAverageTestTime());
			System.out.println();
		}

		return arr;

	}
}

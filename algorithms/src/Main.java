import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int[] arr = new int[]{88, 77, 66, 55, 44, 33, 22, 11};
        QUICK_SORT(arr, 0, arr.length -1);
        printArray(arr);

        testAlgorithmPerformance(10000);
        testAlgorithmPerformance(15000);
        testAlgorithmPerformance(20000);
    }


    public static void testAlgorithmPerformance(double size) {
        System.out.println("\tTesting for size " + (int)size);
        System.out.println("--------------------------------------------");

        // Best Case
        System.out.println("Testing best case");

        int[] bestCaseArray = new int[(int) size];
        for (int i = 0; i < size; i++) {
            bestCaseArray[i] = 5;
        }

        long bestCaseRuntimeSumProposed = 0;
        long bestCaseRuntimeSumClassical = 0;

        for (int i = 0; i < 5; i++) {
            int[] bestCaseArrayCopy = bestCaseArray.clone();

            // Measure runtime for the proposed algorithm
            long startTime1 = System.nanoTime();
            QUICK_SORT(bestCaseArrayCopy, 0, bestCaseArrayCopy.length - 1);
            long endTime1 = System.nanoTime();
            bestCaseRuntimeSumProposed += (endTime1 - startTime1);

            // Measure runtime for the classical algorithm
            long startTime2 = System.nanoTime();
            classicalQuickSort(bestCaseArrayCopy, 0, bestCaseArrayCopy.length - 1);
            long endTime2 = System.nanoTime();
            bestCaseRuntimeSumClassical += (endTime2 - startTime2);
        }

        System.out.println("Runtime of proposed algorithm: " + (bestCaseRuntimeSumProposed / 5) + " nanoseconds");
        System.out.println("Runtime of classical algorithm: " + (bestCaseRuntimeSumClassical / 5) + " nanoseconds");

        System.out.println("--------------------------------------------");

        // Average Case
        System.out.println("Testing average case");

        long averageCaseRuntimeSumProposed = 0;
        long averageCaseRuntimeSumClassical = 0;

        for (int j = 0; j < 5; j++) {
            int[] averageCaseArray = new int[(int) size];
            for (int i = 0; i < Math.ceil(size / 2); i++) {
                averageCaseArray[i] = 2 * i + 1;
                averageCaseArray[i + (int) Math.ceil(size / 2)] = 2 * i + 2;
            }
            int[] averageCaseArrayCopy = averageCaseArray.clone();

            // Measure runtime for the proposed algorithm
            long startTime3 = System.nanoTime();
            QUICK_SORT(averageCaseArrayCopy, 0, averageCaseArrayCopy.length - 1);
            long endTime3 = System.nanoTime();
            averageCaseRuntimeSumProposed += (endTime3 - startTime3);

            // Measure runtime for the classical algorithm
            long startTime4 = System.nanoTime();
            classicalQuickSort(averageCaseArrayCopy, 0, averageCaseArrayCopy.length - 1);
            long endTime4 = System.nanoTime();
            averageCaseRuntimeSumClassical += (endTime4 - startTime4);
        }

        System.out.println("Runtime of proposed algorithm: " + (averageCaseRuntimeSumProposed / 5) + " nanoseconds");
        System.out.println("Runtime of classical algorithm: " + (averageCaseRuntimeSumClassical / 5) + " nanoseconds");

        System.out.println("--------------------------------------------");

        // Worst Case
        System.out.println("Testing worst case");

        long worstCaseRuntimeSumProposed = 0;
        long worstCaseRuntimeSumClassical = 0;

        for (int k = 0; k < 5; k++) {
            int[] worstCaseArray = new int[(int) size];
            for (int i = 0; i < size; i++) {
                worstCaseArray[i] = (int) size - i;
            }
            int[] worstCaseArrayCopy = worstCaseArray.clone();

            // Measure runtime for the proposed algorithm
            long startTime5 = System.nanoTime();
            QUICK_SORT(worstCaseArrayCopy, 0, worstCaseArrayCopy.length - 1);
            long endTime5 = System.nanoTime();
            worstCaseRuntimeSumProposed += (endTime5 - startTime5);

            // Measure runtime for the classical algorithm
            long startTime6 = System.nanoTime();
            classicalQuickSort(worstCaseArrayCopy, 0, worstCaseArrayCopy.length - 1);
            long endTime6 = System.nanoTime();
            worstCaseRuntimeSumClassical += (endTime6 - startTime6);
        }

        System.out.println("Runtime of proposed algorithm: " + (worstCaseRuntimeSumProposed / 5) + " nanoseconds");
        System.out.println("Runtime of classical algorithm: " + (worstCaseRuntimeSumClassical / 5) + " nanoseconds");

        System.out.println("--------------------------------------------");
    }


    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random rn = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rn.nextInt(99901) + 100;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    //Algorithm 1: Quick Sort
    public static void QUICK_SORT(int[] arr, int low, int high) {
        int N = high - low + 1;
        if (N <= 3) {
            MANUAL_SORT(arr, low, high);
        } else {
            double pivot = calculate_pivot(arr, low, high);
            System.out.println("pivot is " + pivot);
            int q = PARTITION(arr, low, high, pivot);
            QUICK_SORT(arr, low, q);
            QUICK_SORT(arr, q + 1, high);
        }
    }

    //Procedure 1: Manual Sort
    public static int[] MANUAL_SORT(int[] arr, int low, int high) {
        int N = high - low + 1;

        if (N <= 1) {
            return arr;
        } else if (N == 2) {
            if (arr[low] > arr[high]) {
                int j = arr[low];
                arr[low] = arr[high];
                arr[high] = j;
            }
        } else if (N == 3) {
            if (arr[low] > arr[high - 1]) {
                int j = arr[low];
                arr[low] = arr[high - 1];
                arr[high - 1] = j;
            }
        }
        if (arr[low] > arr[high]) {
            int j = arr[low];
            arr[low] = arr[high];
            arr[high] = j;
        }
        if (arr[high - 1] > arr[high]) {
            int j = arr[high - 1];
            arr[high - 1] = arr[high];
            arr[high] = j;
        }
        return arr;
    }

    //TO DO
    private static int PARTITION(int[] arr, int low, int high, double pivot) {
        int i = low;
        int j = high;

        while (true) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;

            if (i >= j)
                return j;

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;

            // Check if i and j cross each other
            if (i > j)
                return j;
        }
    }


    private static double calculate_pivot(int[] arr, int low, int high) {
        // Find the minimum and maximum values in the first half of the array
        double min1 = min(arr, low, high);
        double max1 = max(arr, low, high);
        double mean1 = (min1 + max1) / ((high / 2) - low + 1);

        /*// Find the minimum and maximum values in the second half of the array
        double min2 = min(arr, high / 2 + 1, high);
        double max2 = max(arr, high / 2 + 1, high);
        double mean2 = (min2 + max2) / (high - (high / 2 + 1) + 1);
        System.out.println(mean2 + " min is " + min2 + " max is " + max2 + " low is " + (high / 2 + 1) + " high is  " + high);*/

        // Calculate the pivot as the average of the maximum and minimum values from each half
        return mean1;
    }



    //supporting methods
    public static double min(int[] arr, int low, int high) {
        int min = arr[low];
        for (int i = low; i <= high; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static double max(int[] arr, int low, int high) {
        int max = arr[low];
        for (int i = low; i <= high; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private static int[] classicalQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int qt = classicalPartition(arr, low, high);
            classicalQuickSort(arr, low, qt - 1);
            classicalQuickSort(arr, qt + 1, high);
        }
        return arr;
    }

    private static int classicalPartition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Choose the rightmost element as the pivot
        int i = low - 1; // Index of the smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to the pivot
            if (arr[j] <= pivot) {
                // Increment index of smaller element
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (place pivot element at its correct position)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

}

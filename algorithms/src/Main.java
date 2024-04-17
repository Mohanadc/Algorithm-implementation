import java.util.Random;

public class Main {
    public static void main(String[] args) {
        test25AlgorithmPerformance();
        test50AlgorithmPerformance();
        test100AlgorithmPerformance();
    }


    public static void testAlgorithmPerformance() {
        // Define array sizes for testing
        int[] inputSizes = {25, 50, 100};

        // Repeat the test for each input size
        for (int size : inputSizes) {
            System.out.println("Testing for input size: " + size);

            // Generate random arrays for testing
            int[] arr1 = generateRandomArray(size);
            int[] arr2 = arr1.clone(); // Make a copy for testing the classical algorithm

            // Measure runtime for the proposed algorithm
            long startTime1 = System.nanoTime();
            QUICK_SORT(arr1, 0, arr1.length - 1);
            long endTime1 = System.nanoTime();
            long runtime1 = endTime1 - startTime1;

            System.out.println("Runtime of proposed algorithm: " + runtime1 + " nanoseconds");

            // Measure runtime for the classical algorithm
            long startTime2 = System.nanoTime();
            classicalQuickSort(arr2, 0, arr2.length - 1);
            long endTime2 = System.nanoTime();
            long runtime2 = endTime2 - startTime2;

            System.out.println("Runtime of classical algorithm: " + runtime2 + " nanoseconds");

            System.out.println("--------------------------------------------");
        }
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
    public static void QUICK_SORT(int[] arr, int low,int high){
        int N = high - low + 1;
        if (N <= 3) {
            MANUAL_SORT(arr, low, high);
        } else {
            double pivot = calculate_pivot(arr, low, high);
            int q = PARTITION(arr, low, high, pivot);
            QUICK_SORT(arr, low, q);
            QUICK_SORT(arr, q + 1, high);
        }
    }
    //Procedure 1: Manual Sort
    public static int[] MANUAL_SORT(int[] arr, int low, int high) {
        int N = high-low + 1;

        if (N<=1) {
            return arr;
        }
        else if(N==2){
            if(arr[low]>arr[high]){
                int j= arr[low];
                arr[low] = arr[high];
                arr[high] = j;
            }
        }
        else if(N==3){
            if(arr[low] > arr[high-1]){
                int j= arr[low];
                arr[low] = arr[high-1];
                arr[high-1] = j;
            }
        }
        if(arr[low]>arr[high]){
            int j= arr[low];
            arr[low] = arr[high];
            arr[high] = j;
        }
        if(arr[high-1]>arr[high]){
            int j= arr[high-1];
            arr[high-1] = arr[high];
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
        }
    }
    private static double calculate_pivot(int[] arr, int low, int high) {

      double sum = 0;
        for (int i = low; i <= high; i++) {
            sum += arr[i];
        }
        return sum / (high - low + 1);
    }


    //supporting methods
    public static double min(int[] arr, int low, int high){
        int min=arr[0];
        for(int i=low;i<=high;i++){
            if (arr[i]<min) {
                min=arr[i];
            }
        }
        return min;
    }
    public static double max(int[] arr, int low, int high){
        int max=arr[0];
        for(int i=low;i<=high;i++){
            if (arr[i]>max) {
                max=arr[i];
            }
        }
        return max;
    }

    private static int[] classicalQuickSort(int[] arr, int low, int high){
        if(low < high){
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

    public static void test25AlgorithmPerformance() {
        System.out.println("--------------------------------------------");
        System.out.println("\tTesting for size 25");
        System.out.println("--------------------------------------------");

        // Best Case
        System.out.println("Testing best case");

        int[] bestCaseArray = new int[25];
        for (int i = 0; i < 25; i++) {
            bestCaseArray[i] = i + 1;
        }

        int[] bestCaseArray2 = bestCaseArray.clone(); // Make a copy for testing the classical algorithm

        // Measure runtime for the proposed algorithm
        long startTime1 = System.nanoTime();
        QUICK_SORT(bestCaseArray, 0, bestCaseArray.length - 1);
        long endTime1 = System.nanoTime();
        long runtime1 = endTime1 - startTime1;

        System.out.println("Runtime of proposed algorithm: " + runtime1 + " nanoseconds");

        // Measure runtime for the classical algorithm
        long startTime2 = System.nanoTime();
        classicalQuickSort(bestCaseArray2, 0, bestCaseArray2.length - 1);
        long endTime2 = System.nanoTime();
        long runtime2 = endTime2 - startTime2;

        System.out.println("Runtime of classical algorithm: " + runtime2 + " nanoseconds");

        System.out.println("--------------------------------------------");

        // Average Case
        System.out.println("Testing average case");

        int[] averageCaseArray = new int[25];
        for (int i = 0; i < 13; i++) {
            averageCaseArray[i] = 2 * i + 1;
            averageCaseArray[i + 12] = 2 * i + 2;
        }
        int[] averageCaseArray2 = averageCaseArray.clone();

        // Measure runtime for the proposed algorithm
        long startTime3 = System.nanoTime();
        QUICK_SORT(averageCaseArray, 0, averageCaseArray.length - 1);
        long endTime3 = System.nanoTime();
        long runtime3 = endTime3 - startTime3;

        System.out.println("Runtime of proposed algorithm: " + runtime3 + " nanoseconds");

        // Measure runtime for the classical algorithm
        long startTime4 = System.nanoTime();
        classicalQuickSort(averageCaseArray2, 0, averageCaseArray2.length - 1);
        long endTime4 = System.nanoTime();
        long runtime4 = endTime4 - startTime4;

        System.out.println("Runtime of classical algorithm: " + runtime4 + " nanoseconds");

        System.out.println("--------------------------------------------");

        // Worst Case
        System.out.println("Testing worst case");

        int[] worstCaseArray = new int[25];
        for (int i = 0; i <25; i++) {
            worstCaseArray[i] = 25 - i;
        }
        int[] worstCaseArray2 = worstCaseArray.clone();

        // Measure runtime for the proposed algorithm
        long startTime5 = System.nanoTime();
        QUICK_SORT(worstCaseArray, 0, worstCaseArray.length - 1);
        long endTime5 = System.nanoTime();
        long runtime5 = endTime5 - startTime5;

        System.out.println("Runtime of proposed algorithm: " + runtime5 + " nanoseconds");

        // Measure runtime for the classical algorithm
        long startTime6 = System.nanoTime();
        classicalQuickSort(worstCaseArray2, 0, worstCaseArray2.length - 1);
        long endTime6 = System.nanoTime();
        long runtime6 = endTime6 - startTime6;

        System.out.println("Runtime of classical algorithm: " + runtime6 + " nanoseconds");

        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println();
    }
    public static void test50AlgorithmPerformance() {
        System.out.println("\tTesting for size 50");
        System.out.println("--------------------------------------------");

        // Best Case
        System.out.println("Testing best case");

        int[] bestCaseArray = new int[50];
        for (int i = 0; i < 50; i++) {
            bestCaseArray[i] = i + 1;
        }
        int[] bestCaseArray2 = bestCaseArray.clone(); // Make a copy for testing the classical algorithm

        // Measure runtime for the proposed algorithm
        long startTime1 = System.nanoTime();
        QUICK_SORT(bestCaseArray, 0, bestCaseArray.length - 1);
        long endTime1 = System.nanoTime();
        long runtime1 = endTime1 - startTime1;

        System.out.println("Runtime of proposed algorithm: " + runtime1 + " nanoseconds");

        // Measure runtime for the classical algorithm
        long startTime2 = System.nanoTime();
        classicalQuickSort(bestCaseArray2, 0, bestCaseArray2.length - 1);
        long endTime2 = System.nanoTime();
        long runtime2 = endTime2 - startTime2;

        System.out.println("Runtime of classical algorithm: " + runtime2 + " nanoseconds");

        System.out.println("--------------------------------------------");

        // Average Case
        System.out.println("Testing average case");

        int[] averageCaseArray = new int[50];
        for (int i = 0; i < 25; i++) {
            averageCaseArray[i] = 2 * i + 1;
            averageCaseArray[i + 25] = 2 * i + 2;
        }
        int[] averageCaseArray2 = averageCaseArray.clone();

        // Measure runtime for the proposed algorithm
        long startTime3 = System.nanoTime();
        QUICK_SORT(averageCaseArray, 0, averageCaseArray.length - 1);
        long endTime3 = System.nanoTime();
        long runtime3 = endTime3 - startTime3;

        System.out.println("Runtime of proposed algorithm: " + runtime3 + " nanoseconds");

        // Measure runtime for the classical algorithm
        long startTime4 = System.nanoTime();
        classicalQuickSort(averageCaseArray2, 0, averageCaseArray2.length - 1);
        long endTime4 = System.nanoTime();
        long runtime4 = endTime4 - startTime4;

        System.out.println("Runtime of classical algorithm: " + runtime4 + " nanoseconds");

        System.out.println("--------------------------------------------");

        // Worst Case
        System.out.println("Testing worst case");

        int[] worstCaseArray = new int[50];
        for (int i = 0; i < 50; i++) {
            worstCaseArray[i] = 50 - i;
        }
        int[] worstCaseArray2 = worstCaseArray.clone();

        // Measure runtime for the proposed algorithm
        long startTime5 = System.nanoTime();
        QUICK_SORT(worstCaseArray, 0, worstCaseArray.length - 1);
        long endTime5 = System.nanoTime();
        long runtime5 = endTime5 - startTime5;

        System.out.println("Runtime of proposed algorithm: " + runtime5 + " nanoseconds");

        // Measure runtime for the classical algorithm
        long startTime6 = System.nanoTime();
        classicalQuickSort(worstCaseArray2, 0, worstCaseArray2.length - 1);
        long endTime6 = System.nanoTime();
        long runtime6 = endTime6 - startTime6;

        System.out.println("Runtime of classical algorithm: " + runtime6 + " nanoseconds");

        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println();
    }
    public static void test100AlgorithmPerformance() {
        System.out.println("\tTesting for size 100");
        System.out.println("--------------------------------------------");

        // Best Case
        System.out.println("Testing best case");

        int[] bestCaseArray = new int[100];
        for (int i = 0; i < 100; i++) {
            bestCaseArray[i] = i + 1;
        }
        int[] bestCaseArray2 = bestCaseArray.clone(); // Make a copy for testing the classical algorithm

        // Measure runtime for the proposed algorithm
        long startTime1 = System.nanoTime();
        QUICK_SORT(bestCaseArray, 0, bestCaseArray.length - 1);
        long endTime1 = System.nanoTime();
        long runtime1 = endTime1 - startTime1;

        System.out.println("Runtime of proposed algorithm: " + runtime1 + " nanoseconds");

        // Measure runtime for the classical algorithm
        long startTime2 = System.nanoTime();
        classicalQuickSort(bestCaseArray2, 0, bestCaseArray2.length - 1);
        long endTime2 = System.nanoTime();
        long runtime2 = endTime2 - startTime2;

        System.out.println("Runtime of classical algorithm: " + runtime2 + " nanoseconds");

        System.out.println("--------------------------------------------");

        // Average Case
        System.out.println("Testing average case");

        int[] averageCaseArray = new int[100];
        for (int i = 0; i < 50; i++) {
            averageCaseArray[i] = 2 * i + 1;
            averageCaseArray[i + 50] = 2 * i + 2;
        }
        int[] averageCaseArray2 = averageCaseArray.clone();

        // Measure runtime for the proposed algorithm
        long startTime3 = System.nanoTime();
        QUICK_SORT(averageCaseArray, 0, averageCaseArray.length - 1);
        long endTime3 = System.nanoTime();
        long runtime3 = endTime3 - startTime3;

        System.out.println("Runtime of proposed algorithm: " + runtime3 + " nanoseconds");

        // Measure runtime for the classical algorithm
        long startTime4 = System.nanoTime();
        classicalQuickSort(averageCaseArray2, 0, averageCaseArray2.length - 1);
        long endTime4 = System.nanoTime();
        long runtime4 = endTime4 - startTime4;

        System.out.println("Runtime of classical algorithm: " + runtime4 + " nanoseconds");

        System.out.println("--------------------------------------------");

        // Worst Case
        System.out.println("Testing worst case");

        int[] worstCaseArray = new int[100];
        for (int i = 0; i < 100; i++) {
            worstCaseArray[i] = 100 - i;
        }
        int[] worstCaseArray2 = worstCaseArray.clone();

        // Measure runtime for the proposed algorithm
        long startTime5 = System.nanoTime();
        QUICK_SORT(worstCaseArray, 0, worstCaseArray.length - 1);
        long endTime5 = System.nanoTime();
        long runtime5 = endTime5 - startTime5;

        System.out.println("Runtime of proposed algorithm: " + runtime5 + " nanoseconds");

        // Measure runtime for the classical algorithm
        long startTime6 = System.nanoTime();
        classicalQuickSort(worstCaseArray2, 0, worstCaseArray2.length - 1);
        long endTime6 = System.nanoTime();
        long runtime6 = endTime6 - startTime6;

        System.out.println("Runtime of classical algorithm: " + runtime6 + " nanoseconds");

        System.out.println("--------------------------------------------");
    }

}

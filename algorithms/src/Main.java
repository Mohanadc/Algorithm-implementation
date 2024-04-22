import java.sql.SQLOutput;
import java.util.Random;

public class Main {
    static int noOfChecks = 0;
    static int noOfSwaps = 0;
    static int noOfChecks1 = 0;
    static int noOfSwaps1 = 0;
    public static void main(String[] args) {

        int[] arr = new int[]{88, 77, 66, 55, 44, 33, 22, 11};
        QUICK_SORT(arr, 0, arr.length -1);
        printArray(arr);

        testAlgorithmPerformance(10000);
        //testAlgorithmPerformance(5000);
        //testAlgorithmPerformance(15000);
    }


    public static void testAlgorithmPerformance(double size) {
        System.out.println("\tTesting for size " + (int)size);
        System.out.println("--------------------------------------------");

        // Best Case
        System.out.println("Testing best case");

        int[] bestCaseArray = new int[(int) size];
        for (int i = 0; i < size; i++) {
            bestCaseArray[i] = 11 + (i * 11);

        }
       // printArray(bestCaseArray);

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

        System.out.println("Runtime of proposed algorithm: " + (bestCaseRuntimeSumProposed / 5) + " nanoseconds in " + noOfChecks/5 + " checks in " + noOfSwaps/5 + " swaps");
        System.out.println("Runtime of classical algorithm: " + (bestCaseRuntimeSumClassical / 5) + " nanoseconds in " + noOfChecks1/5 + " checks in " + noOfSwaps1/5 + " swaps");
        noOfChecks = 0;
        noOfSwaps = 0;
        noOfSwaps1 = 0;
        noOfChecks1 = 0;

        System.out.println("--------------------------------------------");

        // Average Case
        System.out.println("Testing average case");

        long averageCaseRuntimeSumProposed = 0;
        long averageCaseRuntimeSumClassical = 0;


        for (int j = 0; j < 100; j++) {
            int[] averageCaseArray = new int[(int) size];
            for (int i = 0; i < size; i++) {
                averageCaseArray[i] = 11 + (i * 11);
            }
            int[] averageCaseArrayCopy = averageCaseArray.clone();
            //printArray(averageCaseArrayCopy);
            shuffleArray(averageCaseArrayCopy);
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

        System.out.println("Runtime of proposed algorithm: " + (averageCaseRuntimeSumProposed / 100) + " nanoseconds in " + noOfChecks/100 + " checks in " + noOfSwaps/100 + " swaps");
        noOfChecks = 0;
        noOfSwaps = 0;
        System.out.println("Runtime of classical algorithm: " + (averageCaseRuntimeSumClassical / 100) + " nanoseconds in " + noOfChecks1/100 + " checks in " + noOfSwaps1/100 + " swaps");
        noOfChecks1 = 0;
        noOfSwaps1 = 0;

        System.out.println("--------------------------------------------");

        // Worst Case
        System.out.println("Testing worst case");

        long worstCaseRuntimeSumProposed = 0;
        long worstCaseRuntimeSumClassical = 0;

        for (int k = 0; k < 5; k++) {
            int[] worstCaseArray = new int[(int) size];
            for (int i = 0; i < size; i++) {
                if(i%2 == 0)
                worstCaseArray[i] = 88 - (i*i);
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

        System.out.println("Runtime of proposed algorithm: " + (worstCaseRuntimeSumProposed / 5) + " nanoseconds in " + noOfChecks/5 + " checks in " + noOfSwaps/5 + " swaps");
        System.out.println("Runtime of classical algorithm: " + (worstCaseRuntimeSumClassical / 5) + " nanoseconds in " + noOfChecks1/5 + " checks in " + noOfSwaps1/5 + " swaps");

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

    public static void printArray(int[] arr, int low, int high) {
        for (int i = low; i <= high; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //Algorithm 1: Quick Sort
    public static void QUICK_SORT(int[] arr, int low, int high) {
        int N = high - low + 1;
        //System.out.println("size:" + N);
        if (N <= 3) {
            MANUAL_SORT(arr, low, high);
        } else {
            double pivot = calculate_pivot(arr, low, high);
            int q = PARTITION(arr, low, high, pivot);
            //System.out.println("pivot is " + pivot);
            //System.out.println("low: " + low);
            //System.out.println("high: " + high);
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
                noOfSwaps++;
            }
        } else if (N == 3) {
            if (arr[low] > arr[high - 1]) {
                int j = arr[low];
                arr[low] = arr[high - 1];
                arr[high - 1] = j;
                noOfSwaps++;
            }
        }
        if (arr[low] > arr[high]) {
            int j = arr[low];
            arr[low] = arr[high];
            arr[high] = j;
            noOfSwaps++;
        }
        if (arr[high - 1] > arr[high]) {
            int j = arr[high - 1];
            arr[high - 1] = arr[high];
            arr[high] = j;
            noOfSwaps++;
        }
        return arr;
    }

    //TO DO
    public static int PARTITION(int[] arr, int low, int high, double pivot) {
        int i = low - 1;
        int j = high + 1;
        int z = 0;
        //printArray(arr, low, high);
        while (true) {
            z++;
            do {
                j--;
                noOfChecks++;
            } while (arr[j] > pivot);
            do {
                i++;
                noOfChecks++;
            } while (arr[i] < pivot);

           /* System.out.println("i: " + arr[i] + "\nj: " + arr[j]);
            System.out.println("pivot: " + pivot);
            System.out.println("iteration number " + z);*/
            //printArray(arr);

            noOfChecks++;
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                noOfSwaps++;
            } else {
               //System.out.println("returning " + j);
                return j;
            }
        }
    }

    private static double calculate_pivot(int[] arr, int low, int high) {
        // Find the minimum and maximum values in the first half of the array
       // System.out.println("pivot calc");
        //System.out.println("low is " );
        //printArray(arr, low, high);
        int high1 = high - (high - low)/2;
        double min1 = min(arr, low, high1);
        //System.out.println(min1);
        double max1 = max(arr, low, high1);
        //System.out.println(max1);
        double mean1 = (min1 + max1) / 2;
        //System.out.println("mean1: " + mean1 +"min is " + min1 + " max is " + max1 + " low is " + low + " high is  " + high1);

        //Find the minimum and maximum values in the second half of the array
        double min2 = min(arr, high1 + 1, high);
        //System.out.println(min2);
        double max2 = max(arr, high1 + 1, high);
        //System.out.println(max2);
        double mean2 = (min2 + max2) / 2;
        //System.out.println("mean2: " + mean2 + " min is " + min2 + " max is " + max2 + " low is " + (high1 + 1) + " high is  " + high);

        // Calculate the pivot as the average of the maximum and minimum values from each half
        return (mean1 + mean2)/ 2;
    }



    //supporting methods
    public static double min(int[] arr, int low, int high) {
        int min = arr[low];
        //System.out.println("min is " + min);
        for (int i = low; i <= high; i++) {
            noOfChecks++;
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        //System.out.println("min is " + min);
        return min;
    }

    public static double max(int[] arr, int low, int high) {
        int max = arr[low];
        //System.out.println("max is " + max);
        for (int i = low; i <= high; i++) {
            noOfChecks++;
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //System.out.println("max is " + max);
        return max;
    }

    private static int[] classicalQuickSort(int[] arr, int low, int high) {
        noOfChecks1++;
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
            noOfChecks1++;
            if (arr[j] <= pivot) {
                // Increment index of smaller element
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                noOfSwaps1++;
            }
        }

        // Swap arr[i+1] and arr[high] (place pivot element at its correct position)
        noOfSwaps1++;
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void shuffleArray(int[] array) {
        int n = array.length;
        Random random = new Random();
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Swap array[i] and array[j]
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}



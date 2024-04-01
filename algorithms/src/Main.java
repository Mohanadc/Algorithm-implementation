

public class Main {
    public static void main(String[] args) {
        int[] arr = {7, 2, 1, 6, 8, 5, 3, 4};

        System.out.println("Original array:");
        printArray(arr);

        QUICK_SORT(arr, 0, arr.length - 1);

        System.out.println("Sorted array:");
        printArray(arr);

    }
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    //Algorithm 1: Quick Sort
    public static void QUICK_SORT(int[] arr, int low,int high){
        int N=high-low+1;
        if (N<=3) {
            MANUAL_SORT(arr, low, high);
        }
        else{ 
            double pivot=calculate_pivot(arr,low,high);
            int q=PARTITION(arr, low,high,pivot);
            QUICK_SORT(arr, low, q);
            QUICK_SORT(arr, q+1, high);
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
        int i = low - 1;
        int j = high + 1;

        do {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;

            if (i >= j)
                break;

        } while (true);

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        return j;
    }
    private static double calculate_pivot(int[] arr, int low, int high) {

        int middle = (high + low)/2;
        return ( min(arr,low,middle) + max(arr,low,middle) + min(arr,middle ,high) + max(arr,low,high))/4;
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
}

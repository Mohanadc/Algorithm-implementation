

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


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
    //quickSort
    //piviotSelction
    public static int low(int[] arr){
        int min=arr[0];
        for(int i=0;i<=arr.length-1;i++){
            if (arr[i]<min) {
                min=arr[i];
            }
        }
        return min;
    }
    public static int high(int[] arr){
        int high=arr[0];
        for(int i=0;i<=arr.length-1;i++){
            if (arr[i]>high) {
                high=arr[i];
            }
        }
        return high;
    }
}

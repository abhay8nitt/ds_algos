package sorting;

public class SortingAlgorithms {

    SortingAlgorithms(){}

    /**
     * Iterative Bubble Sort
     * @param A
     */
    public void bubbleSort(int A[]){
        int size = A.length;
        boolean isSwapped ;
        for (int i = 0; i < size-1; i++) {
            isSwapped = false;
            for (int j = 0; j < size-i-1; j++) {
                if (A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                    isSwapped = true;
                }
            }
            if(!isSwapped){
                break;
            }
        }
    }

    /**
     * Recursive Bubble sort
     */

    public void _bubbleSort(int A[], int size){
        if (size == 1) return;
        for (int j = 0; j < size-1; j++) {
            if (A[j] > A[j + 1]) {
                int temp = A[j];
                A[j] = A[j + 1];
                A[j + 1] = temp;
            }
        }
        _bubbleSort(A, size - 1);
    }

    /**
     * Insertion Sort Iterative
     * @param data
     */
    public void insertionSort(int []data){
        int size = data.length;
        for(int i = 1;i < size; i++){
            int key = data[i];
            int j = i-1;
            while(j >= 0 && key < data[j]){
                data[j+1] = data[j];
                j--;
            }
            data[j+1] = key;
        }
    }

    /**
     * Insertion Sort Recursive
     */

    public void _insertionSort(int A[], int size){
        if(size == 0) return;
        _insertionSort(A, size - 1);

        int key = A[size];
        int j = size-1;

        while(j >= 0 && key < A[j]){
            A[j+1] = A[j];
            j--;
        }
        A[j+1] = key;
    }

    /**
     * Recursive Merge Sort
     * @param A
     */
    public void mergeSort(int A[]){
        mergeSort(A,0,A.length-1);
    }

    private void mergeSort(int A[], int left, int right){
        if(left < right){
            int mid = left+(right-left)/2;
            mergeSort(A,left,mid);
            mergeSort(A,mid+1,right);
            merge(A,left,mid,right);
        }
    }

    private void merge(int[] A, int left, int mid, int right) {
        int S1  = mid - left +1;
        int S2  = right - mid;
        int L[] = new int[S1];
        int R[] = new int[S2];

        for(int i = 0;i < S1; i++){
            L[i] = A[left+i];
        }
        for(int j = 0;j < S2; j++){
            R[j] = A[mid+j+1];
        }
        int i = 0;
        int j = 0;
        int k = left;
        while(i < S1 && j < S2){
            if(L[i]<=R[j]){
                A[k] = L[i];
                i++;
            }else{
                A[k] = R[j];
                j++;
            }
            k++;
        }
        while(i<S1){
            A[k] = L[i];
            k++;
            i++;
        }
        while(j<S2){
            A[k] = R[j];
            k++;
            j++;
        }
    }

    /**
     * Recursive Quick Sort
     */

    public void quickSort(int A[], int low, int high){
        if(low < high){
            int pivotIndex = partition(A,low,high);
            quickSort(A,low, pivotIndex - 1);
            quickSort(A,pivotIndex + 1, high);
        }
    }

    private int partition(int[] A, int low, int high) {
        int pivot = A[high];
        int i = low - 1;

        for(int j = low;j<high;j++){
            if(A[j] <=pivot){
                i++;
                int temp  = A[j];
                A[j] = A[i];
                A[i] = temp;
            }
        }
        int temp  = A[i+1];
        A[i+1] = A[high];
        A[high] = temp;
        return i + 1;
    }

    /**
     * Heap Sort
     */

    public void heap_sort(int A[]){
        build_max_heap(A);
        for(int i = A.length - 1;i>=0;i--){
            int temp = A[i];
            A[i] = A[0];
            A[0] = temp;
            heapify(A, i, 0);
        }

    }

    private void build_max_heap(int[] A) {
        for (int i = A.length/2 -1;i>=0;i--){
            heapify(A,i,0);
        }
    }

    private void heapify(int[] A, int size, int index) {
        int largest = index;
        int left  = 2 * index + 1;
        int right = 2 * index + 2;
        if(left < size && A[left] > A[largest]){
            largest = left;
        }
        if(right < size && A[right] > A[largest]){
            largest = right;
        }

        if(largest != index){
            int temp = A[largest];
            A[largest] = A[index];
            A[index] = temp;
            heapify(A,size,largest);
        }
    }

    public void selectionSort(int []A){
        for(int i = 0;i< A.length;i++){
            int min = i;
            for(int j = i+1;j<A.length;j++){
                if(A[j] < A[min]){
                    min = j;
                }
            }
            int temp = A[i];
            A[i] = A[min];
            A[min] = temp;
        }
    }
}

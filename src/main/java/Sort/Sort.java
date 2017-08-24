package Sort;

import java.util.Arrays;

public class Sort {
    private static final int[] arr = {18, 6, 61, 16, 33, 0, 3};

    public static void main(String[] args) {
//        bubbleSort(arr);
//        cocktailSort(arr);
//        selectionSort(arr);
//        insertionSort(arr);
//        insertionSortDichotomy(arr);
//        shellSort(arr);
//        Merge.mergeSort(arr);
        QuickSort.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 比较相邻的元素，如果前一个比后一个大，就把它们两个调换位置。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * 分类 -------------- 内部比较排序
     * 数据结构 ---------- 数组
     * 最差时间复杂度 ---- O(n^2)
     * 最优时间复杂度 ---- 如果能在内部循环第一次运行时,使用一个旗标来表示有无需要交换的可能,可以把最优时间复杂度降低到O(n)
     * 平均时间复杂度 ---- O(n^2)
     * 所需辅助空间 ------ O(1)
     * 稳定性 ------------ 稳定
     */
    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1])
                    swap(arr, j, j + 1);
            }
        }

    }

    /**
     * 鸡尾酒排序，也叫定向冒泡排序，是冒泡排序的一种改进。
     * 此算法与冒泡排序的不同处在于从低到高然后从高到低，而冒泡排序则仅从低到高去比较序列里的每个元素。
     * 他可以得到比冒泡排序稍微好一点的效能。
     * 分类 -------------- 内部比较排序
     * 数据结构 ---------- 数组
     * 最差时间复杂度 ---- O(n^2)
     * 最优时间复杂度 ---- 如果序列在一开始已经大部分排序过的话,会接近O(n)
     * 平均时间复杂度 ---- O(n^2)
     * 所需辅助空间 ------ O(1)
     * 稳定性 ------------ 稳定
     */

    private static void cocktailSort(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            for (int i = left; i < right; i++) {
                if (arr[i] > arr[i + 1])
                    swap(arr, i, i + 1);
            }
            right--;
            for (int i = right; i > left; i--) {
                if (arr[i - 1] > arr[i])
                    swap(arr, i - 1, i);
            }
            left++;
        }
    }

    /**
     * 初始时在序列中找到最小（大）元素，放到序列的起始位置作为已排序序列；
     * 然后，再从剩余未排序元素中继续寻找最小（大）元素，放到已排序序列的末尾。
     * 以此类推，直到所有元素均排序完毕。
     * 分类 -------------- 内部比较排序
     * 数据结构 ---------- 数组
     * 最差时间复杂度 ---- O(n^2)
     * 最优时间复杂度 ---- O(n^2)
     * 平均时间复杂度 ---- O(n^2)
     * 所需辅助空间 ------ O(1)
     * 稳定性 ------------ 不稳定
     */
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min])
                    min = j;
            }
            if (min != i)
                swap(arr, min, i);

        }
    }

    /**
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 将新元素插入到该位置后
     * 重复步骤2~5
     * <p>
     * 分类 ------------- 内部比较排序
     * 数据结构 ---------- 数组
     * 最差时间复杂度 ---- 最坏情况为输入序列是降序排列的,此时时间复杂度O(n^2)
     * 最优时间复杂度 ---- 最好情况为输入序列是升序排列的,此时时间复杂度O(n)
     * 平均时间复杂度 ---- O(n^2)
     * 所需辅助空间 ------ O(1)
     * 稳定性 ------------ 稳定
     */
    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int get = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > get) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = get;
        }
    }

    /**
     * 二分插入排序
     * 分类 -------------- 内部比较排序
     * 数据结构 ---------- 数组
     * 最差时间复杂度 ---- O(n^2)
     * 最优时间复杂度 ---- O(nlogn)
     * 平均时间复杂度 ---- O(n^2)
     * 所需辅助空间 ------ O(1)
     * 稳定性 ------------ 稳定
     */
    private static void insertionSortDichotomy(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int get = arr[i];
            int left = 0;
            int right = i - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (arr[mid] > get)
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            for (int j = i - 1; j >= left; j--) {
                arr[j + 1] = arr[j];
            }
            arr[left] = get;
        }
    }

    /**
     * 希尔排序，也叫递减增量排序，是插入排序的一种更高效的改进版本。希尔排序是不稳定的排序算法。
     * 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
     * 插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率
     * 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位
     * 分类 -------------- 内部比较排序
     * 数据结构 ---------- 数组
     * 最差时间复杂度 ---- 根据步长序列的不同而不同。已知最好的为O(n(logn)^2)
     * 最优时间复杂度 ---- O(n)
     * 平均时间复杂度 ---- 根据步长序列的不同而不同。
     * 所需辅助空间 ------ O(1)
     * 稳定性 ------------ 不稳定
     */
    private static void shellSort(int[] arr) {
        int h = 0;    //初始增量
        while (h <= arr.length)
            h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < arr.length; i++) {
                int j = i - h;   //相对增量位置
                int get = arr[i];
                while (j >= 0 && arr[j] > get) {
                    arr[j + h] = arr[j];
                    j = j - h;  //原始位置
                }
                arr[j + h] = get;
            }
            h = (h - 1) / 3;
        }
    }

    /**
     * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
     * 设定两个指针，最初位置分别为两个已经排序序列的起始位置
     * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
     * 重复步骤3直到某一指针到达序列尾
     * 将另一序列剩下的所有元素直接复制到合并序列尾
     * 分类 -------------- 内部比较排序
     * 数据结构 ---------- 数组
     * 最差时间复杂度 ---- O(nlogn)
     * 最优时间复杂度 ---- O(nlogn)
     * 平均时间复杂度 ---- O(nlogn)
     * 所需辅助空间 ------ O(n)
     * 稳定性 ------------ 稳定
     */

    private static class Merge {

        /**
         * 合并两个已排好序的数组A[left...mid]和A[mid+1...right]
         */
        private static void merge(int[] arr, int left, int mid, int right) {
            int len = right - left + 1;
            int[] temp = new int[len];
            int index = 0;
            int i = left;  //前一数组起始元素
            int j = mid + 1;  //后一数组起始元素
            while (i <= mid && j <= right)
                temp[index++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
            while (i <= mid)
                temp[index++] = arr[i++];
            while (j <= right)
                temp[index++] = arr[j++];
            for (int k = 0; k < len; k++) {
                arr[left++] = temp[k];
            }
        }

        /**
         * 递归实现归并排序
         */
        private static void mergeSortRecursion(int arr[], int left, int right) {
            if (left == right)
                return;
            int mid = (left + right) / 2;
            mergeSortRecursion(arr, left, mid);
            mergeSortRecursion(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }

        private static void mergeSort(int[] arr) {
            mergeSortRecursion(arr, 0, arr.length - 1);
        }

    }

    /**
     * 堆排序
     */
    private static class heapSort {

    }

    /**
     * 快速排序
     * <p>
     * 从序列中挑出一个元素，作为"基准"(pivot).
     * 把所有比基准值小的元素放在基准前面，所有比基准值大的元素放在基准的后面（相同的数可以到任一边），这个称为分区(partition)操作。
     * 对每个分区递归地进行步骤1~2，递归的结束条件是序列的大小是0或1，这时整体已经被排好序了。
     *
     * 分类 ------------ 内部比较排序
     * 数据结构 --------- 数组
     * 最差时间复杂度 ---- 每次选取的基准都是最大（或最小）的元素，导致每次只划分出了一个分区，需要进行n-1次划分才能结束递归，时间复杂度为O(n^2)
     * 最优时间复杂度 ---- 每次选取的基准都是中位数，这样每次都均匀的划分出两个分区，只需要logn次划分就能结束递归，时间复杂度为O(nlogn)
     * 平均时间复杂度 ---- O(nlogn)
     * 所需辅助空间 ------ 主要是递归造成的栈空间的使用(用来保存left和right等局部变量)，取决于递归树的深度，一般为O(logn)，最差为O(n)
     * 稳定性 ---------- 不稳定
     */
    private static class QuickSort {
        private static void quickSort(int[] arr, int left, int right) {
            int i,j,t,temp;
            if (left > right)
                return;
            temp = arr[left];  //基数
            i = left;
            j = right;
            while (i != j){
                //顺序很重要，要先从右边开始找
                while (arr[j] >= temp && i<j)
                    j--;
                //再从左边开始找
                while (arr[i] <= temp && i<j)
                    i++;
                if(i < j){
                    t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
            //最终将基数归位
            arr[left] = arr[i];
            arr[i] = temp;


            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }


    private static void swap(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

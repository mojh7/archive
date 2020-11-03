package com.company;

import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        int[] originArr = new int[]{8, 16, 5, 20, 12, 5, 25, 13};
        printArr(originArr, "origin arr");

        int[] mergeSortArr = originArr.clone();
        int[] quickSortArr = originArr.clone();
        int[] insertionSortArr = originArr.clone();

        MergeSort ms = new MergeSort();
        QuickSort qs = new QuickSort();
        InsertionSort is = new InsertionSort();

        ms.mergeSort(mergeSortArr, 0, mergeSortArr.length - 1);
        printArr(mergeSortArr, "merge sort");

        qs.quickSort(quickSortArr, 0, quickSortArr.length - 1);
        printArr(quickSortArr,"quick sort");

        is.insertionSort(insertionSortArr);
        printArr(insertionSortArr, "insertion sort");
    }

    public static void printArr(int[] arr, String name) {
        System.out.printf("%s : ", name);
        Arrays.stream(arr)
                .forEach(e -> System.out.printf("%d ", e));
        System.out.println();
    }
}

class MergeSort {
    // 오름차순 기준으로 작성
    // 나누는 역할을 함.
    public void mergeSort(int[] list, int start, int end) {
        if(start < end) {
            int mid = (start + end) / 2;
            mergeSort(list, start, mid);
            mergeSort(list, mid + 1, end);
            merge(list, start, mid, end);
        }
    }

    // 나눈 부분 배열을 합침.
    private void merge(int[] list, int start, int mid, int end) {
        int leftListIdx = start;
        int rightListIdx = mid + 1;
        int tempListIdx = 0;
        int[] temp = new int[end - start + 1];

        // 분할된 좌측 배열과 우측 배열의 첫 원소부터 비교하여 새로운 배열의 추가
        while(leftListIdx <= mid && rightListIdx <= end) {
            if(list[leftListIdx] <= list[rightListIdx]) {
                temp[tempListIdx] = list[leftListIdx++];
            } else {
                temp[tempListIdx] = list[rightListIdx++];
            }
            tempListIdx++;
        }

        // 남아있는 원소 추가
        for(int idx = leftListIdx; idx <= mid; idx++) {
            temp[tempListIdx++] = list[idx];
        }
        for(int idx = rightListIdx; idx <= end; idx++) {
            temp[tempListIdx++] = list[idx];
        }

        // 나눈 배열을 다시 conquer하여 정렬한 값을
        // 원래 배열에 복사
        for(int idx = 0; idx < temp.length; idx++) {
            list[start + idx] = temp[idx];
        }
    }
}

class QuickSort {
    public void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];

        while(l <= r) {
            while(arr[l] < pivot) {
                l++;
            }
            while(pivot < arr[r]) {
                r--;
            }

            if(l <= r) {
                if(l != r) {
                    int tmp = arr[l];
                    arr[l] = arr[r];
                    arr[r] = tmp;
                }
                l++;
                r--;
            }
        }

        if(left < r) {
            quickSort(arr, left, r);
        }
        if(l < right) {
            quickSort(arr, l, right);
        }
    }
}

class InsertionSort {
    // 오름차순 기준
    public void insertionSort(int[] arr) {
        if(arr.length < 2) {
            return;
        }

        for(int idx = 1; idx < arr.length; idx++) {
            int standard = arr[idx];
            int curIdx = idx - 1;
            while (curIdx >= 0 && arr[curIdx] > standard) {
                arr[curIdx + 1] = arr[curIdx];
                curIdx--;
            }
            arr[curIdx + 1] = standard;
        }
    }
}

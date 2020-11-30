package com.mshernandez.kth_smallest_analysis;

/**
 * Finds the kth smallest element by sorting the
 * input list and grabbind the element at the index
 * corresponding to k.
 * 
 * Theoretical Complexity: O(n*log(n))
 */
public class MergeSortKthSmallestAlgorithm extends KthSmallestAlgorithm
{
    public MergeSortKthSmallestAlgorithm()
    {
        super("Kth Smallest Via Merge Sort");
    }

    @Override
    public int findKthElement(int[] nums, int k)
    {
        mergeSort(nums, 0, nums.length - 1);
        return nums[k - 1];
    }

    /**
     * Sorts the array using the internal
     * merge sort method for testing purposes.
     * 
     * @param nums The array to sort.
     */
    public void sortArray(int[] nums)
    {
        mergeSort(nums, 0, nums.length - 1);
    }

    /**
     * Recursively sort a section of an
     * array using the merge sort method.
     * 
     * @param nums The array to sort.
     * @param start The starting index of the section.
     * @param end The ending index of the section.
     */
    private void mergeSort(int[] nums, int start, int end)
    {
        if (start >= end)
        {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, mid, end);
    }

    /**
     * Merge two sorted sections of the array
     * into a larger sorted section.
     * 
     * @param nums The array containing the sections.
     * @param start The starting index of the first section.
     * @param mid The last index of the first section.
     * @param end The last index of the second section.
     */
    private void merge(int[] nums, int start, int mid, int end)
    {
        int aPtr = start;
        int bPtr = mid + 1;
        int[] sorted = new int[end - start + 1];
        for (int i = 0; i < sorted.length; i++)
        {
            if (aPtr <= mid && bPtr <= end)
            {
                sorted[i] = nums[aPtr] < nums[bPtr] ? nums[aPtr++] : nums[bPtr++];
            }
            else if (aPtr <= mid)
            {
                sorted[i] = nums[aPtr++];
            }
            else
            {
                sorted[i] = nums[bPtr++];
            }
        }
        System.arraycopy(sorted, 0, nums, start, sorted.length);
    }
}
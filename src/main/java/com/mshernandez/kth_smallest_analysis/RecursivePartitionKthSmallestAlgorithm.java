package com.mshernandez.kth_smallest_analysis;

import static com.mshernandez.kth_smallest_analysis.Utilities.swap;

/**
 * Finds the kth smallest element by using the
 * partitioning procedure of the QuickSort algorithm
 * recursively.
 * 
 * Theoretical Complexity: O(n^2)
 */
public class RecursivePartitionKthSmallestAlgorithm extends KthSmallestAlgorithm
{
    public RecursivePartitionKthSmallestAlgorithm()
    {
        super("Kth Smallest Via Recursive Partitioning");
    }

    @Override
    public int findKthSmallestElement(int[] nums, int k)
    {
        return partition(nums, 0, nums.length - 1, 0, k - 1);
    }

    /**
     * Recursively partition an array until the element at
     * the target index is in its final sorted position.
     * 
     * @param nums The array to partition.
     * @param start The start index of the section to partition.
     * @param end The end index of the section to partition.
     * @param pivotVal The value to pivot elements around.
     * @return The element at the target index after sorting.
     */
    private int partition(int[] nums, int start, int end,
                          int pivotValPos, int targetIndex)
    {
        int pivotVal = nums[pivotValPos];
        swap(nums, pivotValPos, end);
        int i = start;
        for (int j = start; j < end; j++)
        {
            if (nums[j] <= pivotVal)
            {
                swap(nums, i++, j);
            }
        }
        swap(nums, i, end);
        if (targetIndex == i)
        {
            return nums[targetIndex];
        }
        else if (targetIndex < i)
        {
            end = i - 1;
        }
        else
        {
            start = i + 1;
        }
        return partition(nums, start, end, start, targetIndex);
    }
}
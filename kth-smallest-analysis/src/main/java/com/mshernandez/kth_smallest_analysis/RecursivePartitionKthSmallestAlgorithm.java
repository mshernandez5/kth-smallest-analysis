package com.mshernandez.kth_smallest_analysis;

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
     * Partition a section of an array around a pivot value,
     * returning a pivot index such that all values before
     * that index are below the pivot value and those above
     * the pivot value are after the pivot index.
     * 
     * @param nums The array to partition.
     * @param start The start index of the section to partition.
     * @param end The end index of the section to partition.
     * @param pivotVal The value to pivot elements around.
     * @return The location of the newly created pivot index.
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
    
    /**
     * Swap two integer elements in an array
     * using the XOR swap method.
     * 
     * @param nums The array containing the elements.
     * @param indexA The index of the first element.
     * @param indexB The index of the second element.
     */
    private void swap(int[] nums, int indexA, int indexB)
    {
        if (nums[indexA] == nums[indexB])
        {
            return;
        }
        nums[indexA] ^= nums[indexB];
        nums[indexB] ^= nums[indexA];
        nums[indexA] ^= nums[indexB];
    }
}
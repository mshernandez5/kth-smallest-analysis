package com.mshernandez.kth_smallest_analysis;

import static com.mshernandez.kth_smallest_analysis.Utilities.swap;

/**
 * Finds the kth smallest element by using the
 * partitioning procedure of the QuickSort algorithm
 * using the median of medians method to choose an
 * optimal pivot position.
 * 
 * Theoretical Complexity: O(n^2)
 */
public class MedianOfMediansKthSmallestAlgorithm extends KthSmallestAlgorithm
{
    private static final int SUBSET_SIZE = 5;

    public MedianOfMediansKthSmallestAlgorithm()
    {
        super("Kth Smallest Via Median Of Medians Partitioning");
    }

    @Override
    public int findKthSmallestElement(int[] nums, int k)
    {
        int start = 0;
        int end = nums.length - 1;
        int pivotPos = selectPivot(nums, 0, end, k);
        int targetIndex = k - 1;
        do
        {
            pivotPos = partition(nums, start, end, start);
            if (targetIndex > pivotPos)
            {
                start = pivotPos + 1;
            }
            else if (targetIndex < pivotPos)
            {
                end = pivotPos - 1;
            }
        } while (pivotPos != targetIndex);
        return nums[targetIndex];
    }

    private int selectPivot(int[] nums, int start, int end, int k)
    {
        int numSubsets = (end - start + 1) / SUBSET_SIZE;
        if (numSubsets == 0)
        {
            sort(nums, start, end);
            return k;
        }
        int[] medians = new int[numSubsets];
        int medianOffset = SUBSET_SIZE / 2;
        // Sort Each Subset Only Up To Median Element
        for (int subset = 0; subset < numSubsets; subset++)
        {
            int base = start + subset * SUBSET_SIZE;
            int medianIndex = base + medianOffset;
            sort(nums, base, medianIndex);
            // Insert Into Sorted Medians Array
            medians[subset] = nums[medianIndex];
            for (int j = 0; j < subset; j++)
            {
                if (medians[subset] < medians[j])
                {
                    swap(nums, subset, j);
                }
            }
        }
        return medians[numSubsets / 2];
    }

    private void sort(int[] nums, int start, int end)
    {
        for (int i = start; i < end - 1; i++)
        {
            int minIndex = i;
            for (int j = i + 1; j < end; j++)
            {
                if (nums[j] < nums[minIndex])
                {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
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
    private int partition(int[] nums, int start, int end, int pivotValPos)
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
        return i;
    }
}
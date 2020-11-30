package com.mshernandez.kth_smallest_analysis;

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
    public MedianOfMediansKthSmallestAlgorithm()
    {
        super("Kth Smallest Via Median Of Medians Partitioning");
    }

    @Override
    public int findKthElement(int[] nums, int k)
    {
        return 0;
    }
}
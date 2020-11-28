package com.mshernandez.kth_smallest_analysis;

/**
 * An abstract class to represent a kth smallest algorithm,
 * leaving the specific implementation to
 * child classes.
 */
public abstract class KthSmallestAlgorithm
{
    private String algorithmName;

    /**
     * Initializes the algorithm with its name.
     */
    public KthSmallestAlgorithm(String algorithmName)
    {
        this.algorithmName = algorithmName;
    }

    /**
     * In an unsorted list of n elements, this algorithm will
     * find the kth smallest element in the list.
     * 
     * @param nums A list of numbers.
     * @param k Which k to use when finding the kth smallest element.
     * @return The value of the kth smallest element in the list.
     */
    public abstract int findKthElement(int[] nums, int k);

    /**
     * Returns the name of the algorithm.
     */
    public String getAlgorithmName()
    {
        return algorithmName;
    }

    /**
     * Returns the name of the algorithm.
     */
    @Override
    public String toString()
    {
        return algorithmName;
    }
}
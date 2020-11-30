package com.mshernandez.kth_smallest_analysis;

public class Utilities
{
    /**
     * Swap two integer elements in an array
     * using the XOR swap method.
     * 
     * @param nums The array containing the elements.
     * @param indexA The index of the first element.
     * @param indexB The index of the second element.
     */
    public static void swap(int[] nums, int indexA, int indexB)
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
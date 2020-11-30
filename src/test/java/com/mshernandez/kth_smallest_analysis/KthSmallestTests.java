package com.mshernandez.kth_smallest_analysis;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KthSmallestTests 
{
    int[] list;
    int[] sortedList;

    public void initializeFirstInput()
    {
        list = new int[] {2, 5, 6, 9, 8, 4, 2, 6, 3, 10};
        sortedList = new int[] {2, 2, 3, 4, 5, 6, 6, 8, 9, 10};
    }

    @Test
    public void mergeSortAlgorithmTest()
    {
        MergeSortKthSmallestAlgorithm algorithm = new MergeSortKthSmallestAlgorithm();
        initializeFirstInput();
        algorithm.sortArray(list);
        assertArrayEquals(sortedList, list);
        firstTest(algorithm);
    }

    @Test
    public void iterativePartitionAlgorithmTest()
    {
        firstTest(new IterativePartitionKthSmallestAlgorithm());
    }

    @Test
    public void recursivePartitionAlgorithmTest()
    {
        firstTest(new RecursivePartitionKthSmallestAlgorithm());
    }

    @Test
    public void medianOfMediansPartitionAlgorithmTest()
    {
        firstTest(new MedianOfMediansKthSmallestAlgorithm());
    }

    public void firstTest(KthSmallestAlgorithm algorithm)
    {
        initializeFirstInput();
        assertEquals(2, algorithm.findKthSmallestElement(list, 1));
        initializeFirstInput();
        assertEquals(2, algorithm.findKthSmallestElement(list, 2));
        initializeFirstInput();
        assertEquals(3, algorithm.findKthSmallestElement(list, 3));
        initializeFirstInput();
        assertEquals(4, algorithm.findKthSmallestElement(list, 4));
        initializeFirstInput();
        assertEquals(5, algorithm.findKthSmallestElement(list, 5));
        initializeFirstInput();
        assertEquals(6, algorithm.findKthSmallestElement(list, 6));
        initializeFirstInput();
        assertEquals(6, algorithm.findKthSmallestElement(list, 7));
        initializeFirstInput();
        assertEquals(8, algorithm.findKthSmallestElement(list, 8));
        initializeFirstInput();
        assertEquals(9, algorithm.findKthSmallestElement(list, 9));
        initializeFirstInput();
        assertEquals(10, algorithm.findKthSmallestElement(list, 10));
    }
}
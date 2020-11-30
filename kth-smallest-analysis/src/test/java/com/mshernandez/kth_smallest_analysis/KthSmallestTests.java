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
        assertEquals(algorithm.findKthElement(list, 1), 2);
        initializeFirstInput();
        assertEquals(algorithm.findKthElement(list, 2), 2);
        initializeFirstInput();
        assertEquals(algorithm.findKthElement(list, 3), 3);
        initializeFirstInput();
        assertEquals(algorithm.findKthElement(list, 4), 4);
        initializeFirstInput();
        assertEquals(algorithm.findKthElement(list, 5), 5);
        initializeFirstInput();
        assertEquals(algorithm.findKthElement(list, 6), 6);
        initializeFirstInput();
        assertEquals(algorithm.findKthElement(list, 7), 6);
        initializeFirstInput();
        assertEquals(algorithm.findKthElement(list, 8), 8);
        initializeFirstInput();
        assertEquals(algorithm.findKthElement(list, 9), 9);
        initializeFirstInput();
        assertEquals(algorithm.findKthElement(list, 10), 10);
    }
}
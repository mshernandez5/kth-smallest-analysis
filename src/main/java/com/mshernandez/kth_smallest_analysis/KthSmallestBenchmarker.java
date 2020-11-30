package com.mshernandez.kth_smallest_analysis;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a kth smallest algorithm, this class measures
 * the runtimes of given inputs and keeps track of
 * the average runtimes corresponding to each tested
 * input size. Testing multiple inputs of the same size
 * will automatically update the overall average times
 * of that input size.
 */
public class KthSmallestBenchmarker
{
    private static final int RANDOM_NUMBER_RANGE = 1000;

    private KthSmallestAlgorithm algorithm;
    private Map<Integer, RunningAverage> sizeRuntimeAverages;

    /**
     * Initializes a new benchmark object.
     * 
     * @param algorithm The matrix multiplier to use.
     */
    public KthSmallestBenchmarker(KthSmallestAlgorithm algorithm)
    {
        this.algorithm = algorithm;
        sizeRuntimeAverages = new TreeMap<>();
    }

    /**
     * Gets the average time to find the kth smallest
     * element for the provided list with a variety of
     * values for k.
     * 
     * @param nums A list of numbers.
     * @param numSamples The number of measurements to take.
     * @return The average time to find the kth element, in nanoseconds.
     */
    public void benchmarkInput(int[] nums, int numSamples)
    {
        long[] sampleTimes = new long[numSamples];
        for (int sample = 0; sample < numSamples; sample++)
        {
            // Test k = 1, n/4, n/2, 3n/4, n
            for (int fraction = 0; fraction <= 4; fraction++)
            {
                int k = 0;
                sampleTimes[sample] = measureRuntime(nums, (k = (nums.length * fraction) / 4) == 0 ? 1 : k);
            }
        }
        double averageTimeForInput = 0.0;
        for (long time : sampleTimes)
        {
            averageTimeForInput += time;
        }
        averageTimeForInput /= (double) numSamples;
        int inputSize = nums.length;
        if (!sizeRuntimeAverages.containsKey(inputSize))
        {
            sizeRuntimeAverages.put(inputSize, new RunningAverage());
        }
        sizeRuntimeAverages.get(inputSize).addData(averageTimeForInput);
    }

    /**
     * Measures the time it takes to find the kth
     * smallest number using the associated algorithm.
     *
     * @param nums A list of numbers.
     * @param k Which k to use when finding the kth smallest element.
     * @return The time the algorithm took in nanoseconds.
     */
    public long measureRuntime(int[] nums, int k)
    {
        int[] copiedNums = Arrays.copyOf(nums, nums.length);
        long startingTime = System.nanoTime();
        algorithm.findKthSmallestElement(copiedNums, k);
        return System.nanoTime() - startingTime;
    }

    /**
     * Gets the average runtimes for every input
     * size tested.
     * 
     * @return A mapping from input size to average runtime.
     */
    public Map<Integer, Double> getAverageRuntimes()
    {
        Map<Integer, Double> averageRuntimes = new TreeMap<>();
        for (int key : sizeRuntimeAverages.keySet())
        {
            averageRuntimes.put(key, sizeRuntimeAverages.get(key).getAverage());
        }
        return averageRuntimes;
    }

    /**
     * Gets the average runtimes for a specific
     * size tested.
     * 
     * @param size The input size to get a runtime average for.
     * @return The average runtime for the input size.
     */
    public double getAverageRuntime(int size)
    {
        if (sizeRuntimeAverages.containsKey(size))
        {
            return sizeRuntimeAverages.get(size).getAverage();
        }
        return 0.0;
    }

    /**
     * Fills an array with random values.
     * 
     * @param nums The number array to randomize.
     */
    public static void randomizeMatrix(int[] nums)
    {
        for (int i = 0; i < nums.length; i++)
        {
            nums[i] = (int) (Math.random() * RANDOM_NUMBER_RANGE) + 1;
        }
    }

    /**
     * Gets the name of the algorithm being
     * used by the matrix multiplier in use.
     * 
     * @return Name of the algoritm being used.
     */
    public String getAlgorithmName()
    {
        return algorithm.getAlgorithmName();
    }
}

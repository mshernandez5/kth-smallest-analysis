package com.mshernandez.kth_smallest_analysis;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class Main
{
    public static final int MINIMUM_LIST_SIZE = 10;
    public static final int MAXIMUM_LIST_SIZE = 1000;
    public static final int NUMBER_SAMPLES_PER_INPUT = 20;
    public static final int NUMBER_INPUTS_PER_SIZE = 1000;

    public static final String OUTPUT_FILE = "data.csv";

    /**
     * Measures the runtime to find the kth smallest
     * element in a list of n numbers for different
     * algorithms, testing a variety of input sizes
     * with a variety of situations for each size.
     * 
     * Randomly generated lists are generated starting
     * at MINIMUM_LIST_SIZE and ending at MAXIMUM_LIST_SIZE.
     * Multiple inputs are generated for each size to test
     * a variety of situations (k is at start, mid, end of list, etc)
     * and this process is repeated NUMBER_CYCLES_PER_SIZE times
     * for each input size, where a larger value increases
     * the accuracy of the average.
     * 
     * @param args Program arguments, discarded.
     */
    public static void main(String[] args)
    {
        /**
         * MatrixMultiplierBenchmarker objects keep track of the runtimes for
         * their respective matrix multipliers for different input sizes.
         * 
         * Adding a benchmarker to this list registers it with the program.
         * All registered benchmarkers will be given every set of randomly
         * generated input matrices to test so that all multipliers are benchmarked
         * with the same exact set of inputs without having to save every generated matrix.
         */
        List<KthSmallestBenchmarker> benchmarks = new ArrayList<>();
        benchmarks.add(new KthSmallestBenchmarker(/*algo1*/));
        benchmarks.add(new KthSmallestBenchmarker(/*algo2*/));
        benchmarks.add(new KthSmallestBenchmarker(/*algo3*/));
        benchmarks.add(new KthSmallestBenchmarker(/*algo4*/));

        /**
         * Generate and feed inputs into registered benchmarkers,
         * which will measure and average the runtimes for each input.
         * 
         * Inputs are generated with size n, where n begins and
         * ends at the specified boundaries.
         * 
         * A progress bar is displayed in the console to allow the user
         * to guage the completion of the program.
         */
        for (int size = MINIMUM_LIST_SIZE; size <= MAXIMUM_LIST_SIZE; size = size << 1)
        {
            int[] nums = new int[size];
            System.out.println();
            for (int input = 0; input < NUMBER_INPUTS_PER_SIZE; input++)
            {
                KthSmallestBenchmarker.randomizeMatrix(nums);
                for (KthSmallestBenchmarker benchmarker : benchmarks)
                {
                    showProgressBar("Size " + size, 50, input, NUMBER_INPUTS_PER_SIZE);
                    benchmarker.benchmarkInput(nums, NUMBER_SAMPLES_PER_INPUT);
                    System.gc();
                }
            }
        }
        System.out.println("\n");

        /**
         * Extract the runtime averages from the benchmarker objects.
         * Save the results in a CSV file.
         */
        try (PrintWriter filePrintWriter = new PrintWriter(OUTPUT_FILE))
        {
            for (KthSmallestBenchmarker benchmarker : benchmarks)
            {
                filePrintWriter.print(benchmarker.getAlgorithmName() + ",");
                Map<Integer, Double> runtimes = benchmarker.getAverageRuntimes();
                for (int size : runtimes.keySet())
                {
                    filePrintWriter.print(runtimes.get(size) + ",");
                }
                filePrintWriter.println();
            }
            System.out.println("Results Saved: " + OUTPUT_FILE);
        }
        catch (IOException e)
        {
            System.out.println("Error Writing File: " + OUTPUT_FILE);
            System.out.println("Printing Results In Console...\n");
            for (KthSmallestBenchmarker benchmarker : benchmarks)
            {
                System.out.print(benchmarker.getAlgorithmName() + ",");
                Map<Integer, Double> runtimes = benchmarker.getAverageRuntimes();
                for (int size : runtimes.keySet())
                {
                    System.out.print(runtimes.get(size) + ",");
                }
                System.out.println();
            }
        }
    }

    /**
     * Displays or updates a progress bar
     * on the current line.
     * 
     * @param label A label to show before the progress bar.
     * @param progressBarSize The length of the loading section.
     * @param currentCycle The number of cycles completed.
     * @param numberCycles The total number of cycles required.
     */
    public static void showProgressBar(String label, int progressBarSize,
                                       int currentCycle, int numberCycles)
    {
        int progress = (int) ((double) currentCycle / numberCycles * progressBarSize);
        System.out.printf("\r%-10s[", label);
        for (int i = 0; i < progressBarSize; i++)
        {
            System.out.print((i < progress) ? "▓" : " ");
        }
        System.out.print("]");
    }
}
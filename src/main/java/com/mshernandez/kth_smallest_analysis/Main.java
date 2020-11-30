package com.mshernandez.kth_smallest_analysis;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static final int MAXIMUM_LIST_SIZE = 100000;
    public static final int NUMBER_SAMPLES_PER_INPUT = 20;
    public static final int NUMBER_INPUTS_PER_SIZE = 1000;

    public static final File OUTPUT_FILE = new File("data.csv");

    /**
     * Measures the runtime to find the kth smallest
     * element in a list of n numbers for different
     * algorithms, testing a variety of input sizes
     * with a variety of situations for each size.
     * 
     * Randomly generated lists are generated starting
     * at a size of 10 and ending at MAXIMUM_LIST_SIZE.
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
        benchmarks.add(new KthSmallestBenchmarker(new MergeSortKthSmallestAlgorithm()));
        benchmarks.add(new KthSmallestBenchmarker(new IterativePartitionKthSmallestAlgorithm()));
        benchmarks.add(new KthSmallestBenchmarker(new RecursivePartitionKthSmallestAlgorithm()));
        benchmarks.add(new KthSmallestBenchmarker(new MedianOfMediansKthSmallestAlgorithm()));

        // Initialize Output File With Algorithm Column
        initializeFile(benchmarks);

        /**
         * Generate and feed inputs into registered benchmarkers,
         * which will measure and average the runtimes for each input.
         * 
         * Inputs are generated for sizes 10, 50, 100, 250, 500, 1000,
         * etc. to the specified limit.
         * 
         * A progress bar is displayed in the console to allow the user
         * to guage the completion of the program.
         */
        int inputSize = 10;
        int total = 0;
        while (inputSize <= MAXIMUM_LIST_SIZE)
        {
            int[] nums = new int[inputSize];
            System.out.println();
            for (int input = 0; input < NUMBER_INPUTS_PER_SIZE; input++)
            {
                KthSmallestBenchmarker.randomizeMatrix(nums);
                for (KthSmallestBenchmarker benchmarker : benchmarks)
                {
                    showProgressBar("Size " + inputSize, 50, input, NUMBER_INPUTS_PER_SIZE);
                    benchmarker.benchmarkInput(nums, NUMBER_SAMPLES_PER_INPUT);
                    System.gc();
                }
            }
            // Write Results To File
            writeColumn(benchmarks, inputSize);
            // Move To Next Size
            if (inputSize < 100)
            {
                total += inputSize = (total += 50);
            }
            else
            {
                total += inputSize = (total + 100);
            }
        }
        System.out.println("\n");
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

    public static void initializeFile(List<KthSmallestBenchmarker> benchmarkers)
    {
        try (PrintWriter filePrintWriter = new PrintWriter(OUTPUT_FILE))
        {
            filePrintWriter.println("Algorithm");
            for (KthSmallestBenchmarker benchmarker : benchmarkers)
            {
                filePrintWriter.println(benchmarker.getAlgorithmName());
            }
        }
        catch (IOException e)
        {
            System.out.println("Error Writing File: " + OUTPUT_FILE);
        }
    }

    public static void writeColumn(List<KthSmallestBenchmarker> benchmarkers, int size)
    {
        // Read Existing File Lines
        String[] lines = new String[benchmarkers.size() + 1];
        try (Scanner fileScanner = new Scanner(OUTPUT_FILE))
        {
            int index = 0;
            while (fileScanner.hasNext())
            {
                lines[index++] = fileScanner.nextLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Error Reading File: " + OUTPUT_FILE);
        }
        // Append New Column To Lines
        int line = 0;
        lines[line++] += ", " + size;
        for (KthSmallestBenchmarker benchmarker : benchmarkers)
        {
            lines[line++] += ", " + benchmarker.getAverageRuntime(size);
        }
        // Rewrite File
        try (PrintWriter filePrintWriter = new PrintWriter(OUTPUT_FILE))
        {
            for (int i = 0; i < lines.length; i++)
            {
                filePrintWriter.println(lines[i]);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error Writing File: " + OUTPUT_FILE);
        }
    }
}
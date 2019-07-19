package ru.javaops.masterjava.matrix;

import java.util.Random;
import java.util.concurrent.*;

/**
 * gkislin
 * 03.07.2016
 */
public class MatrixUtil {

    // TODO implement parallel multiplication matrixA*matrixB
    public static int[][] concurrentMultiply(int[][] matrixA, int[][] matrixB, ExecutorService executor) throws InterruptedException, ExecutionException {
        final int matrixSize = matrixA.length;
        final int[][] matrixC = new int[matrixSize][matrixSize];

        class ColumnMultiplyResult {
            private final int col;
            private final int[] columnC;

            public ColumnMultiplyResult(int col, int[] columnC) {
                this.col = col;
                this.columnC = columnC;
            }
        }

        final CompletionService<ColumnMultiplyResult> completionService = new ExecutorCompletionService<>(executor);
        for (int i = 0; i < matrixSize; i++) {
            final int col = i;
            final int[] thatColumn = new int[matrixSize];
            for (int k = 0; k < matrixSize; k++) {
                thatColumn[k] = matrixB[k][i];
            }
            completionService.submit(() -> {
                final int[] columnC = new int[matrixSize];
                for (int row = 0; row < matrixSize; row++) {
                    final int[] thisRow = matrixA[row];
                    int value = 0;
                    for (int k = 0; k < matrixSize; k++) {
                        value += thisRow[k] * thatColumn[k];
                    }
                    columnC[row] = value;
                }
                return new ColumnMultiplyResult(col, columnC);
            });
        }

        for (int l = 0; l < matrixSize; l++) {
            ColumnMultiplyResult result = completionService.take().get();
            for (int m = 0; m < matrixSize; m++) {
                matrixC[m][result.col] = result.columnC[m];
            }
        }
        return matrixC;
    }

    // TODO optimize by https://habrahabr.ru/post/114797/
    public static int[][] singleThreadMultiply(int[][] matrixA, int[][] matrixB) {
        final int matrixSize = matrixA.length;
        final int[][] matrixC = new int[matrixSize][matrixSize];
        try {
            for (int j = 0; j < matrixSize; j++) {
                final int[] thatColumn = new int[matrixSize];
                for (int k = 0; k < matrixSize; k++) {
                    thatColumn[k] = matrixB[j][k];
                }
                for (int i = 0; i < matrixSize; i++) {
                    final int[] thisRow = matrixA[i];
                    int sum = 0;
                    for (int k = 0; k < matrixSize; k++) {
                        sum += thisRow[k] * thatColumn[k];
                    }
                    matrixC[i][j] = sum;
                }
            }
        } catch (IndexOutOfBoundsException ignored) {
        }

//
//        for (int i = 0; i < matrixSize; i++) {
//            for (int j = 0; j < matrixSize; j++) {
//                int sum = 0;
//                for (int k = 0; k < matrixSize; k++) {
//                    sum += matrixA[i][k] * matrixB[k][j];
//                }
//                matrixC[i][j] = sum;
//            }
//        }
        return matrixC;
    }

    public static int[][] create(int size) {
        int[][] matrix = new int[size][size];
        Random rn = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rn.nextInt(10);
            }
        }
        return matrix;
    }

    public static boolean compare(int[][] matrixA, int[][] matrixB) {
        final int matrixSize = matrixA.length;
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrixA[i][j] != matrixB[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}

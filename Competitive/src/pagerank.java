import java.util.Random;
import java.util.Scanner;

public class pagerank {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        Random rand = new Random();

        while (scan.hasNextLine()) {
            String testName = scan.nextLine();
            int n = scan.nextInt();
            double alpha = scan.nextDouble();
            int visits = scan.nextInt();

            int[][] linkCount = new int[n][n];
            int[] outCount = new int[n];

            for (int i = 0; i < n; i++) {
                boolean more = true;
                while (more && scan.hasNextInt()) {
                    int j = scan.nextInt() - 1;
                    if (j != -1) {
                        linkCount[i][j]++;
                        outCount[i]++;
                    } 
                    else
                        more = false;
                }
            }

            // probability of moving from page i to page j
            double[][] probMatrix = new double[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double linkProb;
                    if (linkCount[i][j] == 0)
                        linkProb = 0.0;
                    else
                        linkProb = alpha * linkCount[i][j] / outCount[i];

                    double jumpProb = (1 - alpha) / n;

                    probMatrix[i][j] = linkProb + jumpProb;
                }
            }

            int page = rand.nextInt(n);

            double[] ranks = new double[n];
            ranks[page] = 1.0;

            for (int i = 0; i < visits; i++) {
                double[] newRanks = new double[n];
                
                for (int j = 0; j < n; j++)
                    for (int k = 0; k < n; k++)
                        newRanks[j] += ranks[k] * probMatrix[k][j];
                
                ranks = newRanks;
            }

            System.out.println(testName);
            
            for (int i = 0; i < n; i++)
                System.out.println((i + 1) + " " + String.format("%.6f", ranks[i]));

            if (scan.hasNextLine())
                scan.nextLine();
        }
        scan.close();
    }
}


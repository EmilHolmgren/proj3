package proj3;
import java.util.*;
public class OptimalPricing implements Pricer {

    private int ticketCount;
    private int customerCount;
    private float[][] pricingMatrix;
    private float[][] revMatrix;

    public OptimalPricing (int ticketCount, int customerCount) {
        this.ticketCount = ticketCount;
        this.customerCount = customerCount;
        pricingMatrix = new float[ticketCount][customerCount];
        revMatrix = new float[ticketCount][customerCount];
        fillMatrix();
        // System.out.println(Arrays.deepToString(pricingMatrix));
        // System.out.println(Arrays.deepToString(revMatrix));
    }

    private void fillMatrix() {
        for (int i = 1; i <= ticketCount; i++) {
            for (int j = 1; j <= customerCount; j++) {
                if (i >= j) {
                    pricingMatrix[i-1][j-1] = 0.5f;
                    revMatrix[i-1][j-1] = (j)/4f;
                }
                else if (i == 1) {
                    float p = (float) Math.pow(1+j, -1f/j);
                    pricingMatrix[i-1][j-1] = p;
                    float r = (float) (p*(1-Math.pow(p,j)));
                    revMatrix[i-1][j-1] = r;
                }
                else {
                    float alpha = revMatrix[i-2][j-2];
                    float beta = revMatrix[i-1][j-2];
                    // System.out.println("alpha: " + alpha);
                    // System.out.println("beta: " + beta);
                    float p = (1 + beta - alpha)/2;
                    pricingMatrix[i-1][j-1] = p;
                    float r = (float) (p*(1+beta-alpha) - Math.pow(p, 2) + alpha);
                    revMatrix[i-1][j-1] = r;
                }

            }
        }
    }

    @Override
    public float getPricing(int tickets, int customer) {
        float pricing = pricingMatrix[tickets-1][customer-1];
        return pricing;
    }

    public float getRev(int tickets, int customer) {
        float rev = revMatrix[tickets-1][customer-1];
        return rev;

    }
    
}

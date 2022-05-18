package proj3;

public class PricingStratTest {
    public static void main(String[] args) {

        int tickets = 10;
        int customers = 1000;

        BasicPricing bp = new BasicPricing(tickets, customers);
        
        OptimalPricing op = new OptimalPricing(tickets, customers);
        runExp(customers,tickets,bp);
    }

    public static void runExp(int customers, int tickets, Pricer priceStrat){
        for (int i = 0; i < 1000; i++){
            float rev = 0;
            int ticketsRemaining = tickets;
            for (int j = 1; j <= customers; j++) {
                float v = (float) Math.random();
                float price = priceStrat.getPricing(ticketsRemaining, customers-j+1);
                if (v >= price) {
                    rev += price;
                    ticketsRemaining--;
                    if (ticketsRemaining == 0) {
                        break;
                    }
                }
            }
            System.out.println(rev);
        }
    }
}

package proj3;

public class BasicPricing implements Pricer {
    
    private int ticketCount;
    private int customerCount;

    public BasicPricing (int ticketCount, int customerCount) {
        this.ticketCount = ticketCount;
        this.customerCount = customerCount;

    }

    @Override
    public float getPricing(int ticket, int customer) {
        float pricing = 1-((float)ticketCount)/customerCount;
        return pricing;
    }

    
}

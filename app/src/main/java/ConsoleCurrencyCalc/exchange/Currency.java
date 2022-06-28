package ConsoleCurrencyCalc.exchange;

import lombok.Data;

@Data
public class Currency {
    private String currPair;
    private double cost;
}

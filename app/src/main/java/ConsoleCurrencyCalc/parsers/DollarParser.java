package ConsoleCurrencyCalc.parsers;

import ConsoleCurrencyCalc.calculator.coins.Coin;
import ConsoleCurrencyCalc.calculator.coins.CoinParser;

public class DollarParser implements CoinParser {
    @Override
    public Coin parse(String token) {
        if (token.charAt(0) != '$')
            return null;
        return new Coin(Double.parseDouble(token.substring(1)), '$');
    }
}
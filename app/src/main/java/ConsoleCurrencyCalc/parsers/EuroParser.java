package ConsoleCurrencyCalc.parsers;

import ConsoleCurrencyCalc.calculator.coins.Coin;
import ConsoleCurrencyCalc.calculator.coins.CoinParser;

public class EuroParser implements CoinParser {
    @Override
    public Coin parse(String token) {
        if (token.charAt(0) != 'e')
            return null;
        return new Coin(Double.parseDouble(token.substring(1)), 'e', "EU");
    }
}

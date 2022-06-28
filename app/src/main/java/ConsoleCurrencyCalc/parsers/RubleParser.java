package ConsoleCurrencyCalc.parsers;

import ConsoleCurrencyCalc.calculator.coins.Coin;
import ConsoleCurrencyCalc.calculator.coins.CoinParser;

public class RubleParser implements CoinParser {
    @Override
    public Coin parse(String token) {
        if (token.charAt(token.length() - 1) != 'p')
            return null;
        return new Coin(Double.parseDouble(token.substring(0, token.length()-1)), 'p', "RUB");
    }
}

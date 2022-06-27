package ConsoleCurrencyCalc.calculator;

import ConsoleCurrencyCalc.calculator.coins.Coin;

@FunctionalInterface
public interface CustomFunction {
    Coin eval(Coin[] coins);
}

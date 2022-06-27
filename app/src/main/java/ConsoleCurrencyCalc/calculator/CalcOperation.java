package ConsoleCurrencyCalc.calculator;

import ConsoleCurrencyCalc.calculator.coins.Coin;

import java.util.function.BiFunction;

public interface CalcOperation extends BiFunction<Coin, Coin, Coin> {

}

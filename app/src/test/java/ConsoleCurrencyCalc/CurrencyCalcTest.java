package ConsoleCurrencyCalc;

import ConsoleCurrencyCalc.calculator.CurrencyCalc;
import ConsoleCurrencyCalc.calculator.coins.Coin;
import ConsoleCurrencyCalc.parsers.DollarParser;
import ConsoleCurrencyCalc.parsers.RubleParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyCalcTest {
    static CurrencyCalc calc;

    @BeforeAll
    public static void setup() {
        calc = CurrencyCalc.builder()
                .parser(new DollarParser())
                .parser(new RubleParser())
                .customFunction("toRuble", (args) -> {
                    return new Coin(args[0].getVal() * 10, 'p', "RUB");
                })
                .customFunction("toDollar", (args) -> {
                    return new Coin(args[0].getVal() / 10, '$', "USD");
                })
                .customFunction("min", (args) -> {
                    return args[0].compareTo(args[1]) < 0 ? args[0] : args[1];
                })
                .build();
    }

    @Test
    public void toRuble_success() {
        var actual = calc.calculate("toRuble($10)");
        assertEquals('p', actual.getSign());
        assertEquals(100.0, actual.getVal());
    }

    @Test
    public void toRuble_missedLefParenthesis() {
        assertThrows(IllegalArgumentException.class, () ->
                        calc.calculate("toRuble$10)"),
                "Missed '('");
    }

    @Test
    public void toRuble_missedRightParenthesis() {
        assertThrows(IllegalArgumentException.class, () ->
                        calc.calculate("toRuble($10"),
                "Missed ')'");
    }

    @Test
    public void parse10Dollars_success() {
        var actual = calc.calculate("$10");
        assertEquals('$', actual.getSign());
        assertEquals(10.0, actual.getVal());
    }

    @Test
    public void parse10Rubles_success() {
        var actual = calc.calculate("10p");
        assertEquals('p', actual.getSign());
        assertEquals(10.0, actual.getVal());
    }

    @Test
    public void toDollar_success() {
        var actual = calc.calculate("toDollar(75.0p)");
        assertEquals(7.5, actual.getVal());
        assertEquals('$', actual.getSign());
    }

    @Test
    public void toDollar_cyrillic_success() {
        var actual = calc.calculate("toDollar(75.0р)");
        assertEquals(7.5, actual.getVal());
        assertEquals('$', actual.getSign());
    }

    @Test
    public void sum10And11Dollars_success() {
        var actual = calc.calculate("$10.0 + $11.0");
        assertEquals(21.0, actual.getVal());
    }

    @Test
    public void sum10DollarsAnd110Rubles_differentSigns() {
        assertThrows(IllegalArgumentException.class, () -> calc.calculate("$10.0 + 110.0p"),
                "Cannot add two currency with different signs.");
    }

    @Test
    public void sum10DollarsAnd110Rubles_success() {
        var actual = calc.calculate("$10.0 + toDollar(110.0p)");
        assertEquals(21.0, actual.getVal());
    }

    @Test
    public void sum10DollarsAnd100Rubles_success() {
        var actual = calc.calculate("$10 + toDollar(100.0p)");
        assertEquals(20.0, actual.getVal());
    }

    @Test
    public void min_success() {
        var actual = calc.calculate("min($10.3, toDollar(700.0p))");
        assertEquals(10.3, actual.getVal());
    }

}

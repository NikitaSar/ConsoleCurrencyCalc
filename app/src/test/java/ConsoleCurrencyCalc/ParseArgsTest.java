package ConsoleCurrencyCalc;

import ConsoleCurrencyCalc.calculator.CurrencyCalc;
import ConsoleCurrencyCalc.calculator.coins.Coin;
import ConsoleCurrencyCalc.parsers.DollarParser;
import ConsoleCurrencyCalc.parsers.RubleParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class ParseArgsTest {

    static Method method;
    static CurrencyCalc calc;

    @BeforeAll
    static void setup() throws NoSuchMethodException {
        method = CurrencyCalc.class.getDeclaredMethod("parseArgs", String.class);
        method.setAccessible(true);
        calc = CurrencyCalc.builder()
                .parser(new DollarParser())
                .parser(new RubleParser())
                .build();
    }

    Coin[] invoke(String argsExpr) throws InvocationTargetException, IllegalAccessException {
        return  (Coin[]) method.invoke(calc, argsExpr);
    }

    @Test
    public void dollar_success() throws Exception {
        var coins = invoke("$10, $1");
        assertEquals(2, coins.length);
        assertEquals(10, coins[0].getVal());
        assertEquals(1, coins[1].getVal());
    }

    @Test
    public void ruble_success() throws Exception {
        var coins = invoke("10p, 20p, 1p");
        assertEquals(3, coins.length);
        assertEquals(10, coins[0].getVal());
        assertEquals(1, coins[2].getVal());
    }

}

# ConsoleCurrencyCalc
Currency calculator console app.

Futures:
```
toRuble($10.5)
toDollar(700.20p)
$10.1 + $11
$5.5 + toDollar(200p + toRuble($1.5))
toEuro(700p)
min($20.5, $11)
min(toDollar(700p), $23, $11.7)
```

Install:

```shell
gradle build
java -jar app/build/libs/app.jar 
```
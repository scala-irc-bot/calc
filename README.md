calc
========
calc is an automatic arithmetic calculation on [scala-irc-bot](https://github.com/scala-irc-bot/scala-irc-bot).

# Installation
1. git clone
2. sbt package
3. copy `calc/target/scala-2.9.2/calc_2.9.2-0.0.1-SNAPSHOT.jar` to `scala-irc-bot/bots`.
4. modify `scala-irc-bot/config/Config.scala` like:

```scala
val bots = Array[(String, Option[BotConfig])](
  ("net.mtgto.irc.bot.calc.CalcBot", None)
)
```

# Order of operations
It looks like ruby.

1. `()` (parentheses)
2. `+` `-` (unary operation)
3. `**` (exponent, right operand must be non-negative integer)
4. `*` `/` `%` (multiplication, division, modulo)
5. `+` `-` (addition and subtraction)

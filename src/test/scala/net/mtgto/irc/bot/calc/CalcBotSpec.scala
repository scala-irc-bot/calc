package net.mtgto.irc.bot.calc

import net.mtgto.irc.{Bot, Client}
import org.specs2.mutable._

class CalcBotSpec extends Specification {
  "CalcBot" should {
    val calcBot = new CalcBot

    "not evaluate invalid formulas" in {
      calcBot.eval("hoge") must beNone
      calcBot.eval("hoge + fuga") must beNone
      calcBot.eval("2 * (3 + 7))") must beNone
    }

    "not evaluate single number" in {
      calcBot.eval("2") must beNone
      calcBot.eval("-123") must beNone
      calcBot.eval("45.67") must beNone
    }

    "evaluate valid formulas" in {
      calcBot.eval("1 + 2 * 3 / 6") must beSome(2)
      calcBot.eval("2 * (3 + 7)") must beSome(20)
      calcBot.eval("-3 * (((((-3)))))") must beSome(9)
      calcBot.eval("2.5 * 3.7") must beSome(9.25)
      calcBot.eval("1 - + 3") must beSome(-2)
      calcBot.eval("1 - - 3") must beSome(4)
    }
  }
}

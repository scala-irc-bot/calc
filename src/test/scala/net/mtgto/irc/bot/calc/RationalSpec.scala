package net.mtgto.irc.bot.calc

import org.specs2.mutable._

class RationalSpec extends Specification {
  "Ratinal" should {
    "have a valid denominator" in {
      Rational(1234).denom === 1
      Rational(1234, -1).denom === 1
      Rational(BigDecimal("0.01")) === Rational(1, 100)
    }

    "have toString" in {
      "integer" in {
        Rational(123).toString === "123"
        Rational(-100).toString === "-100"
      }

      "decimal" in {
        Rational(1, 2).toString === "1/2"
      }
    }

    "add other rational" in {
      Rational(1) + Rational(2) === Rational(3)
      Rational(1, 3) + Rational(1, 2) === Rational(5, 6)
      Rational(1, 6) + Rational(1, 3) === Rational(1, 2)
    }

    "subtract other rational" in {
      Rational(7) - Rational(2) === Rational(5)
      Rational(1, 2) - Rational(1, 3) === Rational(1, 6)
      Rational(5, 6) - Rational(1, 3) === Rational(1, 2)
    }

    "multiply other rational" in {
      Rational(7) * Rational(2) === Rational(14)
      Rational(1, 2) * Rational(1, 3) === Rational(1, 6)
      Rational(1, 2) * Rational(2, 3) === Rational(1, 3)
    }

    "divide other rational" in {
      Rational(7) * Rational(2) === Rational(14)
      Rational(1, 2) * Rational(1, 3) === Rational(1, 6)
      Rational(1, 2) * Rational(2, 3) === Rational(1, 3)
    }

    "modulo other rational" in {
      Rational(7) % Rational(3) === Rational(1)
      Rational(8) % Rational(3) === Rational(2)
      Rational(1, 3) % Rational(1, 4) === Rational(1, 4)
      Rational(1, 4) % Rational(1, 3) === Rational(1, 12)
    }
  }
}

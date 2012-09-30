package net.mtgto.irc.bot.calc

class Rational(
  n: BigInt,
  d: BigInt
) {
  require(d != 0)
  private val gcd = n.gcd(d)
  val numer = n / gcd * d.signum
  val denom = d / gcd * d.signum

  override def toString = {
    if (denom == 1) {
      numer.toString
    } else {
      numer + "/" + denom
    }
  }

  override def equals(o: Any): Boolean = {
    o match {
      case r: Rational =>
        numer == r.numer && denom == r.denom
      case _ =>
        false
    }
  }

  def +(that: Rational): Rational = {
    Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }

  def -(that: Rational): Rational = {
    Rational(numer * that.denom - that.numer * denom, denom * that.denom)
  }

  def *(that: Rational): Rational = {
    Rational(numer * that.numer, denom * that.denom)
  }

  def /(that: Rational): Rational = {
    require(that.numer != 0)
    Rational(numer * that.denom, denom * that.numer)
  }

  def `%`(that: Rational): Rational = {
    require(that.numer != 0)
    val gcd = denom.gcd(that.denom)
    Rational(numer * that.denom / gcd % (that.numer * denom / gcd), denom * that.denom / gcd)
  }

  def toDouble: Double = {
    numer.toDouble / denom.toDouble
  }
}

object Rational {
  def apply(n: Int): Rational = {
    new Rational(n, 1)
  }

  def apply(n: BigInt): Rational = {
    new Rational(n, 1)
  }

  def apply(n: BigDecimal): Rational = {
    val scale = n.scale
    val denom = BigInt(10).pow(scale)
    new Rational((n * BigDecimal(denom)).toBigInt, denom)
  }

  def apply(n: BigInt, d: BigInt): Rational = {
    new Rational(n, d)
  }
}

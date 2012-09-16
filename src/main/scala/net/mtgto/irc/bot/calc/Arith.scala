package net.mtgto.irc.bot.calc

import util.parsing.combinator._

protected object Arith extends JavaTokenParsers {
  sealed trait Exp
  case class Add(exp1: Exp, exp2: Exp) extends Exp
  case class Subtract(exp1: Exp, exp2: Exp) extends Exp
  case class Multiply(exp1: Exp, exp2: Exp) extends Exp
  case class Divide(exp1: Exp, exp2: Exp) extends Exp
  case class Value(value: BigDecimal) extends Exp

  private def expr: Parser[Exp] = term ~ rep(("+" | "-") ~ term) ^^ {
    case term ~ rest => {
      rest.foldLeft(term)(
        (left, right) => right match {
          case "+" ~ term => Add(left, term)
          case "-" ~ term => Subtract(left, term)
        }
      )
    }
  }

  private def term: Parser[Exp] = factor ~ rep(("*" | "/") ~ factor) ^^ {
    case factor ~ rest => {
      rest.foldLeft(factor)(
        (left, right) => right match {
          case "*" ~ term => Multiply(left, term)
          case "/" ~ term => Divide(left, term)
        }
      )
    }
  }

  private def factor: Parser[Exp] = floatingPointNumber ^^ {
    case value => Value(BigDecimal(value))
  } | "(" ~> expr <~ ")" ^^ {
    case expr => expr
  }

  def parse(input: String) = {
    parseAll(expr, input)
  }

  def eval(expr: Exp): Double = {
    expr match {
      case Value(v) => v.toDouble
      case Add(a,b) => eval(a) + eval(b)
      case Subtract(a,b) => eval(a) - eval(b)
      case Multiply(a,b) => eval(a) * eval(b)
      case Divide(a,b) => eval(a) / eval(b)
    }
  }
}

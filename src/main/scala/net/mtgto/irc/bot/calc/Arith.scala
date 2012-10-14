package net.mtgto.irc.bot.calc

import util.parsing.combinator._

protected object Arith extends JavaTokenParsers {
  sealed trait Exp
  case class Add(exp1: Exp, exp2: Exp) extends Exp
  case class Subtract(exp1: Exp, exp2: Exp) extends Exp
  case class Multiply(exp1: Exp, exp2: Exp) extends Exp
  case class Divide(exp1: Exp, exp2: Exp) extends Exp
  case class Modulo(exp1: Exp, exp2: Exp) extends Exp
  case class Pow(exp1: Exp, exp2: Exp) extends Exp
  case class Value(value: Rational) extends Exp

  private def expr1: Parser[Exp] = expr2 ~ rep(("+" | "-") ~ expr2) ^^ {
    case expr2 ~ rest => {
      rest.foldLeft(expr2)(
        (left, right) => right match {
          case "+" ~ expr2 => Add(left, expr2)
          case "-" ~ expr2 => Subtract(left, expr2)
        }
      )
    }
  }

  private def expr2: Parser[Exp] = expr3 ~ rep(("*" | "/" | "%") ~ expr3) ^^ {
    case expr3 ~ rest => {
      rest.foldLeft(expr3)(
        (left, right) => right match {
          case "*" ~ expr2 => Multiply(left, expr2)
          case "/" ~ expr2 => Divide(left, expr2)
          case "%" ~ expr2 => Modulo(left, expr2)
        }
      )
    }
  }

  private def expr3: Parser[Exp] = rep(expr4 <~ "**") ~ expr4 ^^ {
    case rest ~ expr4 => {
      rest.foldRight(expr4)(
        (left, right) => Pow(left, right)
      )
    }
  }

  private def expr4: Parser[Exp] = floatingPointNumber ^^ {
    case value => Value(Rational(BigDecimal(value)))
  } | "(" ~> expr1 <~ ")" ^^ {
    case expr => expr
  } | "+" ~> floatingPointNumber ^^ {
    case value => Value(Rational(BigDecimal(value)))
  } | "-" ~> floatingPointNumber ^^ {
    case value => Value(Rational(-BigDecimal(value)))
  }

  def parse(input: String) = {
    parseAll(expr1, input)
  }

  def eval(expr: Exp): Rational = {
    expr match {
      case Value(v) => v
      case Add(a,b) => eval(a) + eval(b)
      case Subtract(a,b) => eval(a) - eval(b)
      case Multiply(a,b) => eval(a) * eval(b)
      case Divide(a,b) => eval(a) / eval(b)
      case Modulo(a,b) => eval(a) % eval(b)
      case Pow(a,b) => eval(a) ** eval(b)
    }
  }
}

package net.mtgto.irc.bot.calc

import net.mtgto.irc.{Bot, Client}
import net.mtgto.irc.event.Message

class CalcBot extends Bot {
  private[this] def parse(input: String) = {
    Arith.parse(input)
  }

  private[this] def eval(input: String) = {
    val result = Arith.parse(input)
    if (result.successful) {
      result.get match {
        case Arith.Value(_) =>
          // if input is only value, do nothing.
          None
        case expr => Some(Arith.eval(expr))
      }
    } else {
      None
    }
  }

  override def onMessage(message: Message): Unit = {
    eval(message.text) match {
      case Some(value) => Client.sendNotice(message.channel, value.toString)
      case _ => ()
    }
  }
}

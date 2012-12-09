package net.mtgto.irc.bot.calc

import net.mtgto.irc.{Bot, Client}
import net.mtgto.irc.event.Message

class CalcBot extends Bot {
  private[calc] def parse(input: String) = {
    Arith.parse(input)
  }

  private[calc] def eval(input: String): Option[Double] = {
    val result = Arith.parse(input)
    if (result.successful) {
      result.get match {
        case Arith.Value(_) =>
          // if input is only value, do nothing.
          None
        case expr => Some(Arith.eval(expr).toDouble)
      }
    } else {
      None
    }
  }

  override def onMessage(client: Client, message: Message): Unit = {
    eval(message.text) match {
      case Some(value) => client.sendNotice(message.channel, value.toString)
      case _ => ()
    }
  }
}

package myfirst

import java.security.InvalidParameterException

/**
  * Created by markkewley on 2/25/17.
  */
class Date(y: Int, m: Int, d: Int) extends Ord{
  def year = y
  def month = m
  def day = d

  override def <(that: Any): Boolean = {
    if (!that.isInstanceOf[Date]) {
      throw new InvalidParameterException("Can't compare " + that + " and a Date")
    }

    val o = that.asInstanceOf[Date]
    (year < o.year) ||
      (year == o.year && (month < o.month) ||
        (month == o.month && (day < o.day)))
  }

  override def equals(that: Any): Boolean = {
    // uses Java's instanceof operator here
    that.isInstanceOf[Date] && {
      // this is similar to Java's cast operator
      val o = that.asInstanceOf[Date]
      o.day == day && o.month == month && o.year == year
    }
  }
}

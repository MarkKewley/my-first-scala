package myfirst

/**
  * Created by markkewley on 2/25/17.
  */
class ComplexNumber(real: Double, imaginary: Double) {
  def getReal() = real
  def getImaginary() = imaginary

  // refer to methods without parenthesis
  def re = real
  def im = imaginary

  override def toString() = "" + re + (if (im < 0) "-" else "+") + im + "i"
}

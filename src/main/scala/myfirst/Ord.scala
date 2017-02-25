package myfirst

/**
  * Similar to the comparable interface in java. Traits are simply
  * interfaces that can contain code (which with java 8 this is also possible).
  *
  * With Ord we can build off of the predicates here, the only implementation required would
  * be the '<'. After that everything will build upon it (well we also should implement ==).
  *
  * Any is the super type of all other types in Scala.
  */
trait Ord {
  def < (that: Any): Boolean
  def <= (that: Any): Boolean = (this < that) || (this == that)
  def > (that: Any): Boolean = !(this <= that)
  def >= (that: Any): Boolean = !(this < that)
}

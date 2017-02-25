package myfirst

/**
  * Example of using Generics and classes, similar to Java
  */
class Reference[T] {
  // _ represents default value
  private var contents: T = _
  def set(value: T) { contents = value }
  def get: T = contents
}

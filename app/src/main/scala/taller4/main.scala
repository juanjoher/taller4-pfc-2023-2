package taller4

object main {
  def main(args: Array[String]): Unit = {
    type Matriz = Vector[Vector[Int]]
    val obj = new Taller4()
    val matriz1: Matriz = Vector(
      Vector(1, 2, 3, 4),
      Vector(5, 6, 7, 8),
      Vector(9, 10, 11, 12),
      Vector(13, 14, 15, 16)
    )
    println(obj.subMatriz(matriz1, 2,1,2))
  }
}

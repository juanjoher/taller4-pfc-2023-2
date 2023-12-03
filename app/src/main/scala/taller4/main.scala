package taller4

object main {
  def main(args: Array[String]): Unit = {
    type Matriz = Vector[Vector[Int]]
    val matriz1: Matriz = Vector(
      Vector(29, 4, 10, 26, 19, 3, 9, 16), Vector(22, 6, 16, 4, 23, 16, 17, 18), Vector(29, 24, 1, 5, 6, 29, 13, 6), Vector(4, 13, 26, 2, 15, 26, 1, 8), Vector(8, 27, 18, 18, 21, 13, 28, 27), Vector(4, 8, 28, 14, 6, 16, 11, 22), Vector(9, 21, 27, 17, 18, 17, 0, 0), Vector(18, 28, 22, 9, 6, 18, 9, 28)
    )
    val matriz2: Matriz = Vector(
      Vector(26, 39, 39, 22, 35, 3, 26, 13), Vector(29, 20, 3, 6, 14, 4, 11, 39), Vector(13, 25, 39, 36, 1, 8, 27, 35), Vector(23, 0, 20, 12, 2, 37, 19, 27), Vector(13, 23, 30, 27, 28, 30, 24, 9), Vector(1, 5, 25, 26, 10, 0, 16, 2), Vector(23, 11, 4, 26, 9, 32, 34, 29), Vector(30, 28, 25, 23, 9, 15, 25, 35)

    )
  val obj = new Taller4()
  /*println(obj.matrizAlAzar(8,40))*/
    println(obj.restaMatriz(matriz1,matriz2))
  }
}

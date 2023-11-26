/**
  * Taller 3 - Programación Funcional
  * Autores: Juan José Hernandez Arenas - 2259500
  * Profesor: Carlos A Delgado
  */
package taller4

import org.scalameter.measure
import org.scalameter.withWarmer
import org.scalameter.Warmer

import scala.util.Random

object Taller4{
  type Matriz = Vector[Vector[Int]]

  def matrizAlAzar(long: Int, vals: Int): Matriz = {
    // Crea una matriz de enteros cuadrada de long x long,
    // con valores aleatorios entre 0 y vals
    val random = new Random()
    val v = Vector.fill(long, long)(random.nextInt(vals))
    v
  }

  def vectorAlAzar(long: Int, vals: Int): Vector[Int] = {
    //Crea un vector de enteros de longitud long ,
    // con valores aleatorios entre 0 y vals
    val random = new Random()
    val v = Vector.fill(long) {
      random.nextInt(vals)
    }
    v
  }

  def prodPunto(v1: Vector[Int], v2: Vector[Int]): Int = {
    // Producto punto de dos vectores
    (v1 zip v2).map { case (i, j) => i * j }.sum
  }

  def transpuesta(m: Matriz): Matriz = {
    val l = m.length
    Vector.tabulate(l, l)((i, j) => m(j)(i))
  }

  def multMatriz(m1: Matriz, m2: Matriz): Matriz = {
    require(m1.nonEmpty && m1.size == m1.head.size && m1.size == m2.size && m2.size == m2.head.size, "Las matrices deben ser cuadradas y tener la misma dimensión")

    val n = m1.head.size

    Vector.tabulate(n, n) { (i, j) =>
      val fila = m1(i)
      val columna = transpuesta(m2)(j)
      prodPunto(fila, columna)
    }
  }


  def subMatriz(m: Matriz, i: Int, j: Int, l: Int): Matriz = {
    // Dada m, matriz cuadrada de NxN, 1 <= i, j <= N, i + n <= N, j + n <= N,
    // devuelve la submatriz de nxn correspondiente a m[i .. i + (n-1), j .. j + (n-1)]

    Vector.tabulate(l, l) { (row, col) =>
      m(i + row)(j + col)
    }
  }

  def sumMatriz(m1: Matriz, m2: Matriz): Matriz = {
    // Recibe m1 y m2 matrices cuadradas de la misma dimensión (potencia de 2)
    // y devuelve la matriz resultante de la suma de las dos matrices

    require(m1.length == m2.length && m1.headOption.getOrElse(Vector()).length == m2.headOption.getOrElse(Vector()).length, "Las matrices deben tener la misma dimensión")

    Vector.tabulate(m1.length, m1.headOption.getOrElse(Vector()).length) { (i, j) =>
      m1(i)(j) + m2(i)(j)
    }
  }

  def multMatrizRec(m1: Matriz, m2: Matriz): Matriz = {
    require(m1.nonEmpty && m1.size == m1.head.size && m1.size == m2.size && m2.size == m2.head.size, "Las matrices deben ser cuadradas y tener la misma dimensión")

    val n = m1.head.size

    if (n == 1) {
      Vector(Vector(m1(0)(0) * m2(0)(0)))
    } else {
      val m = n / 2

      val a11 = subMatriz(m1, 0, 0, m)
      val a12 = subMatriz(m1, 0, m, m)
      val a21 = subMatriz(m1, m, 0, m)
      val a22 = subMatriz(m1, m, m, m)

      val b11 = subMatriz(m2, 0, 0, m)
      val b12 = subMatriz(m2, 0, m, m)
      val b21 = subMatriz(m2, m, 0, m)
      val b22 = subMatriz(m2, m, m, m)

      val c11 = sumMatriz(multMatrizRec(a11, b11), multMatrizRec(a12, b21))
      val c12 = sumMatriz(multMatrizRec(a11, b12), multMatrizRec(a12, b22))
      val c21 = sumMatriz(multMatrizRec(a21, b11), multMatrizRec(a22, b21))
      val c22 = sumMatriz(multMatrizRec(a21, b12), multMatrizRec(a22, b22))

      // Construir la matriz resultante
      Vector.tabulate(n, n) { (i, j) =>
        if (i < m && j < m) c11(i)(j)
        else if (i < m && j >= m) c12(i)(j - m)
        else if (i >= m && j < m) c21(i - m)(j)
        else c22(i - m)(j - m)
      }
    }
  }

  def restaMatriz(m1: Matriz, m2: Matriz): Matriz = {
    // Recibe m1 y m2 matrices cuadradas de la misma dimensión (potencia de 2)
    // y devuelve la matriz resultante de la resta de las dos matrices

    require(m1.length == m2.length && m1.headOption.getOrElse(Vector()).length == m2.headOption.getOrElse(Vector()).length, "Las matrices deben tener la misma dimensión")

    Vector.tabulate(m1.length, m1.headOption.getOrElse(Vector()).length) { (i, j) =>
      m1(i)(j) - m2(i)(j)
    }
  }


  def multStrassen(m1: Matriz, m2: Matriz): Matriz = {
    // Recibe m1 y m2 matrices cuadradas de la misma dimensión (potencia de 2)
    // y devuelve la multiplicación de las dos matrices usando el algoritmo de Strassen

    require(m1.nonEmpty && m1.size == m1.head.size && m1.size == m2.size && m2.size == m2.head.size, "Las matrices deben ser cuadradas y tener la misma dimensión")

    val n = m1.head.size

    if (n == 1) {
      Vector(Vector(m1(0)(0) * m2(0)(0)))
    } else {
      val m = n / 2

      val a11 = subMatriz(m1, 0, 0, m)
      val a12 = subMatriz(m1, 0, m, m)
      val a21 = subMatriz(m1, m, 0, m)
      val a22 = subMatriz(m1, m, m, m)

      val b11 = subMatriz(m2, 0, 0, m)
      val b12 = subMatriz(m2, 0, m, m)
      val b21 = subMatriz(m2, m, 0, m)
      val b22 = subMatriz(m2, m, m, m)

      val p1 = multStrassen(sumMatriz(a11, a22), sumMatriz(b11, b22))
      val p2 = multStrassen(sumMatriz(a21, a22), b11)
      val p3 = multStrassen(a11, restaMatriz(b12, b22))
      val p4 = multStrassen(a22, restaMatriz(b21, b11))
      val p5 = multStrassen(sumMatriz(a11, a12), b22)
      val p6 = multStrassen(restaMatriz(a21, a11), sumMatriz(b11, b12))
      val p7 = multStrassen(restaMatriz(a12, a22), sumMatriz(b21, b22))

      val c11 = restaMatriz(sumMatriz(sumMatriz(p1, p4), p7), p5)
      val c12 = sumMatriz(p3, p5)
      val c21 = sumMatriz(p2, p4)
      val c22 = restaMatriz(sumMatriz(sumMatriz(p1, p3), p6), p2)

      // Construir la matriz resultante
      Vector.tabulate(n, n) { (i, j) =>
        if (i < m && j < m) c11(i)(j)
        else if (i < m && j >= m) c12(i)(j - m)
        else if (i >= m && j < m) c21(i - m)(j)
        else c22(i - m)(j - m)
      }
    }
  }

  def saludo() = "Taller 4 2023-II"

  def main(args: Array[String]): Unit = {
    println(saludo())
    println(
      withWarmer(new Warmer.Default) measure {
        (1 to 100000000).toArray
      }
    )
  }
 }

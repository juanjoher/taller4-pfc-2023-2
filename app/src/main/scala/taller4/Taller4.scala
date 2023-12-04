/**
  * Taller 4 - Programación Concurrente
  * Autores: Carlos Alberto Camacho Castaño - 2160331
 *           Juan José Hernandez Arenas - 2259500
 *           Santiago Reyes Rodriguez - 2259738
 *           Carlos Alberto Camacho Castaño -2160331
  * Profesor: Carlos A Delgado
  */
package taller4

import common.parallel
import common._
import org.scalameter.measure
import org.scalameter.withWarmer
import org.scalameter.Warmer
import org.scalameter.picklers.noPickler._

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.util.Random

class Taller4 {
  type Matriz = Vector[Vector[Int]]
  type ParVector[A] = Vector[A]

  //FUNCIONES AUXILIARES

  //Funciones que generan matrices y vectores aleatorios
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

  //Funcion que calcula la submatriz de una matriz
  def subMatriz(m: Matriz, i: Int, j: Int, l: Int): Matriz = {
    // Dada m, matriz cuadrada de NxN, 1 <= i, j <= N, i + n <= N, j + n <= N,
    // devuelve la submatriz de nxn correspondiente a m[i .. i + (n-1), j .. j + (n-1)]

    Vector.tabulate(l, l) { (row, col) =>
      m(i + row)(j + col)
    }
  }

  //Funciones que calcula la suma y resta de dos matrices
  def sumMatriz(m1: Matriz, m2: Matriz): Matriz = {
    // Recibe m1 y m2 matrices cuadradas de la misma dimensión (potencia de 2)
    // y devuelve la matriz resultante de la suma de las dos matrices

    Vector.tabulate(m1.length, m1.headOption.getOrElse(Vector()).length) { (i, j) =>
      m1(i)(j) + m2(i)(j)
    }
  }

  def restaMatriz(m1: Matriz, m2: Matriz): Matriz = {
    // Recibe m1 y m2 matrices cuadradas de la misma dimensión (potencia de 2)
    // y devuelve la matriz resultante de la resta de las dos matrices


    Vector.tabulate(m1.length, m1.headOption.getOrElse(Vector()).length) { (i, j) =>
      m1(i)(j) - m2(i)(j)
    }
  }



  //EJERCICIOS DE PROGRAMACION

  //Funciones que calculan el producto punto de dos vectores
  def prodPunto(v1: Vector[Int], v2: Vector[Int]): Int = {
    // Producto punto de dos vectores
    (v1 zip v2).map { case (i, j) => i * j }.sum
  }

  //Funciones que calculan el producto punto de dos vectores paralelamente
  def prodPuntoParD(v1: ParVector[Int], v2: ParVector[Int]): Int = {
    (v1 zip v2).map({ case (i, j) => i * j }).sum
  }

  //Funcion que calcula la transpuesta de una matriz
  def transpuesta(m: Matriz): Matriz = {
    val l = m.length
    Vector.tabulate(l, l)((i, j) => m(j)(i))
  }


  //Funcion que calcula la multiplicacion de dos matrices normal, recursivo, con algoritmo de Strassen y paralelo
  def multMatriz(m1: Matriz, m2: Matriz): Matriz = {
    //Matrices de entrada (AB) que usa las dos funciones definidas anteriormente( transpuesta y prodPunto) para calcular la multiplicacion de dos matrices
    val m2t = transpuesta(m2)
    Vector.tabulate(m1.length, m2t.length) { (i, j) =>
      prodPunto(m1(i), m2t(j))
    }
  }

  def multMatrizPar(m1: Matriz, m2: Matriz): Matriz = {
    val m2t = transpuesta(m2)
    val (m1a, m1b) = m1.splitAt(m1.length / 2)

    val top = task(m1a.map(row => m2t.map(col => prodPunto(row, col))))
    val bot = task(m1b.map(row => m2t.map(col => prodPunto(row, col))))

    top.join() ++ bot.join()
  }


  //Funciones que calculan la multiplicacion de dos matrices recursivamente y recursivamente paralelo
  def multMatrizRec(m1: Matriz, m2: Matriz): Matriz = {

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

  def multMatrizRecPar(m1: Matriz, m2: Matriz): Matriz = {
    val n = m1.length
    val umbral = 64
    if (n <= umbral) {
      multMatrizRec(m1, m2)
    } else {
      val a = subMatriz(m1, 0, 0, n / 2)
      val b = subMatriz(m1, 0, n / 2, n / 2)
      val c = subMatriz(m1, n / 2, 0, n / 2)
      val d = subMatriz(m1, n / 2, n / 2, n / 2)

      val e = subMatriz(m2, 0, 0, n / 2)
      val f = subMatriz(m2, 0, n / 2, n / 2)
      val g = subMatriz(m2, n / 2, 0, n / 2)
      val h = subMatriz(m2, n / 2, n / 2, n / 2)

      val (p1, p2) = parallel(multMatrizRecPar(a, sumMatriz(f, h)), multMatrizRecPar(sumMatriz(a, b), h))
      val (p3, p4) = parallel(multMatrizRecPar(sumMatriz(c, d), e), multMatrizRecPar(d, sumMatriz(g, e)))
      val (p5, p6) = parallel(multMatrizRecPar(sumMatriz(a, d), sumMatriz(e, h)), multMatrizRecPar(sumMatriz(b, d), sumMatriz(g, h)))
      val p7 = multMatrizRecPar(sumMatriz(a, c), sumMatriz(e, f))

      val A = sumMatriz(sumMatriz(p5, p4), sumMatriz(p6, p2))
      val B = sumMatriz(p1, p2)
      val C = sumMatriz(p3, p4)
      val D = sumMatriz(sumMatriz(p1, p5), sumMatriz(p3, p7))

      A ++ B ++ C ++ D
    }
  }


  //Funciones que calculan la multiplicacion de dos matrices con el algoritmo de Strassen y Strassen paralelo
  def multStrassen(m1: Matriz, m2: Matriz): Matriz = {
    // Recibe m1 y m2 matrices cuadradas de la misma dimensión (potencia de 2)
    // y devuelve la multiplicación de las dos matrices usando el algoritmo de Strassen


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

  def multStrassenPar(m1: Matriz, m2: Matriz): Matriz = {
    val n = m1.length
    val umbral = 64
    if (n <= umbral) {
      multStrassen(m1, m2)
    } else {
      val m = n / 2

      val a = subMatriz(m1, 0, 0, m)
      val b = subMatriz(m1, 0, m, m)
      val c = subMatriz(m1, m, 0, m)
      val d = subMatriz(m1, m, m, m)

      val e = subMatriz(m2, 0, 0, n / 2)
      val f = subMatriz(m2, 0, n / 2, n / 2)
      val g = subMatriz(m2, n / 2, 0, n / 2)
      val h = subMatriz(m2, n / 2, n / 2, n / 2)

      val (p1, p2) = parallel(multStrassenPar(a, sumMatriz(f, h)), multStrassenPar(sumMatriz(a, b), h))
      val (p3, p4) = parallel(multStrassenPar(sumMatriz(c, d), e), multStrassenPar(d, sumMatriz(g, e)))
      val (p5, p6) = parallel(multStrassenPar(sumMatriz(a, d), sumMatriz(e, h)), multStrassenPar(sumMatriz(b, d), sumMatriz(g, h)))
      val p7 = multStrassenPar(sumMatriz(a, c), sumMatriz(e, f))

      val A = sumMatriz(sumMatriz(p5, p4), sumMatriz(p6, p2))
      val B = sumMatriz(p1, p2)
      val C = sumMatriz(p3, p4)
      val D = sumMatriz(sumMatriz(p1, p5), sumMatriz(p3, p7))

      A ++ B ++ C ++ D
    }
  }

  object Benchmark {
    type Matriz = Vector[Vector[Int]]

    def medirTiempo(funcion: => Unit): Double = {
      val inicio = System.nanoTime()
      funcion
      val fin = System.nanoTime()
      (fin - inicio) / 1e6 // Tiempo en milisegundos
    }

    def compararAlgoritmos(algoritmo1: (Matriz, Matriz) => Matriz, algoritmo2: (Matriz, Matriz) => Matriz)(m1: Matriz, m2: Matriz): (Double, Double, Double) = {
      val tiempoAlgoritmo1 = medirTiempo(algoritmo1(m1, m2))
      val tiempoAlgoritmo2 = medirTiempo(algoritmo2(m1, m2))
      val relacionRendimiento = tiempoAlgoritmo1 / tiempoAlgoritmo2
      (tiempoAlgoritmo1, tiempoAlgoritmo2, relacionRendimiento)
    }

    def compararAlgoritmos2(Funcion1: (Matriz, Matriz) => Matriz, Funcion2: (Matriz, Matriz) => Matriz)(m1: Matriz, m2: Matriz): (Double, Double, Double) = {
      val timeF1 = withWarmer(new Warmer.Default) measure {
        Funcion1(m1, m2)
      }
      val timeF2 = withWarmer(new Warmer.Default) measure {
        Funcion2(m1, m2)
      }

      val promedio = timeF1.value / timeF2.value
      (timeF1.value, timeF2.value, promedio)

    }


  }

}


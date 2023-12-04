/**
 * Plantilla para pruebas
* @author Carlos Delgado
* @version 1.0
* @note 22 de Noviembre de 2023 
 */
package taller4

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestTaller4 extends AnyFunSuite{
    type Matriz = Vector[Vector[Int]]

    test("testSuma"){
        val obj = new Taller4()
        val matriz1: Matriz = Vector(
            Vector(29, 4, 10, 26, 19, 3, 9, 16), Vector(22, 6, 16, 4, 23, 16, 17, 18), Vector(29, 24, 1, 5, 6, 29, 13, 6), Vector(4, 13, 26, 2, 15, 26, 1, 8), Vector(8, 27, 18, 18, 21, 13, 28, 27), Vector(4, 8, 28, 14, 6, 16, 11, 22), Vector(9, 21, 27, 17, 18, 17, 0, 0), Vector(18, 28, 22, 9, 6, 18, 9, 28)
        )
        val matriz2: Matriz = Vector(
            Vector(26, 39, 39, 22, 35, 3, 26, 13), Vector(29, 20, 3, 6, 14, 4, 11, 39), Vector(13, 25, 39, 36, 1, 8, 27, 35), Vector(23, 0, 20, 12, 2, 37, 19, 27), Vector(13, 23, 30, 27, 28, 30, 24, 9), Vector(1, 5, 25, 26, 10, 0, 16, 2), Vector(23, 11, 4, 26, 9, 32, 34, 29), Vector(30, 28, 25, 23, 9, 15, 25, 35)

        )
        assert(Vector(Vector(55, 43, 49, 48, 54, 6, 35, 29), Vector(51, 26, 19, 10, 37, 20, 28, 57), Vector(42, 49, 40, 41, 7, 37, 40, 41), Vector(27, 13, 46, 14, 17, 63, 20, 35), Vector(21, 50, 48, 45, 49, 43, 52, 36), Vector(5, 13, 53, 40, 16, 16, 27, 24), Vector(32, 32, 31, 43, 27, 49, 34, 29), Vector(48, 56, 47, 32, 15, 33, 34, 63)) == obj.sumMatriz(matriz1,matriz2))
    }
    test("testResta") {
        val obj = new Taller4()
        val matriz1: Matriz = Vector(
            Vector(29, 4, 10, 26, 19, 3, 9, 16), Vector(22, 6, 16, 4, 23, 16, 17, 18), Vector(29, 24, 1, 5, 6, 29, 13, 6), Vector(4, 13, 26, 2, 15, 26, 1, 8), Vector(8, 27, 18, 18, 21, 13, 28, 27), Vector(4, 8, 28, 14, 6, 16, 11, 22), Vector(9, 21, 27, 17, 18, 17, 0, 0), Vector(18, 28, 22, 9, 6, 18, 9, 28)
        )
        val matriz2: Matriz = Vector(
            Vector(26, 39, 39, 22, 35, 3, 26, 13), Vector(29, 20, 3, 6, 14, 4, 11, 39), Vector(13, 25, 39, 36, 1, 8, 27, 35), Vector(23, 0, 20, 12, 2, 37, 19, 27), Vector(13, 23, 30, 27, 28, 30, 24, 9), Vector(1, 5, 25, 26, 10, 0, 16, 2), Vector(23, 11, 4, 26, 9, 32, 34, 29), Vector(30, 28, 25, 23, 9, 15, 25, 35)

        )
        assert(Vector(Vector(3, -35, -29, 4, -16, 0, -17, 3), Vector(-7, -14, 13, -2, 9, 12, 6, -21), Vector(16, -1, -38, -31, 5, 21, -14, -29), Vector(-19, 13, 6, -10, 13, -11, -18, -19), Vector(-5, 4, -12, -9, -7, -17, 4, 18), Vector(3, 3, 3, -12, -4, 16, -5, 20), Vector(-14, 10, 23, -9, 9, -15, -34, -29), Vector(-12, 0, -3, -14, -3, 3, -16, -7))
          == obj.restaMatriz(matriz1, matriz2))
    }
    test("testMultMatriz") {
        val obj = new Taller4()
        //matrices de 3x3
        val matriz1: Matriz = Vector(
            Vector(1, 2, 3),
            Vector(4, 5, 6),
            Vector(7, 8, 9)
        )
        val matriz2: Matriz = Vector(
            Vector(9, 8, 7),
            Vector(6, 5, 4),
            Vector(3, 2, 1)
        )

        assert(Vector(Vector(30, 24, 18), Vector(84, 69, 54), Vector(138, 114, 90)) == obj.multMatriz(matriz1, matriz2))
    }
    test("testMultMatrizRecursivo"){
        val obj = new Taller4()
        //matrices de 4x4
        val matriz1: Matriz = Vector(
            Vector(1, 2, 3, 4),
            Vector(5, 6, 7, 8),
            Vector(9, 10, 11, 12),
            Vector(13, 14, 15, 16)
        )
        val matriz2: Matriz = Vector(
            Vector(16, 15, 14, 13),
            Vector(12, 11, 10, 9),
            Vector(8, 7, 6, 5),
            Vector(4, 3, 2, 1)
        )

        assert(Vector(Vector(80, 70, 60, 50), Vector(240, 214, 188, 162),
            Vector(400, 358, 316, 274), Vector(560, 502, 444, 386)) == obj.multMatrizRec(matriz1, matriz2))
    }
    test("testmultiplyMatricesParallel"){
        val obj = new Taller4()
        //matrices de 4x4
        val matriz1: Matriz = Vector(
            Vector(1, 2, 3, 4),
            Vector(5, 6, 7, 8),
            Vector(9, 10, 11, 12),
            Vector(13, 14, 15, 16)
        )
        val matriz2: Matriz = Vector(
            Vector(16, 15, 14, 13),
            Vector(12, 11, 10, 9),
            Vector(8, 7, 6, 5),
            Vector(4, 3, 2, 1)
        )

        assert(Vector(Vector(80, 70, 60, 50), Vector(240, 214, 188, 162),
            Vector(400, 358, 316, 274), Vector(560, 502, 444, 386)) == obj.multMatrizRecPar(matriz1, matriz2))
    }
    test("testmultMatrizRecPar"){
val obj = new Taller4()
        //matrices de 4x4
        val matriz1: Matriz = Vector(
            Vector(1, 2, 3, 4),
            Vector(5, 6, 7, 8),
            Vector(9, 10, 11, 12),
            Vector(13, 14, 15, 16)
        )
        val matriz2: Matriz = Vector(
            Vector(16, 15, 14, 13),
            Vector(12, 11, 10, 9),
            Vector(8, 7, 6, 5),
            Vector(4, 3, 2, 1)
        )

        assert(Vector(Vector(80, 70, 60, 50), Vector(240, 214, 188, 162),
            Vector(400, 358, 316, 274), Vector(560, 502, 444, 386)) == obj.multMatrizRecPar(matriz1, matriz2))

    }
    test("testMultMatrizPar"){
        val obj = new Taller4()
        //matrices de 4x4
        val matriz1: Matriz = Vector(
            Vector(1, 2, 3, 4),
            Vector(5, 6, 7, 8),
            Vector(9, 10, 11, 12),
            Vector(13, 14, 15, 16)
        )
        val matriz2: Matriz = Vector(
            Vector(16, 15, 14, 13),
            Vector(12, 11, 10, 9),
            Vector(8, 7, 6, 5),
            Vector(4, 3, 2, 1)
        )

        assert(Vector(Vector(80, 70, 60, 50), Vector(240, 214, 188, 162),
            Vector(400, 358, 316, 274), Vector(560, 502, 444, 386)) == obj.multMatrizPar(matriz1, matriz2))
    }
    test("testStrassen") {
        val obj = new Taller4()
        //matrices de 4x4
        val matriz1: Matriz = Vector(
            Vector(1, 2, 3, 4),
            Vector(5, 6, 7, 8),
            Vector(9, 10, 11, 12),
            Vector(13, 14, 15, 16)
        )
        val matriz2: Matriz = Vector(
            Vector(16, 15, 14, 13),
            Vector(12, 11, 10, 9),
            Vector(8, 7, 6, 5),
            Vector(4, 3, 2, 1)
        )

        assert(Vector(Vector(80, 70, 60, 50), Vector(240, 214, 188, 162),
            Vector(400, 358, 316, 274), Vector(560, 502, 444, 386)) == obj.multStrassen(matriz1, matriz2))
    }
    test("testProductoPunto") {
        val obj = new Taller4()
        val vector1: Vector[Int] = Vector(1, 2, 3, 4, 5)
        val vector2: Vector[Int] = Vector(25, 24, 23, 22, 21)
        assert(335 == obj.prodPunto(vector1, vector2))
    }
    test("testprodPuntoParD") {
        val obj = new Taller4()
        val vector1: Vector[Int] = Vector(1, 2, 3, 4, 5)
        val vector2: Vector[Int] = Vector(25, 24, 23, 22, 21)
        assert(335 == obj.prodPuntoParD(vector1, vector2))
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
    }

}

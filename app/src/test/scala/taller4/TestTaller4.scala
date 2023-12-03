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
            Vector(1, 2, 3, 4, 5),
            Vector(6, 7, 8, 9, 10),
            Vector(11, 12, 13, 14, 15),
            Vector(16, 17, 18, 19, 20),
            Vector(21, 22, 23, 24, 25)
        )

        val matriz2: Matriz = Vector(
            Vector(25, 24, 23, 22, 21),
            Vector(20, 19, 18, 17, 16),
            Vector(15, 14, 13, 12, 11),
            Vector(10, 9, 8, 7, 6),
            Vector(5, 4, 3, 2, 1)
        )
        assert(Vector(Vector(26, 26, 26, 26, 26), Vector(26, 26, 26, 26, 26), Vector(26, 26, 26, 26, 26), Vector(26, 26, 26, 26, 26), Vector(26, 26, 26, 26, 26)) == obj.sumMatriz(matriz1,matriz2))
    }
    test("testResta") {
        val obj = new Taller4()
        val matriz1: Matriz = Vector(
            Vector(1, 2, 3, 4, 5),
            Vector(6, 7, 8, 9, 10),
            Vector(11, 12, 13, 14, 15),
            Vector(16, 17, 18, 19, 20),
            Vector(21, 22, 23, 24, 25)
        )

        val matriz2: Matriz = Vector(
            Vector(25, 24, 23, 22, 21),
            Vector(10, 12, 14, 16, 18),
            Vector(1, 2, 3, 4, 5),
            Vector(10, 9, 8, 7, 6),
            Vector(5, 4, 3, 2, 1)
        )
        assert(Vector(Vector(-24, -22, -20, -18, -16), Vector(-4, -5, -6, -7, -8), Vector(10, 10, 10, 10, 10), Vector(6, 8, 10, 12, 14), Vector(16, 18, 20, 22, 24)) == obj.restaMatriz(matriz1, matriz2))
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

}

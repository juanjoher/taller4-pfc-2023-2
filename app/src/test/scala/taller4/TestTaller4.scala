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
}

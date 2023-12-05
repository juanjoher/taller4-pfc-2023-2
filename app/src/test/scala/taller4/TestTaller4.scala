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

import scala.collection.parallel.immutable.ParVector

@RunWith(classOf[JUnitRunner])
class TestTaller4 extends AnyFunSuite{
    type Matriz = Vector[Vector[Int]]
    //IMPLEMENTACIONES DE LOS TESTS DE LAS FUNCIONES DE TALLER4 RECIBIENDO NUMEROS BINARIOS
    test("testSuma"){
        val obj = new Taller4()
        val matriz1: Matriz = Vector(
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1),
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1)
        )
        val matriz2: Matriz = Vector(
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1),
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1)
        )
        assert(Vector(Vector(2, 0, 2, 0), Vector(0, 2, 0, 2),
            Vector(2, 0, 2, 0), Vector(0, 2, 0, 2)) == obj.sumMatriz(matriz1, matriz2))
    }
    test("testResta") {
        val obj = new Taller4()
        val matriz1: Matriz = Vector(
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1),
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1)
        )
        val matriz2: Matriz = Vector(
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1),
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1)
        )
        assert(Vector(Vector(0, 0, 0, 0), Vector(0, 0, 0, 0),
            Vector(0, 0, 0, 0), Vector(0, 0, 0, 0)) == obj.restaMatriz(matriz1, matriz2))
    }
    test("testMultMatriz") {
        val obj = new Taller4()
        val matriz1: Matriz = Vector(
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1),
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1)
        )
        val matriz2: Matriz = Vector(
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1),
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1)
        )
        assert(Vector(Vector(2, 0, 2, 0), Vector(0, 2, 0, 2),
            Vector(2, 0, 2, 0), Vector(0, 2, 0, 2)) == obj.multMatriz(matriz1, matriz2))
    }
    test("testMultMatrizRecursivo"){
        val obj = new Taller4()
        val matriz1: Matriz = Vector(
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1),
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1)
        )
        val matriz2: Matriz = Vector(
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1),
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1)
        )
        assert(Vector(Vector(2, 0, 2, 0), Vector(0, 2, 0, 2),
            Vector(2, 0, 2, 0), Vector(0, 2, 0, 2)) == obj.multMatrizRec(matriz1, matriz2))
    }
    test("testmultiplyMatricesParallel"){
        val obj = new Taller4()
        val matriz1: Matriz = Vector(
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1),
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1)
        )
        val matriz2: Matriz = Vector(
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1),
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1)
        )
        assert(Vector(Vector(2, 0, 2, 0), Vector(0, 2, 0, 2),
            Vector(2, 0, 2, 0), Vector(0, 2, 0, 2)) == obj.multMatrizPar(matriz1, matriz2))
    }
    test("testmultMatrizRecPar"){
        val obj = new Taller4()
        val matriz1: Matriz = Vector(
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1),
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1)
        )
        val matriz2: Matriz = Vector(
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1),
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1)
        )
        assert(Vector(Vector(2, 0, 2, 0), Vector(0, 2, 0, 2),
            Vector(2, 0, 2, 0), Vector(0, 2, 0, 2)) == obj.multMatrizRecPar(matriz1, matriz2))
    }
    test("testMultMatrizPar"){
        val obj = new Taller4()
        val matriz1: Matriz = Vector(
            Vector(1, 0, 1, 0, 1, 0),
            Vector(0, 1, 0, 1, 0, 1),
            Vector(1, 0, 1, 0, 1, 0),
            Vector(0, 1, 0, 1, 0, 1),
            Vector(1, 0, 1, 0, 1, 0),
            Vector(0, 1, 0, 1, 0, 1)
        )
        val matriz2: Matriz = Vector(
            Vector(1, 0, 1, 0, 1, 0),
            Vector(0, 1, 0, 1, 0, 1),
            Vector(1, 0, 1, 0, 1, 0),
            Vector(0, 1, 0, 1, 0, 1),
            Vector(1, 0, 1, 0, 1, 0),
            Vector(0, 1, 0, 1, 0, 1)
        )
        assert(Vector(Vector(3, 0, 3, 0, 3, 0), Vector(0, 3, 0, 3, 0, 3),
            Vector(3, 0, 3, 0, 3, 0), Vector(0, 3, 0, 3, 0, 3),
            Vector(3, 0, 3, 0, 3, 0), Vector(0, 3, 0, 3, 0, 3)) == obj.multMatrizPar(matriz1, matriz2))
    }
    test("testStrassen") {
        val obj = new Taller4()
        val matriz1: Matriz = Vector(
            Vector(1, 0, 1, 0, 1, 0),
            Vector(0, 1, 0, 1, 0, 1),
            Vector(1, 0, 1, 0, 1, 0),
            Vector(0, 1, 0, 1, 0, 1),
            Vector(1, 0, 1, 0, 1, 0),
            Vector(0, 1, 0, 1, 0, 1)
        )
        val matriz2: Matriz = Vector(
            Vector(1, 0, 1, 0, 1, 0),
            Vector(0, 1, 0, 1, 0, 1),
            Vector(1, 0, 1, 0, 1, 0),
            Vector(0, 1, 0, 1, 0, 1),
            Vector(1, 0, 1, 0, 1, 0),
            Vector(0, 1, 0, 1, 0, 1)
        )
        assert(Vector(Vector(3, 0, 3, 0, 3, 0), Vector(0, 3, 0, 3, 0, 3),
            Vector(3, 0, 3, 0, 3, 0), Vector(0, 3, 0, 3, 0, 3),
            Vector(3, 0, 3, 0, 3, 0), Vector(0, 3, 0, 3, 0, 3)) == obj.multStrassen(matriz1, matriz2))
    }
    test("testStrassenBinarios"){
        val obj = new Taller4()
        //matrices de 4x4
        val matriz1: Matriz = Vector(
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1),
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1)
        )
        val matriz2: Matriz = Vector(
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1),
            Vector(1, 0, 1, 0),
            Vector(0, 1, 0, 1)
        )

        assert(Vector(Vector(2, 0, 2, 0), Vector(0, 2, 0, 2),
            Vector(2, 0, 2, 0), Vector(0, 2, 0, 2)) == obj.multStrassen(matriz1, matriz2))
    }



    test("testProductoPunto") {
        val obj = new Taller4()
        val vector1: Vector[Int] = Vector(1, 2, 3, 4, 5)
        val vector2: Vector[Int] = Vector(25, 24, 23, 22, 21)
        assert(335 == obj.prodPunto(vector1, vector2))
    }
    //Test de producto punto usando vectores con entradas binarias(0,1)
    test("testprodPuntoParD") {
        val obj = new Taller4()
        //vector 6x6
        val vector1: ParVector[Int] = ParVector(1, 0, 1, 0, 1, 0)
        val vector2: ParVector[Int] = ParVector(0, 1, 0, 1, 0, 1)
        assert(0 == obj.prodPuntoParD(vector1, vector2))
    }



}

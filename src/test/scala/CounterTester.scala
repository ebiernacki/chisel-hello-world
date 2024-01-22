/*
 * A simple counter example with configurable bit width and with a test bench.
 * 
 */

import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

/**
 * Test the counter by printing out the value at each clock cycle.
 */

class CounterTester extends AnyFlatSpec with ChiselScalatestTester {

  "CounterTester test" should "pass" in {
    test(new Counter(4)).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      for (i <- 0 until 25) {
        println(i.toString + ": " + dut.io.out.peek.toString())
        dut.clock.step(1)
      }
    }
  }
}

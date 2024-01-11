/*
 * 
 * A simple counter example with configurable bit width and with a test bench.
 * Code from: https://github.com/schoeberl/chisel-examples/blob/master/src/main/scala/simple/Counter.scala
 */

import chisel3._

/**
 * A simple, configurable counter that wraps around.
 */
class Counter(size: Int) extends Module {
  val io = IO(new Bundle {
    val out = Output(UInt(size.W))
  })

  val r1 = RegInit(0.U(size.W))
  r1 := r1 + 1.U

  io.out := r1
}

/**
 * Main Object to create(emit) the verilog of the Counter Module
 * Puts the verilog into a folder called "generated"
 * Can be run from the sbt command line with: runMain CounterMain
 */
object CounterMain extends App {
  emitVerilog(new Counter(2), Array("--target-dir", "generated"))
}


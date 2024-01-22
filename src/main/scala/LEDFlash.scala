/*
 * This code is a minimal hardware described in Chisel.
 * 
 * Blinking LED: the FPGA version of Hello World
 * 
 * Code from https://github.com/schoeberl/chisel-examples/
 */

import chisel3._

/**
 * The blinking LED component.
 */

class LEDFlash extends Module {
  val io = IO(new Bundle {
    val led = Output(UInt(1.W)) 
  })
  
  
  val CNT_MAX = (100000000 - 1).U //for basys3

  val cntReg = RegInit(0.U(32.W))
  val blkReg = RegInit(0.U(1.W))

  cntReg := cntReg + 1.U
  when(cntReg === CNT_MAX) {
    cntReg := 0.U
    blkReg := ~blkReg
  }
  io.led := blkReg
}

/**
 * An object extending App to generate the Verilog code.
 */
object LEDFlashMain extends App {
  emitVerilog(new LEDFlash, Array("--target-dir", "generated"))
}

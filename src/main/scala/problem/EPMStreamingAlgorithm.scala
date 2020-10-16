package problem

import java.util

import org.uma.jmetal.algorithm.Algorithm
import org.uma.jmetal.solution.BinarySolution


trait EPMStreamingAlgorithm extends Algorithm[util.List[BinarySolution]]{

  private var EXECUTION_TIME: Long = 0
  private var MEMORY: Double = 0

  /**
    * It sets the execution time that the method takes on its processing
    * @param time
    */
  def setExecutionTime(time: Long) = EXECUTION_TIME = time

  def getExecutionTime(): Long  = EXECUTION_TIME

  /**
    * It sets the amount of memory that the algorithm consumes on its processing
    * @param memory
    */
  def setMemory(memory: Double) = MEMORY = memory

  def getMemory(): Double = MEMORY

}

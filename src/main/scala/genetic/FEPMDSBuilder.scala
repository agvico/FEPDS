package genetic

import java.util
import java.util.Comparator

import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection
import org.uma.jmetal.operator.{CrossoverOperator, MutationOperator, SelectionOperator}
import org.uma.jmetal.solution.BinarySolution
import org.uma.jmetal.util.AlgorithmBuilder
import org.uma.jmetal.util.comparator.{DominanceComparator, RankingAndCrowdingDistanceComparator}
import problem.{EPMSparkStreamingProblem, EPMStreamingProblem}
import problem.evaluator.EPMStreamingEvaluator


class FEPMDSBuilder(problem: EPMSparkStreamingProblem, crossover: CrossoverOperator[BinarySolution], mutation: MutationOperator[BinarySolution]) extends AlgorithmBuilder[FEPMDS] {

  private var maxEvaluations: Int = 25000
  private var populationSize: Int = 100
  private var confThreshold: Double = 0.6
  private var suppThreshold: Double = 0.1

  protected var matingPoolSize = populationSize
  protected var offspringPopulationSize = populationSize

  private var selectionOperator: SelectionOperator[util.List[BinarySolution], BinarySolution] = new BinaryTournamentSelection[BinarySolution](new RankingAndCrowdingDistanceComparator[BinarySolution])
  private var evaluator: EPMStreamingEvaluator = _
  private var dominanceComparator: Comparator[BinarySolution] = new DominanceComparator[BinarySolution]()



/* Setters */
  def setMaxEvaluations(value: Int) = maxEvaluations = value;
  def setPopulationSize(value: Int) = {populationSize = value; matingPoolSize = value;  offspringPopulationSize = value}
  def setMatingPoolSize(value: Int) = matingPoolSize = value;
  def setOffspringPopulationSize(value: Int) = offspringPopulationSize = value;
  def setSelectionOperator(value: SelectionOperator[util.List[BinarySolution], BinarySolution]) = selectionOperator = value;
  def setEvaluator(value: EPMStreamingEvaluator) = evaluator = value
  def setDominanceComparator(value: Comparator[BinarySolution]) = dominanceComparator = value
  def setConfidenceThreshold(value: Double) = confThreshold = value
  def setSupportThreshold(value: Double)  = suppThreshold = value

  /* Getters */
  def getMaxEvaluations: Int = maxEvaluations
  def getPopulationSize: Int = populationSize
  def getMatingPoolSize: Int = matingPoolSize
  def getOffspringPopulationSize: Int = offspringPopulationSize
  def getSelectionOperator: SelectionOperator[util.List[BinarySolution], BinarySolution] = selectionOperator
  def getEvaluator: EPMStreamingEvaluator = evaluator
  def getDominanceComparator: Comparator[BinarySolution] = dominanceComparator


  override def build(): FEPMDS = {
    new FEPMDS(problem, confThreshold, suppThreshold, maxEvaluations,populationSize,matingPoolSize,offspringPopulationSize, crossover, mutation,selectionOperator, dominanceComparator,evaluator)
  }
}

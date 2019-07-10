/* 
 * The MIT License
 *
 * Copyright 2018 Ángel Miguel García Vico.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package es.ujaen.simidat.agvico.moa.subgroupdiscovery.genetic.criteria;

import es.ujaen.simidat.agvico.moa.subgroupdiscovery.genetic.GeneticAlgorithm;

/**
 * Stopping criterion based on the number of maximum generations of the genetic algorithm
 * 
 * @author Angel Miguel Garcia-Vico (agvico@ujaen.es)
 */
public final class MaxGenerationsStoppingCriteria extends StoppingCriteria{

    private long maxGen;
    
    public MaxGenerationsStoppingCriteria(long maxGenerations){
        this.maxGen = maxGenerations;
    }
    
    @Override
    public boolean checkStopCondition(GeneticAlgorithm GA) {
        return GA.getGen() >= getMaxGen();
    }

    /**
     * @return the maxGen
     */
    public long getMaxGen() {
        return maxGen;
    }

    /**
     * @param maxGen the maxGen to set
     */
    public void setMaxGen(long maxGen) {
        this.maxGen = maxGen;
    }
    
}

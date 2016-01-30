/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels;

/**
 *
 * @author MEA
 */
 public interface IGeneratable<TInput,TOuput> {
    
   TOuput Generate(TInput ... args);
}

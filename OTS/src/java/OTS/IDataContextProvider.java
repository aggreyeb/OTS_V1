/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS;

import OTS.DataModels.DataSource;

/**
 *
 * @author MEA
 */
public interface IDataContextProvider {
    public DataSource Create();
}

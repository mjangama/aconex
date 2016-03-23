package com.aconex.dao.contract;

import com.aconex.model.contract.Contract;

import java.util.List;

/**
 * Created by madhuri on 3/20/16.
 */
public interface ContractDao {
    /**
     * returns the list of contracts by projectId
     * @param projectId
     * @return
     */
    public List<Contract> getContractsByProjectId(int projectId);

    /**
     * returns the contract by contractId
     * @param contractId
     * @return
     */
    public Contract getContractById(int contractId);

    /**
     * inserts a new contract
     * @param contract
     * @return
     */
    public int insertContract(Contract contract);

    /**
     * updates the existing contract
     * @param contract
     */
    public void updateContract(Contract contract);

}

package com.aconex.services.contract;

import com.aconex.model.contract.Contract;

import java.util.List;

/**
 * Created by madhuri on 3/20/16.
 */
public interface ContractService {

    /**
     * returns the list of contracts by projectId
     *
     * @param projectId
     * @return
     */
    public List<Contract> getContractsByProjectId(int projectId);

    /**
     * returns the contract by contractId
     *
     * @param contractId
     * @return
     */
    public Contract getContractById(int contractId);

    /**
     * inserts a new contract or updates the existing contract
     *
     * @param contract
     * @return
     */
    public int upsertContract(Contract contract);

}

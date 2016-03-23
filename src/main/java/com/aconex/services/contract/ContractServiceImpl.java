package com.aconex.services.contract;

import com.aconex.dao.contract.ContractDao;
import com.aconex.model.contract.Contract;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by madhuri on 3/20/16.
 */
@Service
public class ContractServiceImpl implements ContractService {

    private static Logger logger = Logger.getLogger(ContractServiceImpl.class);
    private ContractDao contractDao;

    public void setContractDao(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    /**
     * returns the list of contracts by projectId
     *
     * @param projectId
     * @return
     */
    public List<Contract> getContractsByProjectId(int projectId) {
        logger.info("Getting contracts by project id : "+ projectId);
        List<Contract> contractList = contractDao.getContractsByProjectId(projectId);
        if (contractList != null && contractList.size() > 0) {
            logger.info("Returning contract list for project id " + projectId);
            return contractList;
        }
        logger.info("No contracts found for project id " + projectId);
        return null;
    }

    /**
     * returns the contract by contractId
     *
     * @param contractId
     * @return
     */
    public Contract getContractById(int contractId) {
        logger.info("Getting contract by contract id : " + contractId);
        Contract contract = contractDao.getContractById(contractId);
        if (contract != null) {
            logger.info("Returning contract for contract id " + contractId);
            return contract;
        }
        logger.info("No contract found for contract id " + contract);
        return null;
    }

    /**
     * inserts a new contract or updates the existing contract
     *
     * @param contract
     * @return
     */
    public int upsertContract(Contract contract) {
        logger.info("Insert / Update contract");
        int contractId = 0;
        if (contract != null) {
            if (contract.getId() > 0) {
                logger.info("Updating contract for contract id : " + contract.getId() + ", Name: "+ contract.getName());
                contractDao.updateContract(contract);
                contractId = contract.getId();
            } else {
                logger.info("Inserting new contract for contract name : " + contract.getName());
                contractId = contractDao.insertContract(contract);
            }
        }
        return contractId;
    }


}

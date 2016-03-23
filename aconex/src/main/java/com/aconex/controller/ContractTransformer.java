package com.aconex.controller;

import com.aconex.model.contract.Contract;
import com.aconex.model.project.Project;
import com.aconex.model.vendor.Vendor;
import org.apache.log4j.Logger;

/**
 * Created by madhuri on 3/21/16.
 */

public class ContractTransformer {

    private static Logger logger = Logger.getLogger(ContractTransformer.class);


    /**
     * transforms the bean to form
     * @param contract
     * @param project
     * @return
     */
    public ContractForm beanToForm(Contract contract, Project project) {
        logger.info("Transforming contract bean to contract form");
        ContractForm form = new ContractForm();
        form.setMode(contract == null ? "add" : "edit");
        form.setProjectId(String.valueOf(project.getId()));
        form.setProjectName(project.getName());
        if (contract != null && contract.getId() > 0) {
            form.setContractId(String.valueOf(contract.getId()));
            form.setName(contract.getName());
            form.setCode(contract.getCode());
            form.setDescription(contract.getDescription());
            if (contract.getBudget() > 0.0) {
                form.setBudget(String.valueOf(contract.getBudget()));
            }
            if (contract.getCommittedCost() > 0.0) {
                form.setCommittedCost(String.valueOf(contract.getCommittedCost()));
            }
            if (contract.getForecast() > 0.0) {
                form.setForecast(String.valueOf(contract.getForecast()));
            }
            if (contract.getPayment() > 0.0) {
                form.setPayment(String.valueOf(contract.getPayment()));
            }
            if (contract.getCompletionPercentage() > 0) {
                form.setCompletionPercentage(String.valueOf(contract.getCompletionPercentage()));
            }
            if (contract.getVendor() != null && contract.getVendor().getId() > 0) {
                form.setVendorId(String.valueOf(contract.getVendor().getId()));
            }
        }
        return form;
    }

    /**
     * transforms form to bean
     * @param contractForm
     * @return
     */
    public Contract formToBean(ContractForm contractForm) {
        logger.info("Transforming contract form to contract bean");
        Contract contract = new Contract();
        if (contractForm.getContractId() != null && Integer.parseInt(contractForm.getContractId()) > 0) {
            contract.setId(Integer.parseInt(contractForm.getContractId()));
        }
        contract.setName(contractForm.getName());
        contract.setDescription(contractForm.getDescription());
        contract.setCode(contractForm.getCode());
        Project project = new Project();
        project.setId(Integer.parseInt(contractForm.getProjectId()));
        contract.setProject(project);
        Vendor vendor = new Vendor();
        vendor.setId(Integer.parseInt(contractForm.getVendorId()));
        contract.setVendor(vendor);
        contract.setBudget(Float.valueOf(contractForm.getBudget()));
        contract.setPayment(Float.valueOf(contractForm.getPayment()));
        contract.setForecast(Float.valueOf(contractForm.getForecast()));
        contract.setCommittedCost(Float.valueOf(contractForm.getCommittedCost()));
        contract.setCompletionPercentage(Integer.valueOf(contractForm.getCompletionPercentage()));
        return contract;
    }
}

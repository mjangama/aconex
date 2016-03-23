package com.aconex.controller;

import com.aconex.model.contract.Contract;
import com.aconex.model.project.Project;
import com.aconex.model.vendor.Vendor;
import com.aconex.services.contract.ContractService;
import com.aconex.services.project.ProjectService;
import com.aconex.services.vendor.VendorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import javax.validation.Valid;


/**
 * Created by madhuri on 3/20/16.
 */
@Controller
public class ContractController {

    private static Logger logger = Logger.getLogger(ContractController.class);

    @Autowired
    private ContractService contractService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private ServletContext servletContext;


    @PostConstruct
    /**
     * Load the vendor and project list only once, and set it in the application context
     */
    public void initialize() {

        logger.info("Initializing application");

        List<Vendor> vendorList = vendorService.getVendorList();
        servletContext.setAttribute("vendorList", vendorList);
        logger.info("Loading vendor list complete");

        List<Project> projectList = projectService.getProjectList();
        servletContext.setAttribute("projectList", projectList);
        logger.info("Loading project list complete");
    }

    /**
     * view the list of contracts
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/")
    public String view(HttpServletRequest request) {

        logger.info("Displaying default project details");
        List<Project> projectList = (List<Project>) request.getSession().getServletContext().getAttribute("projectList");
        if (projectList != null && projectList.size() > 0) {
            request.setAttribute("projectDetail", projectService.getProjectById(projectList.get(0).getId()));
        }
        return "home";
    }

    /**
     * view the list of contracts by projectId
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/view/{projectId}")
    public String view(HttpServletRequest request, @PathVariable int projectId) {
        logger.info("Retrieving project details for project id: " + projectId);
        request.setAttribute("projectDetail", projectService.getProjectById(projectId));
        return "home";
    }

    /**
     * add a new contract for a project
     *
     * @param request
     * @param pid project id
     * @return
     */
    @RequestMapping(value = "/contract/add")
    public String add(HttpServletRequest request, @RequestParam int pid) {
        logger.info("Adding new contract for project : " + pid);
        Project project = projectService.getProjectById(pid);
        if (project != null) {
            logger.info("Project retrieved for project id: " + pid);
            ContractTransformer transformer = new ContractTransformer();
            request.setAttribute("contractForm", transformer.beanToForm(null, project));
        }
        return "addEdit";
    }

    /**
     * edit contract
     *
     * @param request
     * @param contractId
     * @return
     */
    @RequestMapping(value = "/contract/edit/{contractId}")
    public String edit(HttpServletRequest request, @PathVariable int contractId) {
        logger.info("Editing contract for contract id: " + contractId);
        Contract contract = contractService.getContractById(contractId);
        if (contract != null) {
            logger.info("Contract retrieved for contract id: " + contractId);
            ContractTransformer transformer = new ContractTransformer();
            request.setAttribute("contractForm", transformer.beanToForm(contract, contract.getProject()));
        }
        return "addEdit";
    }

    /***
     * saves the existing contract or inserts a new contract
     *
     * @param request
     * @param contractForm
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/contract/save")
    public String save(HttpServletRequest request, @Valid ContractForm contractForm,
                       BindingResult bindingResult) {

        logger.info("Saving / Adding contract");
        if (bindingResult.hasErrors()) {
            request.setAttribute("errors", bindingResult.getAllErrors());
            request.setAttribute("contractForm", contractForm);
            return "addEdit";
        }

        ContractTransformer transformer = new ContractTransformer();
        Contract contract = transformer.formToBean(contractForm);
        contractService.upsertContract(contract);

        request.setAttribute("projectDetail", projectService.getProjectById(contract.getProject().getId()));
        return "home";
    }


    // For unit testing
    public void setVendorService(VendorService vendorService) {
        this.vendorService = vendorService;
    }
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }
}

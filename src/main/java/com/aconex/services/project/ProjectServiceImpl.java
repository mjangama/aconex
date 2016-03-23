package com.aconex.services.project;

import com.aconex.dao.project.ProjectDao;
import com.aconex.model.contract.Contract;
import com.aconex.model.project.Project;
import com.aconex.services.contract.ContractServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by madhuri on 3/20/16.
 */

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ContractServiceImpl contractService;

    private static Logger logger = Logger.getLogger(ProjectServiceImpl.class);

    private ProjectDao projectDao;

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public void setContractService(ContractServiceImpl contractService) {
        this.contractService = contractService;
    }

    /**
     * returns all the projects
     * @return
     */
    public List<Project> getProjectList() {
        logger.info("Retrieving list of projects");
        List<Project> projectList = projectDao.getProjectList();
        return projectList;
    }

    /**
     * returns the project by Id
     * @param projectId
     * @return
     */
    public Project getProjectById(int projectId) {
        logger.info("Retrieving project by project id : " + projectId);
        Project project = projectDao.getProjectById(projectId);
        if (project != null) {
            logger.info("Project found for project id : " + projectId);
            List<Contract> contractList = contractService.getContractsByProjectId(projectId);
            logger.info("Retrieving contracts for project id : " + projectId);
            if (contractList != null && !contractList.isEmpty()) {
                project.setContractList(contractList);
            }
        }
        return project;
    }

}

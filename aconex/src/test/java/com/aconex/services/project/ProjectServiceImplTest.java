package com.aconex.services.project;

import com.aconex.dao.project.ProjectDao;
import com.aconex.model.contract.Contract;
import com.aconex.model.project.Project;
import com.aconex.services.contract.ContractService;
import com.aconex.services.contract.ContractServiceImpl;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

/**
 * Created by madhuri on 3/21/16.
 */
public class ProjectServiceImplTest extends TestCase {

    ProjectDao mockprojectDao = createMock(ProjectDao.class);

    ContractServiceImpl mockContractService = createMock(ContractServiceImpl.class);

    private static final int PROJECT_ID = 12;

    private static ProjectServiceImpl projectService = new ProjectServiceImpl();


    public void setUp() {
        projectService.setProjectDao(mockprojectDao);
        projectService.setContractService(mockContractService);

    }

    private List<Project> prepareProjectList() {
        List<Project> projectList = new ArrayList<Project>();
        for (int i = 0; i < 3; i++) {
            Project project = new Project();
            project.setId((int) (Math.random() * 100000));
            project.setName("i" + " name");
            project.setDescription("i" + " description");
            projectList.add(project);
        }
        return projectList;
    }

    private List<Contract> prepareContractsList(int projectId) {
        List<Contract> contractList = new ArrayList<Contract>();
        for (int i = 0; i < 3; i++) {
            Contract c = new Contract();
            c.setId((int) (Math.random() * 100000));
            c.setName(i + "name");
            c.setCode(i + "code");
            c.setDescription(i + "description");
            c.setBudget(new Float(i + 10.00));
            c.setCommittedCost(new Float(i + 20.00));
            c.setForecast(new Float(i + 30.00));
            c.setCompletionPercentage(i);
            c.setPayment(new Float(i + 40.00));
            Project project = new Project();
            project.setId(projectId);
            c.setProject(project);
            contractList.add(c);
        }
        return contractList;
    }

    /**
     * Method: getProjectList()
     */
    public void testGetProjectList() {
        expect(mockprojectDao.getProjectList()).andReturn(prepareProjectList());
        replay(mockprojectDao);
        List<Project> projectList = projectService.getProjectList();
        assertNotNull("verify the list is not null", projectList);
        assertEquals("verify the list size is same", projectList.size(), prepareProjectList().size());
    }

    /**
     * Method: getProjectById(int projectId)
     */
    public void testGetProjectById() {
        Project p = new Project();
        p.setId(PROJECT_ID);
        p.setName("Test Project");
        p.setDescription("Test description");
        expect(mockprojectDao.getProjectById(PROJECT_ID)).andReturn(p);
        replay(mockprojectDao);
        expect(mockContractService.getContractsByProjectId(PROJECT_ID)).andReturn(prepareContractsList(PROJECT_ID));
        replay(mockContractService);
        Project project = projectService.getProjectById(PROJECT_ID);
        assertNotNull("verify the project is not null", project);
        assertTrue("verify projectID is > 0", project.getId() > 0);
        assertEquals("verify the contract name is same", project.getName(), p.getName());
        assertEquals("verify the contract list size is same", project.getContractList().size(), prepareContractsList(PROJECT_ID).size());

    }

}

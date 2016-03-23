package com.aconex.controller.contract;

import com.aconex.BaseSpringControllerTest;
import com.aconex.controller.ContractController;
import com.aconex.controller.ContractForm;
import com.aconex.model.contract.Contract;
import com.aconex.model.project.Project;
import com.aconex.model.vendor.Vendor;
import com.aconex.services.contract.ContractService;
import com.aconex.services.project.ProjectService;

import javax.servlet.ServletContext;

import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by madhuri on 3/21/16.
 */
public class ContractControllerTest extends BaseSpringControllerTest {

    final static int PROJECT_ID = 123;

    ContractController controller;

    private ContractService contractService;
    private ProjectService projectService;
    List<Project> projectList;
    List<Vendor>  vendorList;

    private static final int CONTRACT_ID = 1;


    public void setUp() {
        contractService = createMock(ContractService.class);
        projectService = createMock(ProjectService.class);
        controller = new ContractController();
        controller.setContractService(contractService);
        controller.setProjectService(projectService);
        projectList = new ArrayList<com.aconex.model.project.Project>();
        vendorList = new ArrayList<Vendor>();

        for (int i = 0; i < 3; i++) {
            Project project = new Project();
            project.setId((int) (Math.random() * 100000));
            project.setName(i + "name");
            projectList.add(project);
        }


        for (int i = 0; i < 3; i++) {
            Vendor vendor = new Vendor();
            vendor.setId((int) (Math.random() * 100000));
            vendor.setName(i + "name");
            vendorList.add(vendor);
        }
        session.getServletContext().setAttribute("projectList", projectList);
        session.getServletContext().setAttribute("vendorList", vendorList);
        request.setSession(session);

    }

    public void testView() {
        expect(projectService.getProjectById(projectList.get(0).getId())).andReturn(projectList.get(0));
        replay(projectService);
        String path = controller.view(request);
        assertEquals("verify the page is loaded", path, "home");
        assertNotNull("Verify the project list is not null", request.getSession().getServletContext().getAttribute("projectList"));
        assertNotNull("Verify project detail is not null", request.getAttribute("projectDetail"));
    }

    public void testViewByProjectId() {
        int projectId = projectList.get(1).getId();
        expect(projectService.getProjectList()).andReturn(projectList);
        expect(projectService.getProjectById(projectId)).andReturn(projectList.get(1));
        replay(projectService);
        String path = controller.view(request, projectId);
        assertEquals("verify the page is loaded", "home", path);
        assertNotNull("Verify project list is not null", request.getSession().getServletContext().getAttribute("projectList"));
        assertNotNull("Verify project detail is not null", request.getAttribute("projectDetail"));
    }

    public void testAdd() {
        int projectId = projectList.get(1).getId();
        expect(projectService.getProjectById(projectId)).andReturn(projectList.get(1));
        replay(projectService);
        String path = controller.add(request, projectId);
        assertEquals("verify the page is loaded", "addEdit", path);
        assertNotNull("Verify contract form is not null", request.getAttribute("contractForm"));
    }

    public void testAddContractToInvalidProject() {
        int projectId = -12; //invalid projectId
        expect(projectService.getProjectById(projectId)).andReturn(null);
        replay(projectService);
        String path = controller.add(request, projectId);
        assertEquals("verify the page is loaded", "addEdit", path);
        assertNull("Verify contract form is null", request.getAttribute("contractForm"));
    }


    public void testEdit() {
        Contract contract = prepareContract(CONTRACT_ID);
        expect(contractService.getContractById(CONTRACT_ID)).andReturn(contract);
        replay(contractService);
        String path = controller.edit(request, CONTRACT_ID);
        assertEquals("verify the page is loaded", "addEdit", path);
        assertNotNull("Verify contract form is not null", request.getAttribute("contractForm"));
    }

    public void testEditInvalidContract() {
        expect(contractService.getContractById(-10)).andReturn(null);
        replay(contractService);
        String path = controller.edit(request, -10);
        assertEquals("verify the page is loaded", "addEdit", path);
        assertNull("Verify contract form is null", request.getAttribute("contractForm"));
    }


    public void testSave() {
        ContractForm form = prepareContractForm();
        Contract contract = prepareContract(CONTRACT_ID);
        expect(contractService.upsertContract(isA(Contract.class))).andReturn(CONTRACT_ID);
        expect(projectService.getProjectById(PROJECT_ID)).andReturn(contract.getProject());
        replay(projectService);
        replay(contractService);
        String path = controller.save(request, form, result);
        assertEquals("verify the page is loaded", "home", path);
        assertNotNull("Verify project detail is not null", request.getAttribute("projectDetail"));
    }

    private Contract prepareContract(int contractId) {
        Contract contract = new Contract();
        contract.setId(contractId);
        contract.setName("Test name");
        contract.setCode("Test code");
        contract.setDescription("Test description");
        contract.setBudget(new Float(10.00));
        contract.setCommittedCost(new Float(20.00));
        contract.setForecast(new Float(30.00));
        contract.setCompletionPercentage(10);
        contract.setPayment(new Float(40.00));
        Project p = new Project();
        p.setId(PROJECT_ID);
        contract.setProject(p);
        return contract;
    }

    private ContractForm prepareContractForm() {
        ContractForm form = new ContractForm();
        form.setContractId(String.valueOf(CONTRACT_ID));
        form.setName("Test name");
        form.setCode("Test code");
        form.setDescription("Test description");
        form.setBudget("10.00");
        form.setCommittedCost("20.00");
        form.setForecast("30.00");
        form.setCompletionPercentage("10");
        form.setPayment("40.00");
        form.setProjectId(String.valueOf(PROJECT_ID));
        form.setVendorId("10");
        return form;
    }

}

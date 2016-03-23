package com.aconex.services.contract;

import com.aconex.dao.contract.ContractDao;
import com.aconex.model.contract.Contract;
import com.aconex.model.project.Project;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;


/**
 * Created by madhuri on 3/21/16.
 */
public class ContractServiceImplTest extends TestCase {

    private static final int PROJECT_ID = 12;
    private static final int CONTRACT_ID = 1;

    ContractDao mockContractDao = createMock(ContractDao.class);

    private static ContractServiceImpl service = new ContractServiceImpl();


    public void setUp() {
        service.setContractDao(mockContractDao);
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

    private Contract prepareContract(int contractId) {
        Contract contract = new Contract();
        contract.setId(contractId);
        contract.setName("Test name");
        contract.setCode("Test code");
        contract.setDescription("Test description");
        contract.setBudget(new Float(10.00));
        contract.setCommittedCost(new Float(20.00));
        contract.setForecast(new Float(30.00));
        contract.setCompletionPercentage(1);
        contract.setPayment(new Float(40.00));
        return contract;
    }


    /**
     * Method: getContractByProjectId(int projectId)
     */
    public void testGetContractByProjectId() {
        List<Contract> contractSetUpList = prepareContractsList(PROJECT_ID);
        expect(mockContractDao.getContractsByProjectId(PROJECT_ID)).andReturn(contractSetUpList);
        replay(mockContractDao);
        List<Contract> contractList = service.getContractsByProjectId(PROJECT_ID);
        assertNotNull("verify the list is not null", contractList);
        assertEquals("verify the list size is same", contractList.size(), contractSetUpList.size());
    }

    /**
     * Method: getContractById(int contractId)
     */
    public void testGetContractById() {
        Contract contract = prepareContract(CONTRACT_ID);
        expect(mockContractDao.getContractById(CONTRACT_ID)).andReturn(contract);
        replay(mockContractDao);
        Contract c = service.getContractById(CONTRACT_ID);
        assertNotNull("verify the contract is not null", c);
        assertTrue("verify contractId is > 0", c.getId() > 0);
        assertEquals("verify the contract name is same", c.getName(), contract.getName());
    }

    /**
     * Method: upsertContract(Contract contract)
     */
    public void testUpdateContract() {
        mockContractDao.updateContract(prepareContract(CONTRACT_ID));
        int contractId = service.upsertContract(prepareContract(CONTRACT_ID));
        assertNotNull("verify contractId is not null", contractId);
        assertEquals("verify contractId is equal", contractId, CONTRACT_ID);

    }

    /**
     * Method: insertContract(Contract contract)
     */
    public void testInsertContract() {
        Contract contract = prepareContract(0);
        expect(mockContractDao.insertContract(contract)).andReturn(CONTRACT_ID);
        replay(mockContractDao);
        int contractId = service.upsertContract(contract);
        assertNotNull("verify contractId is not null", contractId);
        assertEquals("verify contractId is equal", contractId, CONTRACT_ID);

    }


}

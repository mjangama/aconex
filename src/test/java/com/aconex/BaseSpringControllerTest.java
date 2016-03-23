package com.aconex;


import junit.framework.TestCase;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.validation.MapBindingResult;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import static org.easymock.EasyMock.createMock;


import java.util.HashMap;

/**
 * Created by madhuri on 3/21/16.
 */
public class BaseSpringControllerTest extends TestCase {

    protected MockHttpServletRequest request = new MockHttpServletRequest();
    protected MockHttpServletResponse response = new MockHttpServletResponse();
    protected MockHttpSession session = new MockHttpSession();
    protected Validator mockValidator = createMock(Validator.class);
    protected MapBindingResult result = new MapBindingResult(new HashMap(), "objectName");

    public BaseSpringControllerTest(){
    }

    public MockHttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(MockHttpServletRequest request) {
        this.request = request;
    }

    public MockHttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(MockHttpServletResponse response) {
        this.response = response;
    }

    public MockHttpSession getSession() {
        return session;
    }

    public void setSession(MockHttpSession session) {
        this.session = session;
    }

    public Validator getMockValidator() {
        return mockValidator;
    }

    public void setMockValidator(Validator mockValidator) {
        this.mockValidator = mockValidator;
    }
}


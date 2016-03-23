<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: madhuri
  Date: 3/20/16
--%>

<c:choose>
    <c:when test="${contractForm == null}"><h1 class="sub-header">Invalid project or contract selected</h1>
    </c:when>
    <c:otherwise>

        <h2 class="sub-header">
                ${contractForm.mode != null && contractForm.mode.equalsIgnoreCase("edit") ? "Edit contract" : "Add Contract"}
        </h2>

        <form:form name="contractForm" cssClass="form-horizontal" action="/aconex/contract/save" method="post"
                   commandName="contractForm">
            <p><form:errors path="*" cssClass="error"/></p>

            <form id="contractForm" name="contractForm" class="form-horizontal" role="form" action="/aconex/contract/save" method="post">

            <input type="hidden" name="projectId" value="${contractForm.projectId}"/>
            <input type="hidden" name="mode" value="${contractForm.mode}"/>
            <input type="hidden" name="contractId"
                   value="${contractForm.getContractId() != null ? contractForm.getContractId() : "0"}"/>


            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="submit" class="btn btn-primary">
                            ${contractForm.mode != null && contractForm.mode.equalsIgnoreCase("edit") ? "Save" : "Add"}
                    </button>
                    <a href="/aconex/view/${contractForm.projectId}" class="btn btn-default" role="button">Cancel</a>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-2" for="name">Contract name:</label>

                <div class="col-xs-6">
                    <form:input path="name" id="contract_name" cssClass="form-control" placeholder="Contract name"/>
                    <form:errors path="name" cssClass="has-error"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-2" for="contract_code">Contract code:</label>

                <div class="col-xs-6">
                    <form:input path="code" id="contract_code" cssClass="form-control" placeholder="Contract code"/>
                    <form:errors path="code" cssClass="has-error"/>
                </div>
            </div>


            <div class="form-group">
                <label class="control-label col-xs-2" for="contract_description">Description:</label>

                <div class="col-xs-6">
                    <form:textarea path="description" id="contract_description" rows="5" cols="20"
                                   cssClass="form-control" placeholder="Description of the contract"/>
                    <form:errors path="description" cssClass="has-error"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-2" for="budget">Budget:</label>

                <div class="col-xs-4">
                    <form:input path="budget" id="budget" cssClass="form-control" placeholder="Budget"/>
                    <form:errors path="budget" cssClass="has-error"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-2" for="committed_cost">Committed cost:</label>

                <div class="col-xs-4">
                    <form:input path="committedCost" id="committed_cost" cssClass="form-control"
                                placeholder="Committed cost"/>
                    <form:errors path="committedCost" cssClass="has-error"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-2" for="forecast">Forecast:</label>

                <div class="col-xs-4">
                    <form:input path="forecast" id="forecast" cssClass="form-control" placeholder="Forecast"/>
                    <form:errors path="forecast" cssClass="has-error"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-2" for="payment">Payment:</label>

                <div class="col-xs-4">
                    <form:input path="payment" id="payment" cssClass="form-control" placeholder="Payment"/>
                    <form:errors path="payment" cssClass="has-error"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-2" for="completion_percentage">Completion percentage:</label>

                <div class="col-xs-4">
                    <form:input path="completionPercentage" id="completion_percentage" cssClass="form-control"
                                placeholder="Completion percentage"/>
                    <form:errors path="completionPercentage" cssClass="has-error"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-2" for="vendor">Vendor:</label>

                <div class="col-xs-4">
                    <form:select path="vendorId" id="vendor" class="form-control" placeholder="Vendor">
                        <option value="">Select Vendor</option>
                        <c:forEach var="vendor" items="${applicationScope.vendorList}">
                            <option value="${vendor.id}" ${vendor.id == contractForm.vendorId ? 'selected="selected"' : ''}>${vendor.name}</option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="vendorId" cssClass="has-error"/>

                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="submit" class="btn btn-primary">
                            ${contractForm.mode != null && contractForm.mode.equalsIgnoreCase("edit") ? "Save" : "Add"}
                    </button>
                    <a href="/aconex/view/${contractForm.projectId}" class="btn btn-default" role="button">Cancel</a>
                </div>
            </div>

        </form:form>

    </c:otherwise>
</c:choose>

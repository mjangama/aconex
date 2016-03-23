<%--
  Created by IntelliJ IDEA.
  User: Madhuri
  Date: 3/20/16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${projectDetail == null}"><h1 class="sub-header">No project details found</h1>
    </c:when>
    <c:otherwise>

        <h1 class="page-header"> ${projectDetail.name}</h1>

        <div class="sub-header">
            <h2>Project Owner: ${projectDetail.user.firstName} ${projectDetail.user.lastName}</h2>
            <span class="text-muted"><b>Description:</b> ${projectDetail.description}</span><br/>
            <span class="text-muted"><b>Project
                Duration:</b> ${projectDetail.startDate} - ${projectDetail.endDate}</span>
        </div>

        <h2 class="sub-header">Contract List</h2>

        <p><a href="/aconex/contract/add?pid=${projectDetail.id}" class="btn btn-primary" role="button">Add Contract</a></p>

        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Code</th>
                    <th>Description</th>
                    <th>Budget</th>
                    <th>Committed Cost</th>
                    <th>Forecast</th>
                    <th>Paid</th>
                    <th>Complete</th>
                    <th>Vendor</th>
                </tr>
                </thead>
                <tbody>

                <c:if test="${projectDetail.contractList != null}">
                    <c:forEach var="contract" items="${projectDetail.contractList}">
                        <tr>
                            <td><a href="/aconex/contract/edit/${contract.id}">${contract.name}</a></td>
                            <td>${contract.description}</td>
                            <td>${contract.budget}</td>
                            <td>$${contract.committedCost}</td>
                            <td>$${contract.forecast}</td>
                            <td>$${contract.payment}</td>
                            <td>${contract.completionPercentage}%</td>
                            <td>${contract.vendor.name}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
    </c:otherwise>
</c:choose>


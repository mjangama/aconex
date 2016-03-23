package com.aconex.model.project;

import com.aconex.model.Identifier;
import com.aconex.model.contract.Contract;
import com.aconex.model.user.User;

import java.util.Date;
import java.util.List;

/**
 * Created by madhuri on 3/20/16.
 */
public class Project extends Identifier{

    private User user;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<Contract> contractList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }
}

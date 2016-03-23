package com.aconex.model.contract;

import com.aconex.model.Identifier;
import com.aconex.model.project.Project;
import com.aconex.model.vendor.Vendor;

/**
 * Created by madhuri on 3/20/16.
 */
public class Contract extends Identifier{

    private String description;
    private String code;
    private float budget;
    private float committedCost;
    private float forecast;
    private float payment;
    private int completionPercentage;
    private Vendor vendor;
    private Project project;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public float getCommittedCost() {
        return committedCost;
    }

    public void setCommittedCost(float committedCost) {
        this.committedCost = committedCost;
    }

    public float getForecast() {
        return forecast;
    }

    public void setForecast(float forecast) {
        this.forecast = forecast;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public int getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(int completionPercentage) {
        this.completionPercentage = completionPercentage;
    }


    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

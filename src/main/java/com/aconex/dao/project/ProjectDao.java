package com.aconex.dao.project;

import com.aconex.model.project.Project;

import java.util.List;

/**
 * Created by madhuri on 3/20/16.
 */


public interface ProjectDao {

    /**
     * returns all the projects from the database
     * @return
     */
    public List<Project> getProjectList();


    /**
     * returns the project by Id
     * @param projectId
     * @return
     */
    public Project getProjectById(int projectId);


}

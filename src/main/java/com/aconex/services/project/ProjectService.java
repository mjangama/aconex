package com.aconex.services.project;

import com.aconex.model.project.Project;

import java.util.List;

/**
 * Created by madhuri on 3/20/16.
 */
public interface ProjectService {

    /**
     * returns all the projects
     *
     * @return
     */
    public List<Project> getProjectList();

    /**
     * returns the project by Id
     *
     * @param projectId
     * @return
     */
    public Project getProjectById(int projectId);
}

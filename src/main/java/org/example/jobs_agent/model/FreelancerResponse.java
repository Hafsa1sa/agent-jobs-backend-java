package org.example.jobs_agent.model;


import lombok.Data;
import java.util.List;

@Data
public class FreelancerResponse {
    private List<Project> projects;

    public FreelancerResponse(List<Project> projects) {
        this.projects = projects;
    }
}

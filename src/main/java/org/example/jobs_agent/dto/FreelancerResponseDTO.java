package org.example.jobs_agent.dto;

import lombok.Data;
import org.example.jobs_agent.dto.ProjectDTO;

import java.util.List;

@Data
public class FreelancerResponseDTO {
    private List<ProjectDTO> projects;

    public FreelancerResponseDTO(List<ProjectDTO> projects) {
        this.projects = projects;
    }
}



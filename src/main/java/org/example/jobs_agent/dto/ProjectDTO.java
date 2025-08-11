package org.example.jobs_agent.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO{
    private String title;
    private String description;
    private String platform;
    private String url;
    private String budget;
    
    private String datePosted;

    private String deadline;
}

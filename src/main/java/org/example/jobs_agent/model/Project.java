package org.example.jobs_agent.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private String title;
    private String description;
    private String platform;
    private String url;
    private String budget;

    @JsonProperty("date_posted")
    private String datePosted;

    private String deadline;
}
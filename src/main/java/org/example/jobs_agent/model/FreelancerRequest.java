package org.example.jobs_agent.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

@Data
@JsonClassDescription("Requête pour récupérer des projets freelance")
public class FreelancerRequest {

    @JsonProperty(required = true)
    @JsonPropertyDescription("Nombre de projets à récupérer (entre 1 et 25)")
    private int count = 3;

    public FreelancerRequest() {}

    public FreelancerRequest(int count) {
        this.count = Math.min(Math.max(count, 1), 25); // Entre 1 et 25
    }
}
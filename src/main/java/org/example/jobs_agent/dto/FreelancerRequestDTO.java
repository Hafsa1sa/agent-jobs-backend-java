package org.example.jobs_agent.dto;


import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

@Data
@JsonClassDescription("Requête pour récupérer des projets freelance")
public class FreelancerRequestDTO {

    @JsonProperty(required = true)
    @JsonPropertyDescription("Nombre de projets à récupérer (entre 1 et 25)")
    private int count = 3;

    public FreelancerRequestDTO() {}

    public FreelancerRequestDTO(int count) {
        this.count = Math.min(Math.max(count, 1), 25);
    }
}

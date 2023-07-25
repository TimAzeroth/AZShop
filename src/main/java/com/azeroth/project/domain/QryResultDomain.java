package com.azeroth.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QryResultDomain {

    int count;

    String status;

    @JsonIgnore
    public String getToday() { return "오늘"; }
}

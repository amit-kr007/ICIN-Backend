package com.assessment.icinbank.checkbook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckBookRequest {

    @Enumerated(EnumType.STRING)
    private CheckBookType accountType;
    private Long userId;
}

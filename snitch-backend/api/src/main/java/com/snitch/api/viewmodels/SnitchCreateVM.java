package com.snitch.api.viewmodels;

import lombok.Data;

import java.util.List;

@Data
public class SnitchCreateVM {
    private Long id;
    private Long snitchId;
    private Long victimId;
    private Long typeId;
    private List<Long> bonusIds;
}

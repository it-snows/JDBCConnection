package com.company.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor //will give parameterless constructor
public class County {

    private String name;

    private int id;
}
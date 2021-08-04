package com.IBA.SERVER.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class bookListsTableDTO {

    private int Book_ID;
    private String Title;
    private String Author;
    private String Publisher;
    private int Publication_Year;
    private double Thickness;
    private String Location;
}

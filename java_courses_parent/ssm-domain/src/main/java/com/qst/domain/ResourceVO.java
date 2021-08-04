package com.qst.domain;

import lombok.Data;

@Data
public class ResourceVO {

    private Integer currentPage;
    private Integer pageSize;

    private String name;
    private String url;
    private Integer categoryId;
}

package com.qst.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo {

    private Integer currentPage;

    private Integer pageSize;

    private Integer id;

}

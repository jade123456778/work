package com.ibi.parking.vo;

import com.ibi.parking.entity.ChargingRules;
import com.ibi.parking.entity.ChargingRulesItem;
import com.itool.db.VO.R_BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 单独用来返回给前台的实体，可以根据不同的情况定义多个 枚举 字段， 尽量拆分为 index 和name字段 例如 sex 字段是个枚举， 那就返回 Integer sex 和 String
 * sexName 两个字段， 或者 Integer sexIndex 也行
 */
@Data
@Schema(description = "用于返回对收费规则查询的内容")
public class R_ChargingRules extends R_BaseVo<R_ChargingRules, ChargingRules> {
   
    @Schema(description = "记录id")
    private Long Id;
    
    @Schema(description = "规则名称")    
    private String ruleName;    
        
    @Schema(description = "免费时长")    
    private Integer freeDuration;

    @Schema(description = "对应时间段信息")
    private List<ChargingRulesItem> rulesItems;

    /**
     * 一页的记录个数
     */
    private Integer size;
    /**
     * 当前页,从1开始
     */
    private Integer currentPage;
    /**
     * 当前页的记录个数
     */
    private Integer numberOfElements;
    /**
     * 总共多少页
     */
    private Integer totalPages;
    /**
     * 总共多少记录
     */
    private Long totalElements;

}

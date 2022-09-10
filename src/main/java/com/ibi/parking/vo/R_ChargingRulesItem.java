package com.ibi.parking.vo;

import com.ibi.parking.entity.ChargingRulesItem;
import com.itool.db.VO.R_BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 单独用来返回给前台的实体，可以根据不同的情况定义多个 枚举 字段， 尽量拆分为 index 和name字段 例如 sex 字段是个枚举， 那就返回 Integer sex 和 String
 * sexName 两个字段， 或者 Integer sexIndex 也行
 */
@Data
@Schema(description = "用于返回对规则明细查询的内容")
public class R_ChargingRulesItem extends R_BaseVo<R_ChargingRulesItem, ChargingRulesItem> {
   
    @Schema(description = "记录id")
    private Integer Id;
    
    @Schema(description = "规则ID")    
    private Integer ruleId;    
        
    @Schema(description = "开始时间")    
    private String startTime;    
        
    @Schema(description = "结束时间")    
    private String endTime;    
        
    @Schema(description = "收费金额")    
    private Integer amount;    
        
    
}

package com.ibi.parking.vo;

import com.ibi.parking.entity.OtherRulesItem;
import com.itool.db.VO.R_BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 单独用来返回给前台的实体，可以根据不同的情况定义多个 枚举 字段， 尽量拆分为 index 和name字段 例如 sex 字段是个枚举， 那就返回 Integer sex 和 String
 * sexName 两个字段， 或者 Integer sexIndex 也行
 */
@Data
@Schema(description = "用于返回对其他规则明细查询的内容")
public class R_OtherRulesItem extends R_BaseVo<R_OtherRulesItem, OtherRulesItem> {
   
    @Schema(description = "记录id")
    private Integer Id;
    
    @Schema(description = "规则ID")    
    private Integer ruleId;    
        
    @Schema(description = "数量")    
    private Integer quantity;    
        
    @Schema(description = "金额")    
    private Integer amount;    
        
    
}

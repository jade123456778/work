package com.ibi.parking.vo;

import com.ibi.parking.entity.ChargingRulesItem;
import com.itool.db.VO.E_BaseVo;
import com.itool.util.validator.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "接收保存和编辑规则明细表的参数")
public class E_ChargingRulesItem extends E_BaseVo<E_ChargingRulesItem, ChargingRulesItem> {
    
  @Schema(description = "记录id更新时不能为空")
  @NotNull(groups = Update.class, message = "id在更新时不能为空")
  private Long Id;

  @Schema(description = "规则ID")  
  @NotNull(message = "规则ID 不能为空")
  private Integer ruleId;  
  
  @Schema(description = "开始时间")  
  @NotBlank(message = "开始时间 不能为空")
  private String startTime;  
  
  @Schema(description = "结束时间")  
  @NotBlank(message = "结束时间 不能为空")
  private String endTime;  
  
  @Schema(description = "收费金额")  
  @NotNull(message = "收费金额 不能为空")
  private Integer amount;  
  
  

  /**
   * @description: 重写getEntity方法, 忽略基础字段赋值
   * @author: qkcode
   * @createDate: 2022/1/12 14:55
   */
  @Override
  public ChargingRulesItem getEntity(ChargingRulesItem chargingRulesItem) {
    ChargingRulesItem entity = this.getEntity(chargingRulesItem, "createUser", "createDept", "createTime", "updateUser", "updateTime", "status");
    return entity;
  }

}

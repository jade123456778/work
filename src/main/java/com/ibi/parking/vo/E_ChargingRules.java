package com.ibi.parking.vo;

import com.ibi.parking.entity.ChargingRules;
import com.itool.db.VO.E_BaseVo;
import com.itool.util.validator.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "接收保存和编辑收费规则表的参数")
public class E_ChargingRules extends E_BaseVo<E_ChargingRules, ChargingRules> {
    
  @Schema(description = "记录id更新时不能为空")
  @NotNull(groups = Update.class, message = "id在更新时不能为空")
  private Long Id;

  @Schema(description = "规则名称")  
  @NotBlank(message = "规则名称 不能为空")
  private String ruleName;  
  
  @Schema(description = "免费时长")  
  @NotNull(message = "免费时长 不能为空")
  private Integer freeDuration;  
  
  

  /**
   * @description: 重写getEntity方法, 忽略基础字段赋值
   * @author: qkcode
   * @createDate: 2022/1/12 14:55
   */
  @Override
  public ChargingRules getEntity(ChargingRules chargingRules) {
    ChargingRules entity = this.getEntity(chargingRules, "createUser", "createDept", "createTime", "updateUser", "updateTime", "status");
    return entity;
  }

}

package com.ibi.parking.vo;

import com.ibi.parking.entity.OtherRulesItem;
import com.itool.db.VO.E_BaseVo;
import com.itool.util.validator.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "接收保存和编辑其他规则明细表的参数")
public class E_OtherRulesItem extends E_BaseVo<E_OtherRulesItem, OtherRulesItem> {
    
  @Schema(description = "记录id更新时不能为空")
  @NotNull(groups = Update.class, message = "id在更新时不能为空")
  private Long Id;

  @Schema(description = "规则ID")  
  @NotNull(message = "规则ID 不能为空")
  private Integer ruleId;  
  
  @Schema(description = "数量")  
  @NotNull(message = "数量 不能为空")
  private Integer quantity;  
  
  @Schema(description = "金额")  
  @NotNull(message = "金额 不能为空")
  private Integer amount;  
  
  

  /**
   * @description: 重写getEntity方法, 忽略基础字段赋值
   * @author: qkcode
   * @createDate: 2022/1/12 14:55
   */
  @Override
  public OtherRulesItem getEntity(OtherRulesItem otherRulesItem) {
    OtherRulesItem entity = this.getEntity(otherRulesItem, "createUser", "createDept", "createTime", "updateUser", "updateTime", "status");
    return entity;
  }

}

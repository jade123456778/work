package com.ibi.parking.vo;

import com.ibi.parking.entity.OtherRules;
import com.itool.db.VO.E_BaseVo;
import com.itool.util.validator.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Schema(description = "接收保存和编辑其他规则配置表的参数")
public class E_OtherRules extends E_BaseVo<E_OtherRules, OtherRules> {
    
  @Schema(description = "记录id更新时不能为空")
  @NotNull(groups = Update.class, message = "id在更新时不能为空")
  private Long Id;

  @Schema(description = "车位数量")  
  @NotNull(message = "车位数量 不能为空")
  private Integer parkingNum;


  @Schema(description = "其他规则")
  @NotNull(message = "")
  private List<E_OtherRulesItem> otherRulesItems;
  

  /**
   * @description: 重写getEntity方法, 忽略基础字段赋值
   * @author: qkcode
   * @createDate: 2022/1/12 14:55
   */
  @Override
  public OtherRules getEntity(OtherRules otherRules) {
    OtherRules entity = this.getEntity(otherRules, "createUser", "createDept", "createTime", "updateUser", "updateTime", "status");
    return entity;
  }

}

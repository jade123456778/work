package com.ibi.parking.vo;

import com.ibi.parking.entity.ChargingRules;
import com.ibi.parking.entity.QChargingRules;
import com.itool.db.VO.S_BaseVo_Q;
import com.querydsl.jpa.impl.JPAQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.function.Consumer;

/**
 * 实体对应的查询vo，主要用来传递检索条件
 * 如查询方法，比较多，此vo可以根据情况来 建立多个查询vo， 每个vo 用来处理 一种或几种的查询情况
 * <p>
 * 可根据情况 继承 IbiQuery 用以接收分页参数
 */
@Data
@Schema(title = "收费规则表检索实体", description = "用来传递收费规则表的检索条件")
public class S_ChargingRules extends S_BaseVo_Q<ChargingRules, QChargingRules> {


//   @Schema(description = "真实姓名")
//   private String realName;
  
  
  //获得分页查询条件的条件
  @Override
  public Consumer<JPAQuery<ChargingRules>> initWhere() {
    QChargingRules qchargingRules = QChargingRules.chargingRules;
    //拼接查询条件
    return query -> {
    //   if (StringUtil.isNotBlank(realName)) {
    //     query.where(qChargingRules.realName.like("%" + realName + "%"));
    //   }
    };
  }
}

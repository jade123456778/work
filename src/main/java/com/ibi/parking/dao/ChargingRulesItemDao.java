package com.ibi.parking.dao;

import com.ibi.parking.entity.ChargingRulesItem;
import com.itool.db.dao.BaseDaoI;

import java.util.List;

/**
 * @description: 规则明细 Dao
 * @author: qkCode
 * @createDate: 2022/1/10 12:00 
 */
public interface ChargingRulesItemDao extends BaseDaoI<ChargingRulesItem, Long> {

    void deleteByRuleId(Long id);

  List<ChargingRulesItem> getByRuleId(Integer ruleId);
}

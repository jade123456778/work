package com.ibi.parking.service;

import com.ibi.parking.entity.ChargingRules;
import com.ibi.parking.vo.E_ChargingRules;
import com.ibi.parking.vo.R_ChargingRules;
import com.itool.db.service.IbiServiceI;

import java.util.List;

/**
 * @description: 收费规则接口
 * @author: qkCode
 * @createDate: 2022/1/10 12:00 
 */
public interface IChargingRulesService extends IbiServiceI<ChargingRules, Long> {


  List<R_ChargingRules> findPage(Integer currentPage, Integer pageSize);

    R_ChargingRules getRuleById(Long id);

  boolean updateRule(E_ChargingRules request);

  boolean add(E_ChargingRules chargingRules);

  List<R_ChargingRules> findByKeywords(String keyword);

  boolean remove(Long id);
}

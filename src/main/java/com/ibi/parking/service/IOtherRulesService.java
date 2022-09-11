package com.ibi.parking.service;

import com.ibi.parking.entity.OtherRules;
import com.ibi.parking.vo.E_OtherRules;
import com.itool.db.service.IbiServiceI;

/**
 * @description: 其他规则配置接口
 * @author: qkCode
 * @createDate: 2022/1/10 12:00 
 */
public interface IOtherRulesService extends IbiServiceI<OtherRules, Long> {

  boolean add(E_OtherRules otherRules);
}

package com.ibi.parking.serviceImpl;

import com.ibi.parking.entity.OtherRules;
import com.ibi.parking.service.IOtherRulesService;
import com.ibi.parking.vo.E_OtherRules;
import com.itool.db.service.IbiServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 其他规则配置实现类
 * @author: qkCode
 * @createDate: 2022/1/10 12:00 
 */
@Slf4j
@Service
public class OtherRulesService extends IbiServiceImpl<OtherRules, Long> implements IOtherRulesService {
  //private QOtherRules qotherRules = QOtherRules.otherRules;

  @Override
  public boolean add(E_OtherRules request) {
    return false;
  }
    

}

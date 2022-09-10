package com.ibi.parking.serviceImpl;

import com.ibi.parking.entity.ChargingRules;
import com.ibi.parking.service.IChargingRulesService;
import com.itool.db.service.IbiServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 收费规则实现类
 * @author: qkCode
 * @createDate: 2022/1/10 12:00 
 */
@Slf4j
@Service
public class ChargingRulesService extends IbiServiceImpl<ChargingRules, Long> implements IChargingRulesService {
    //private QChargingRules qchargingRules = QChargingRules.chargingRules;
    

}

package com.ibi.parking.entity;

import com.itool.db.bean.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;



/**
 * @description: 规则明细
 * @author: qkCode
 * @createDate: 2022/1/10 12:00 
 */
@Getter
@Setter
@Entity
@javax.persistence.Table(name = "charging_rules_item")
@Table(appliesTo = "charging_rules_item", comment = "规则明细表")
public class ChargingRulesItem extends BaseEntity {
            
    @Column(name = "rule_id", columnDefinition = "int(11) comment &#39;规则ID&#39;")
    private Integer ruleId;
                
    @Column(name = "start_time", columnDefinition = "varchar(255) comment &#39;开始时间&#39;")
    private String startTime;
                
    @Column(name = "end_time", columnDefinition = "varchar(255) comment &#39;结束时间&#39;")
    private String endTime;
                
    @Column(name = "amount", columnDefinition = "int(11) comment &#39;收费金额&#39;")
    private Integer amount;
            
    
}

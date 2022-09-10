package com.ibi.parking.entity;

import com.itool.db.bean.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;



/**
 * @description: 收费规则
 * @author: qkCode
 * @createDate: 2022/1/10 12:00 
 */
@Getter
@Setter
@Entity
@javax.persistence.Table(name = "charging_rules")
@Table(appliesTo = "charging_rules", comment = "收费规则表")
public class ChargingRules extends BaseEntity {
            
    @Column(name = "rule_name", columnDefinition = "varchar(255) comment &#39;规则名称&#39;")
    private String ruleName;
                
    @Column(name = "free_duration", columnDefinition = "int(11) comment &#39;免费时长&#39;")
    private Integer freeDuration;
            
    
}

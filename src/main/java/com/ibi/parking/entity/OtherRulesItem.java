package com.ibi.parking.entity;

import com.itool.db.bean.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;



/**
 * @description: 其他规则明细
 * @author: qkCode
 * @createDate: 2022/1/10 12:00 
 */
@Getter
@Setter
@Entity
@javax.persistence.Table(name = "other_rules_item")
@Table(appliesTo = "other_rules_item", comment = "其他规则明细表")
public class OtherRulesItem extends BaseEntity {
            
    @Column(name = "rule_id", columnDefinition = "int(11) comment &#39;规则ID&#39;")
    private Integer ruleId;
                
    @Column(name = "quantity", columnDefinition = "int(11) comment &#39;数量&#39;")
    private Integer quantity;
                
    @Column(name = "amount", columnDefinition = "int(11) comment &#39;金额&#39;")
    private Integer amount;
            
    
}

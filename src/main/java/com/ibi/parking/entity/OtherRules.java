package com.ibi.parking.entity;

import com.itool.db.bean.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;



/**
 * @description: 其他规则配置
 * @author: qkCode
 * @createDate: 2022/1/10 12:00 
 */
@Getter
@Setter
@Entity
@javax.persistence.Table(name = "other_rules")
@Table(appliesTo = "other_rules", comment = "其他规则配置表")
public class OtherRules extends BaseEntity {
            
    @Column(name = "parking_num", columnDefinition = "int(11) comment &#39;车位数量&#39;")
    private Integer parkingNum;
            
    
}

package com.ibi.parking.dao;

import com.ibi.parking.entity.ChargingRules;
import com.itool.db.dao.BaseDaoI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @description: 收费规则 Dao
 * @author: qkCode
 * @createDate: 2022/1/10 12:00 
 */
public interface ChargingRulesDao extends BaseDaoI<ChargingRules, Long>, JpaRepository<ChargingRules, Long>, JpaSpecificationExecutor<ChargingRules> {


  @Query(value = "select max(id) from charging_rules", nativeQuery = true)
  long getMaxId();

  @Query(value = "select * from charging_rules where rule_name like concat('%', ?1, '%') ", nativeQuery = true)
  List<ChargingRules> findByKeyWord(String keyword);

  @Modifying
//  @Transactional
  @Query(value = "UPDATE charging_rules set delete_flag = 1 WHERE id = ?1 ",nativeQuery = true)
  Integer remove(Long id);
}

package com.ibi.parking.serviceImpl;

import com.ibi.parking.dao.ChargingRulesDao;
import com.ibi.parking.dao.ChargingRulesItemDao;
import com.ibi.parking.entity.ChargingRules;
import com.ibi.parking.entity.ChargingRulesItem;
import com.ibi.parking.service.IChargingRulesService;
import com.ibi.parking.vo.E_ChargingRules;
import com.ibi.parking.vo.E_ChargingRulesItem;
import com.ibi.parking.vo.R_ChargingRules;
import com.itool.db.service.IbiServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;


/**
 * @description: 收费规则实现类
 * @author: qkCode
 * @createDate: 2022/1/10 12:00
 */
@Slf4j
@Service
public class ChargingRulesService extends IbiServiceImpl<ChargingRules, Long> implements IChargingRulesService {

  @Autowired
  private ChargingRulesDao chargingRulesDao;
  @Autowired
  private ChargingRulesItemDao chargingRulesItemDao;


  @Override
  public List<R_ChargingRules> findPage(Integer currentPage, Integer pageSize) {
    PageRequest of = PageRequest.of(currentPage, pageSize, Sort.by("id").descending());
    Page<ChargingRules> all = chargingRulesDao.findAll(of);
    List<ChargingRules> rules = all.getContent();
    List<R_ChargingRules> resultList = new ArrayList<>();
    rules.stream().filter(rule -> rule.getDeleteFlag() == 0)
            .forEach(rule -> {
              Long ruleId = rule.getId();
              List<ChargingRulesItem> byRuleId = chargingRulesItemDao.getByRuleId(ruleId.intValue());
              R_ChargingRules r_chargingRules = builerRChargingRule(rule, byRuleId, all);
              resultList.add(r_chargingRules);
            });

    return resultList;
  }

  @Override
  public boolean add(E_ChargingRules request) {
    Long id = chargingRulesDao.getMaxId();
    Long newId = id + 1;

    try {
      ChargingRules chargingRules = new ChargingRules();
      chargingRules.setRuleName(request.getRuleName());
      chargingRules.setId(newId);
      chargingRules.setFreeDuration(request.getFreeDuration());
      chargingRulesDao.save(chargingRules);


      List<E_ChargingRulesItem> chargingRulesItemsRequest = request.getChargingRulesItems();
      List<ChargingRulesItem> chargingRulesItems = new ArrayList<>();

      chargingRulesItemsRequest.forEach(item -> {
        //todo 遍历，将数据封装到实体类中
        // 拿一个空瓶子
        ChargingRulesItem chargingRulesItem = new ChargingRulesItem();
        // 倒水
        chargingRulesItem.setRuleId(newId.intValue());
        chargingRulesItem.setCreateTime(new Date());
        chargingRulesItem.setStartTime(item.getStartTime());
        chargingRulesItem.setEndTime(item.getEndTime());
        chargingRulesItem.setAmount(item.getAmount());
        // 将瓶子放入箱子
        //todo 将封装好的实体类加入集合
        chargingRulesItems.add(chargingRulesItem);
      });
      //todo 将集合保存入库
      chargingRulesItemDao.saveAll(chargingRulesItems);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public List<R_ChargingRules> findByKeywords(String keyword) {
    List<ChargingRules> rules = chargingRulesDao.findByKeyWord(keyword);
    List<R_ChargingRules> resultList = new ArrayList<>();
    rules.stream().filter(rule ->rule.getDeleteFlag() == 0)
            .forEach(rule ->{
              Long ruleId = rule.getId();
              List<ChargingRulesItem> byRuleId = chargingRulesItemDao.getByRuleId(ruleId.intValue());
              R_ChargingRules r_chargingRules = new R_ChargingRules();
              r_chargingRules.setId(ruleId);
              r_chargingRules.setRuleName(rule.getRuleName());
              r_chargingRules.setFreeDuration(rule.getFreeDuration());
              r_chargingRules.setRulesItems(byRuleId);
              resultList.add(r_chargingRules);
            });
    return resultList;
  }

  @Override
  public boolean remove(Long id) {

    try {
      Integer remove = chargingRulesDao.remove(id);
      if (remove > 0) {
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }
//  Hibernate: select chargingru0_.id as id1_0_, chargingru0_.delete_flag as delete_f2_0_, chargingru0_.free_duration as free_dur3_0_, chargingru0_.rule_name as rule_nam4_0_, chargingru0_.create_user as create_u5_0_, chargingru0_.create_dept as create_d6_0_, chargingru0_.create_time as create_t7_0_, chargingru0_.update_user as update_u8_0_, chargingru0_.update_time as update_t9_0_, chargingru0_.company_id as company10_0_, chargingru0_.status as status11_0_ from charging_rules chargingru0_ where chargingru0_.rule_name=?

  private R_ChargingRules builerRChargingRule(ChargingRules rule, List<ChargingRulesItem> byRuleId, Page all) {
    R_ChargingRules r_chargingRules = new R_ChargingRules();
    r_chargingRules.setRuleName(rule.getRuleName());
    r_chargingRules.setRulesItems(byRuleId);
    r_chargingRules.setId(rule.getId());
    r_chargingRules.setFreeDuration(rule.getFreeDuration());
    r_chargingRules.setCurrentPage(all.getNumber() + 1);
    r_chargingRules.setSize(all.getSize());
    r_chargingRules.setTotalElements(all.getTotalElements());
    r_chargingRules.setTotalPages(all.getTotalPages());
    return r_chargingRules;
  }

  @Override
  public R_ChargingRules getRuleById(Long id) {
    //封装结果的对象
    R_ChargingRules result = new R_ChargingRules();

    //查询第一个表
    Optional<ChargingRules> byId = chargingRulesDao.findById(id);
    if (byId.isPresent()) {
      ChargingRules chargingRules = byId.get();
      result.setId(chargingRules.getId());
      result.setRuleName(chargingRules.getRuleName());
      result.setFreeDuration(chargingRules.getFreeDuration());
    }

    //查询第二个表
    Optional<ChargingRules> item = chargingRulesDao.findById(id);
    //result.setList(item)
    return result;
  }

  @Override
  public boolean updateRule(E_ChargingRules request) {
    //trycatch
    try{
      //创建rule表实体
      ChargingRules chargingRules = new ChargingRules();
      //将request中的数据封装进实体
      chargingRules.setRuleName(request.getRuleName());
      chargingRules.setFreeDuration(request.getFreeDuration());
      chargingRules.setId(request.getId());
      chargingRulesDao.save(chargingRules);

      //创建item实体
      chargingRulesItemDao.deleteByRuleId(request.getId());

      List<ChargingRulesItem> chargingRulesItems = new ArrayList<>();
      //将request中的数据封装进实体
      List<E_ChargingRulesItem> e_chargingRulesItems = request.getE_chargingRulesItems();
      e_chargingRulesItems.forEach(item -> {
        ChargingRulesItem chargingRulesItem = new ChargingRulesItem();
        chargingRulesItem.setRuleId(request.getId().intValue());
        chargingRulesItem.setStartTime(item.getStartTime());
        //todo set其他需要的数据

        //装箱
        chargingRulesItems.add(chargingRulesItem);
      });
      //落库
      chargingRulesItemDao.saveAll(chargingRulesItems);
      return true;
    }catch (Exception e){
      log.debug("{}", e);
      return false;
    }
  }
  //private QChargingRules qchargingRules = QChargingRules.chargingRules;


}

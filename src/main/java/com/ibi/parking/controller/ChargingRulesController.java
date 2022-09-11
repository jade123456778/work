package com.ibi.parking.controller;

import com.ibi.parking.entity.ChargingRules;
import com.ibi.parking.service.IChargingRulesItemService;
import com.ibi.parking.service.IChargingRulesService;
import com.ibi.parking.vo.E_ChargingRules;
import com.ibi.parking.vo.R_ChargingRules;
import com.ibi.parking.vo.S_ChargingRules;
import com.itool.db.VO.R_BaseVo;
import com.itool.db.support.IbiPage;
import com.itool.util.api.R;
import com.itool.util.validator.group.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;


@Slf4j
@Validated
@RestController
@RequestMapping("/chargingRules")
@Api(tags = "收费规则管理")
public class ChargingRulesController {

    @Autowired
    private IChargingRulesService chargingRulesService;
    @Autowired
    private IChargingRulesItemService chargingRulesItemService;

    @Operation(summary = "根据id获取单个记录", description = "根据主键id获取单个记录")
    @GetMapping("/getById")
    public R<R_ChargingRules> getById(@ApiParam("主键id") @NotNull(message = "主键id不能为空") Long id) {
      ChargingRules chargingRules = chargingRulesService.getById(id);
      if (Objects.isNull(chargingRules)) {
        return R.fail("没有对应的收费规则数据");
      }
      R_ChargingRules rChargingRules = R_BaseVo.buildEntity(chargingRules, R_ChargingRules.class, getConsumer());
      return R.data(rChargingRules);
    }

    @GetMapping("/getRuleById")
    public R<R_ChargingRules> getRuleById(@RequestParam("id") Long id) {
        R_ChargingRules rChargingRules = chargingRulesService.getRuleById(id);
        return R.data(rChargingRules);
    }

    @PutMapping("/update")
    public Boolean changeById(@RequestBody E_ChargingRules request) {
      boolean result = chargingRulesService.updateRule(request);
      return result;
    }
    @Operation(summary = "模糊查询")
    @GetMapping("/findByKeywords")
    public List<R_ChargingRules> findByKeywords(@RequestParam("keyword") String keyword) {
      List<R_ChargingRules> chargingRules = chargingRulesService.findByKeywords(keyword);
      return  chargingRules;

    }


    @Operation(summary = "查询分页列表", description = "查询分页列表")
    @GetMapping("/page")
    public R<IbiPage<R_ChargingRules>> page(S_ChargingRules sChargingRules) {
      IbiPage<ChargingRules> page = chargingRulesService.page(sChargingRules);
      IbiPage<R_ChargingRules> rChargingRulesIbiPage = R_BaseVo.buildEntity(page, R_ChargingRules.class, getConsumer());
      return R.data(rChargingRulesIbiPage);
    }

    @GetMapping("/findPage")
    public List<R_ChargingRules> findPage(@RequestParam("current") Integer currentPage, @RequestParam("size") Integer pageSize) {
      currentPage -= 1;
      List<R_ChargingRules> page = chargingRulesService.findPage(currentPage, pageSize);
      return page;
    }
  
    
    @Operation(summary = "新增", description = "新增记录")
    @PostMapping("/save")
    public R<Long> save(@Validated @RequestBody E_ChargingRules eChargingRules) {
      ChargingRules save = chargingRulesService.save(eChargingRules.getEntity());
      return R.data(save.getId());
    }
  
//    @Operation(summary = "更新", description = "更新记录")
//    @PostMapping("/update")
//    public R<Long> update(@Validated(Update.class) @RequestBody E_ChargingRules eChargingRules) {
//      ChargingRules chargingRules = chargingRulesService.getById(eChargingRules.getId());
//      if (Objects.isNull(chargingRules)) {
//        return R.fail("没有对应的收费规则数据");
//      }
//      eChargingRules.getEntity(chargingRules);
//      ChargingRules save = chargingRulesService.save(chargingRules);
//      return R.data(save.getId());
//    }
  
  
    @Operation(summary = "修改状态", description = "修改状态")
    @GetMapping("/changeStatus")
    public R changeStatus(@ApiParam("主键id") @NotNull(message = "主键id不能为空") Long id,
                          @ApiParam("状态：0停用，1启用") @NotNull(message = "状态不能为空") @Range(max = 1, min = 0, message = "状态值不在范围内") Integer status) {
      ChargingRules chargingRules = chargingRulesService.getById(id);
      if (Objects.isNull(chargingRules)) {
        return R.fail("未找到此记录");
      }
      chargingRules.setStatus(status);
      chargingRulesService.update(chargingRules);
      return R.ok("操作成功");
    }

    @PostMapping("/testAdd")
    public Boolean add(@RequestBody E_ChargingRules chargingRules) {
      boolean result = chargingRulesService.add(chargingRules);
      return result;
    }
  
    private Consumer<R_ChargingRules> getConsumer(){
      return r -> {
      //   if (r.getAppType() != null){
      //     r.setAppTypeName(EnumUtil.valueOfByName(EnumChargingRulesType.class,r.getAppType()));
      //   }
      };
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("toRemove")
    public Boolean remove(@RequestParam Long id){
      if (id == null) {
        return false;
      }
      boolean result = chargingRulesService.remove(id);
      return result;
    }

}

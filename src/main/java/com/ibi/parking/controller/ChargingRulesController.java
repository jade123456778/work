package com.ibi.parking.controller;

import com.ibi.parking.entity.ChargingRules;
import com.ibi.parking.service.IChargingRulesService;
import com.ibi.parking.vo.E_ChargingRules;
import com.ibi.parking.vo.R_ChargingRules;
import com.ibi.parking.vo.S_ChargingRules;
import com.itool.db.VO.R_BaseVo;
import com.itool.db.support.IbiPage;
import com.itool.util.api.R;
import com.itool.util.validator.group.Update;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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

    @Operation(summary = "查询分页列表", description = "查询分页列表")
    @GetMapping("/page")
    public R<IbiPage<R_ChargingRules>> page(S_ChargingRules sChargingRules) {
      IbiPage<ChargingRules> page = chargingRulesService.page(sChargingRules);
      IbiPage<R_ChargingRules> rChargingRulesIbiPage = R_BaseVo.buildEntity(page, R_ChargingRules.class, getConsumer());
      return R.data(rChargingRulesIbiPage);
    }
  
    
    @Operation(summary = "新增", description = "新增记录")
    @PostMapping("/save")
    public R<Long> save(@Validated @RequestBody E_ChargingRules eChargingRules) {
      ChargingRules save = chargingRulesService.save(eChargingRules.getEntity());
      return R.data(save.getId());
    }
  
    @Operation(summary = "更新", description = "更新记录")
    @PostMapping("/update")
    public R<Long> update(@Validated(Update.class) @RequestBody E_ChargingRules eChargingRules) {
      ChargingRules chargingRules = chargingRulesService.getById(eChargingRules.getId());
      if (Objects.isNull(chargingRules)) {
        return R.fail("没有对应的收费规则数据");
      }
      eChargingRules.getEntity(chargingRules);
      ChargingRules save = chargingRulesService.save(chargingRules);
      return R.data(save.getId());
    }
  
  
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
  
    private Consumer<R_ChargingRules> getConsumer(){
      return r -> {
      //   if (r.getAppType() != null){
      //     r.setAppTypeName(EnumUtil.valueOfByName(EnumChargingRulesType.class,r.getAppType()));
      //   }
      };
    }
}

package com.ibi.parking.controller;

import com.ibi.parking.entity.ChargingRulesItem;
import com.ibi.parking.service.IChargingRulesItemService;
import com.ibi.parking.vo.E_ChargingRulesItem;
import com.ibi.parking.vo.R_ChargingRulesItem;
import com.ibi.parking.vo.S_ChargingRulesItem;
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
import java.util.Objects;
import java.util.function.Consumer;


@Slf4j
@Validated
@RestController
@RequestMapping("/chargingRulesItem")
@Api(tags = "规则明细管理")
public class ChargingRulesItemController {
  
    @Autowired
    private IChargingRulesItemService chargingRulesItemService;

    @Operation(summary = "根据id获取单个记录", description = "根据主键id获取单个记录")
    @GetMapping("/getById")
    public R<R_ChargingRulesItem> getById(@ApiParam("主键id") @NotNull(message = "主键id不能为空") Long id) {
      ChargingRulesItem chargingRulesItem = chargingRulesItemService.getById(id);
      if (Objects.isNull(chargingRulesItem)) {
        return R.fail("没有对应的规则明细数据");
      }
      R_ChargingRulesItem rChargingRulesItem = R_BaseVo.buildEntity(chargingRulesItem, R_ChargingRulesItem.class, getConsumer());
      return R.data(rChargingRulesItem);
    }

    @Operation(summary = "查询分页列表", description = "查询分页列表")
    @GetMapping("/page")
    public R<IbiPage<R_ChargingRulesItem>> page(S_ChargingRulesItem sChargingRulesItem) {
      IbiPage<ChargingRulesItem> page = chargingRulesItemService.page(sChargingRulesItem);
      IbiPage<R_ChargingRulesItem> rChargingRulesItemIbiPage = R_BaseVo.buildEntity(page, R_ChargingRulesItem.class, getConsumer());
      return R.data(rChargingRulesItemIbiPage);
    }
  
    
    @Operation(summary = "新增", description = "新增记录")
    @PostMapping("/save")
    public R<Long> save(@Validated @RequestBody E_ChargingRulesItem eChargingRulesItem) {
      ChargingRulesItem save = chargingRulesItemService.save(eChargingRulesItem.getEntity());
      return R.data(save.getId());
    }
  
    @Operation(summary = "更新", description = "更新记录")
    @PostMapping("/update")
    public R<Long> update(@Validated(Update.class) @RequestBody E_ChargingRulesItem eChargingRulesItem) {
      ChargingRulesItem chargingRulesItem = chargingRulesItemService.getById(eChargingRulesItem.getId());
      if (Objects.isNull(chargingRulesItem)) {
        return R.fail("没有对应的规则明细数据");
      }
      eChargingRulesItem.getEntity(chargingRulesItem);
      ChargingRulesItem save = chargingRulesItemService.save(chargingRulesItem);
      return R.data(save.getId());
    }
  
  
    @Operation(summary = "修改状态", description = "修改状态")
    @GetMapping("/changeStatus")
    public R changeStatus(@ApiParam("主键id") @NotNull(message = "主键id不能为空") Long id,
                          @ApiParam("状态：0停用，1启用") @NotNull(message = "状态不能为空") @Range(max = 1, min = 0, message = "状态值不在范围内") Integer status) {
      ChargingRulesItem chargingRulesItem = chargingRulesItemService.getById(id);
      if (Objects.isNull(chargingRulesItem)) {
        return R.fail("未找到此记录");
      }
      chargingRulesItem.setStatus(status);
      chargingRulesItemService.update(chargingRulesItem);
      return R.ok("操作成功");
    }
  
    private Consumer<R_ChargingRulesItem> getConsumer(){
      return r -> {
      //   if (r.getAppType() != null){
      //     r.setAppTypeName(EnumUtil.valueOfByName(EnumChargingRulesItemType.class,r.getAppType()));
      //   }
      };
    }
}

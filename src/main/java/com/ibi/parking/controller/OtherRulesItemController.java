package com.ibi.parking.controller;

import com.ibi.parking.entity.OtherRulesItem;
import com.ibi.parking.service.IOtherRulesItemService;
import com.ibi.parking.vo.E_OtherRulesItem;
import com.ibi.parking.vo.R_OtherRulesItem;
import com.ibi.parking.vo.S_OtherRulesItem;
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
@RequestMapping("/otherRulesItem")
@Api(tags = "其他规则明细管理")
public class OtherRulesItemController {
  
    @Autowired
    private IOtherRulesItemService otherRulesItemService;

    @Operation(summary = "根据id获取单个记录", description = "根据主键id获取单个记录")
    @GetMapping("/getById")
    public R<R_OtherRulesItem> getById(@ApiParam("主键id") @NotNull(message = "主键id不能为空") Long id) {
      OtherRulesItem otherRulesItem = otherRulesItemService.getById(id);
      if (Objects.isNull(otherRulesItem)) {
        return R.fail("没有对应的其他规则明细数据");
      }
      R_OtherRulesItem rOtherRulesItem = R_BaseVo.buildEntity(otherRulesItem, R_OtherRulesItem.class, getConsumer());
      return R.data(rOtherRulesItem);
    }

    @Operation(summary = "查询分页列表", description = "查询分页列表")
    @GetMapping("/page")
    public R<IbiPage<R_OtherRulesItem>> page(S_OtherRulesItem sOtherRulesItem) {
      IbiPage<OtherRulesItem> page = otherRulesItemService.page(sOtherRulesItem);
      IbiPage<R_OtherRulesItem> rOtherRulesItemIbiPage = R_BaseVo.buildEntity(page, R_OtherRulesItem.class, getConsumer());
      return R.data(rOtherRulesItemIbiPage);
    }
  
    
    @Operation(summary = "新增", description = "新增记录")
    @PostMapping("/save")
    public R<Long> save(@Validated @RequestBody E_OtherRulesItem eOtherRulesItem) {
      OtherRulesItem save = otherRulesItemService.save(eOtherRulesItem.getEntity());
      return R.data(save.getId());
    }
  
    @Operation(summary = "更新", description = "更新记录")
    @PostMapping("/update")
    public R<Long> update(@Validated(Update.class) @RequestBody E_OtherRulesItem eOtherRulesItem) {
      OtherRulesItem otherRulesItem = otherRulesItemService.getById(eOtherRulesItem.getId());
      if (Objects.isNull(otherRulesItem)) {
        return R.fail("没有对应的其他规则明细数据");
      }
      eOtherRulesItem.getEntity(otherRulesItem);
      OtherRulesItem save = otherRulesItemService.save(otherRulesItem);
      return R.data(save.getId());
    }
  
  
    @Operation(summary = "修改状态", description = "修改状态")
    @GetMapping("/changeStatus")
    public R changeStatus(@ApiParam("主键id") @NotNull(message = "主键id不能为空") Long id,
                          @ApiParam("状态：0停用，1启用") @NotNull(message = "状态不能为空") @Range(max = 1, min = 0, message = "状态值不在范围内") Integer status) {
      OtherRulesItem otherRulesItem = otherRulesItemService.getById(id);
      if (Objects.isNull(otherRulesItem)) {
        return R.fail("未找到此记录");
      }
      otherRulesItem.setStatus(status);
      otherRulesItemService.update(otherRulesItem);
      return R.ok("操作成功");
    }
  
    private Consumer<R_OtherRulesItem> getConsumer(){
      return r -> {
      //   if (r.getAppType() != null){
      //     r.setAppTypeName(EnumUtil.valueOfByName(EnumOtherRulesItemType.class,r.getAppType()));
      //   }
      };
    }
}

package com.ibi.parking.controller;

import com.ibi.parking.entity.OtherRules;
import com.ibi.parking.service.IOtherRulesService;
import com.ibi.parking.vo.E_OtherRules;
import com.ibi.parking.vo.R_OtherRules;
import com.ibi.parking.vo.S_OtherRules;
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
@RequestMapping("/otherRules")
@Api(tags = "其他规则配置管理")
public class OtherRulesController {
  
    @Autowired
    private IOtherRulesService otherRulesService;

    @Operation(summary = "根据id获取单个记录", description = "根据主键id获取单个记录")
    @GetMapping("/getById")
    public R<R_OtherRules> getById(@ApiParam("主键id") @NotNull(message = "主键id不能为空") Long id) {
      OtherRules otherRules = otherRulesService.getById(id);
      if (Objects.isNull(otherRules)) {
        return R.fail("没有对应的其他规则配置数据");
      }
      R_OtherRules rOtherRules = R_BaseVo.buildEntity(otherRules, R_OtherRules.class, getConsumer());
      return R.data(rOtherRules);
    }

    @Operation(summary = "查询分页列表", description = "查询分页列表")
    @GetMapping("/page")
    public R<IbiPage<R_OtherRules>> page(S_OtherRules sOtherRules) {
      IbiPage<OtherRules> page = otherRulesService.page(sOtherRules);
      IbiPage<R_OtherRules> rOtherRulesIbiPage = R_BaseVo.buildEntity(page, R_OtherRules.class, getConsumer());
      return R.data(rOtherRulesIbiPage);
    }
  
    
    @Operation(summary = "新增", description = "新增记录")
    @PostMapping("/save")
    public R<Long> save(@Validated @RequestBody E_OtherRules eOtherRules) {
      OtherRules save = otherRulesService.save(eOtherRules.getEntity());
      return R.data(save.getId());
    }
  
    @Operation(summary = "更新", description = "更新记录")
    @PostMapping("/update")
    public R<Long> update(@Validated(Update.class) @RequestBody E_OtherRules eOtherRules) {
      OtherRules otherRules = otherRulesService.getById(eOtherRules.getId());
      if (Objects.isNull(otherRules)) {
        return R.fail("没有对应的其他规则配置数据");
      }
      eOtherRules.getEntity(otherRules);
      OtherRules save = otherRulesService.save(otherRules);
      return R.data(save.getId());
    }
  
  
    @Operation(summary = "修改状态", description = "修改状态")
    @GetMapping("/changeStatus")
    public R changeStatus(@ApiParam("主键id") @NotNull(message = "主键id不能为空") Long id,
                          @ApiParam("状态：0停用，1启用") @NotNull(message = "状态不能为空") @Range(max = 1, min = 0, message = "状态值不在范围内") Integer status) {
      OtherRules otherRules = otherRulesService.getById(id);
      if (Objects.isNull(otherRules)) {
        return R.fail("未找到此记录");
      }
      otherRules.setStatus(status);
      otherRulesService.update(otherRules);
      return R.ok("操作成功");
    }
  
    private Consumer<R_OtherRules> getConsumer(){
      return r -> {
      //   if (r.getAppType() != null){
      //     r.setAppTypeName(EnumUtil.valueOfByName(EnumOtherRulesType.class,r.getAppType()));
      //   }
      };
    }

  @PostMapping("/testAdd")
  public Boolean add(@RequestBody E_OtherRules otherRules) {
    boolean result = otherRulesService.add(otherRules);
    return result;
  }
}

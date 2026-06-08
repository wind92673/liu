package edu.nuc.light_system.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @ClassName SaveInfoExcelVO
 * @Description // TODO: excel导出类
 * @Author 19265
 * @Date 2026/5/9 16:14
 * @Version 1.0.0
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
@ColumnWidth(15)
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
public class SaveInfoExcelVO implements Serializable {

    @ExcelProperty(value = "自增ID", index = 0)
    @ColumnWidth(10)
    private Integer id;

    @ExcelProperty(value = "设备ID", index = 1)
    @ColumnWidth(30)
    private String deviceId;

    @ExcelProperty(value = "日期", index = 2)
    @DateTimeFormat("yyyy-MM-dd")
    @ColumnWidth(27)
    private Date currentTime;

    @ExcelProperty(value = "小时", index = 3)
    @ColumnWidth(10)
    private Integer currentHour;

    @ExcelProperty(value = "节电量(kWh)", index = 4)
    @ColumnWidth(18)
    private String power;

    @ExcelProperty(value = "节碳量(kg)", index = 5)
    @ColumnWidth(18)
    private String carbon;
}
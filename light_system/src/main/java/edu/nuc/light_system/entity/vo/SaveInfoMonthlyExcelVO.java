package edu.nuc.light_system.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.Data;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SaveInfoMonthlyExcelVO
 * @Description // TODO: 按月导出excel模板
 * @Author 19265
 * @Date 2026/5/11 18:55
 * @Version 1.0.0
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
@ColumnWidth(15)
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
public class SaveInfoMonthlyExcelVO implements Serializable {

    @ExcelProperty(value = "日期", index = 0)
    @DateTimeFormat("yyyy-MM-dd")
    @ColumnWidth(27)
    private Date date;

    @ExcelProperty(value = "节电量(kWh)", index = 1)
    @ColumnWidth(18)
    private String totalPower;

    @ExcelProperty(value = "节碳量(kg)", index = 2)
    @ColumnWidth(18)
    private String totalCarbon;
}

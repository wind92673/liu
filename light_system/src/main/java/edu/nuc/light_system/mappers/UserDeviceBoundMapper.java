package edu.nuc.light_system.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @Description:用户设备关系表Mapper
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public interface UserDeviceBoundMapper<T, P> extends BaseMapper {
	/**
	 * 根据Id查询
	 */
	T selectById(@Param("id") Integer id);

	/**
	 * 根据Id更新
	 */
	Integer updateById(@Param("bean") T t, @Param("id") Integer id);

	/**
	 * 根据Id删除
	 */
	Integer deleteById(@Param("id") Integer id);


}
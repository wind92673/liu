package edu.nuc.light_system.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @Description:处理结果表Mapper
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public interface ResultInfoMapper<T, P> extends BaseMapper {
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
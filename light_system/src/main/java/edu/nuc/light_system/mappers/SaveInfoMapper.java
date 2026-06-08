package edu.nuc.light_system.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:节能统计表Mapper
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public interface SaveInfoMapper<T, P> extends BaseMapper {
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

	/**
	 * 根据DeviceId查询
	 */
	T selectByDeviceId(@Param("deviceId") String deviceId);

	/**
	 * 根据DeviceId更新
	 */
	Integer updateByDeviceId(@Param("bean") T t, @Param("deviceId") String deviceId);

	/**
	 * 根据DeviceId、时间和小时更新
	 */
	Integer updateByDeviceIdAndTime(@Param("bean") T t, @Param("deviceId") String deviceId,
									@Param("currentTime") Date currentTime, @Param("currentHour") Integer currentHour);


	/**
	 * 按天查询所有记录
	 */
	List<T> selectListByDay(@Param("query") P p);

	/**
	 * 按月汇总查询（按天累加）
	 */
	List<Map<String, Object>> selectMonthlySummary(@Param("query") P p);

	/**
	 * 按年汇总查询（按月累加）
	 */
	List<Map<String, Object>> selectYearlySummary(@Param("query") P p);


}
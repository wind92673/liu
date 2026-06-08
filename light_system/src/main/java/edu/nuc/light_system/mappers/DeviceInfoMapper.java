package edu.nuc.light_system.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @Description:设备信息表Mapper
 * @author:Liu Wenhao
 * @date:2026/04/13
 */
public interface DeviceInfoMapper<T, P> extends BaseMapper {
	/**
	 * 根据DeviceId查询
	 */
	T selectByDeviceId(@Param("deviceId") String deviceId);

	/**
	 * 根据DeviceId更新
	 */
	Integer updateByDeviceId(@Param("bean") T t, @Param("deviceId") String deviceId);

	/**
	 * 根据DeviceId删除
	 */
	Integer deleteByDeviceId(@Param("deviceId") String deviceId);


}
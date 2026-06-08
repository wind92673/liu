package edu.nuc.light_system.service.impl;

import edu.nuc.light_system.entity.po.UserInfo;
import edu.nuc.light_system.entity.query.UserInfoQuery;
import edu.nuc.light_system.entity.query.SimplePage;
import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.entity.vo.UserVO;
import edu.nuc.light_system.enums.PageSize;
import edu.nuc.light_system.enums.RightEnum;
import edu.nuc.light_system.exception.BusinessException;
import edu.nuc.light_system.mappers.UserInfoMapper;
import edu.nuc.light_system.service.UserInfoService;
import edu.nuc.light_system.utils.CopyTools;
import edu.nuc.light_system.utils.JwtUtils;
import edu.nuc.light_system.utils.StringTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description:用户信息表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService{

	@Resource
	private UserInfoMapper<UserInfo, UserInfoQuery> userInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<UserInfo> findListByParam(UserInfoQuery query) {
		return this.userInfoMapper.selectList(query);
	}
	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(UserInfoQuery query) {
		return this.userInfoMapper.selectCount(query);
	}
	/**
	 * 分页查询
	 */
	public PaginationResultVO<UserInfo> findListByPage(UserInfoQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<UserInfo> list = this.findListByParam(query);
		PaginationResultVO<UserInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}
	/**
	 * 新增
	 */
	public Integer add(UserInfo bean) {
		return this.userInfoMapper.insert(bean);
	}
	/**
	 * 批量新增
	 */
	public Integer addBatch(List<UserInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userInfoMapper.insertBatch(listBean);
	}
	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<UserInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userInfoMapper.insertOrUpdateBatch(listBean);
	}
	/**
	 * 根据UserId查询
	 */
	public UserInfo getUserInfoByUserId(String userId) {
		return this.userInfoMapper.selectByUserId(userId);
	}
	/**
	 * 根据UserId更新
	 */
	public Integer updateUserInfoByUserId(UserInfo bean, String userId) {
		return this.userInfoMapper.updateByUserId(bean,userId);
	}
	/**
	 * 根据UserId删除
	 */
	public Integer deleteUserInfoByUserId(String userId) {
		return this.userInfoMapper.deleteByUserId(userId);
	}
	/**
	 * 根据Phone查询
	 */
	public UserInfo getUserInfoByPhone(String phone) {
		return this.userInfoMapper.selectByPhone(phone);
	}
	/**
	 * 根据Phone更新
	 */
	public Integer updateUserInfoByPhone(UserInfo bean, String phone) {
		return this.userInfoMapper.updateByPhone(bean,phone);
	}
	/**
	 * 根据Phone删除
	 */
	public Integer deleteUserInfoByPhone(String phone) {
		return this.userInfoMapper.deleteByPhone(phone);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void register(String password, String nickName, String phone) {
		UserInfo userInfo = this.userInfoMapper.selectByPhone(phone);
		if (null != userInfo) {
			throw new BusinessException("邮箱账号已存在");
		}

		String userId = StringTools.getUserId(phone);

		Date curDate = new Date();
		userInfo = new UserInfo();
		userInfo.setUserId(userId);
		userInfo.setNickName(nickName);
		userInfo.setPhone(phone);
		userInfo.setPassword(password);
		userInfo.setCreateTime(curDate);
		userInfo.setRight(0);
		this.userInfoMapper.insert(userInfo);
	}

	@Override
	public UserVO login(String phone, String password) {
		UserInfo userInfo = this.userInfoMapper.selectByPhone(phone);
		if (null == userInfo) {
			throw new BusinessException("账号不存在");
		}
		if (!userInfo.getPassword().equals(password)) {
			throw new BusinessException("密码有误，请检查密码");
		}
		String token = JwtUtils.createToken(userInfo.getUserId(), userInfo.getRight(), userInfo.getNickName());

		UserVO userVO = CopyTools.copy(userInfo, UserVO.class);
		userVO.setToken(token);

        return userVO;
	}

	@Override
	public void setRight(String userId, RightEnum right) {
		UserInfo userInfo = this.userInfoMapper.selectByUserId(userId);
		if (null == userInfo) {
			throw new BusinessException("用户不存在");
		}

		Integer currentRight = userInfo.getRight();
		RightEnum currentRightEnum = RightEnum.getByType(currentRight);

		if (currentRightEnum == null || right == null) {
			throw new BusinessException("权限类型不合法");
		}

		if (currentRightEnum == RightEnum.ADMINISTRATOR || right == RightEnum.ADMINISTRATOR) {
			throw new BusinessException("不允许调整管理员权限");
		}

		if (currentRightEnum == RightEnum.USER && right != RightEnum.DEVOPS) {
			throw new BusinessException("普通用户只能调整为运维人员");
		}

		if (currentRightEnum == RightEnum.DEVOPS && right != RightEnum.USER) {
			throw new BusinessException("运维人员只能调整为普通用户");
		}

		UserInfo updateInfo = new UserInfo();
		updateInfo.setRight(right.getType());
		this.userInfoMapper.updateByUserId(updateInfo, userId);

	}
}

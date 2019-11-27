/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.sys.service.impl;

import com.szzt.iot.admin.modules.sys.dao.SysMenuDao;
import com.szzt.iot.admin.modules.sys.dto.SysMenuDTO;
import com.szzt.iot.admin.modules.sys.entity.SysMenuEntity;
import com.szzt.iot.admin.modules.sys.enums.SuperAdminEnum;
import com.szzt.iot.admin.modules.security.user.UserDetail;
import com.szzt.iot.admin.modules.sys.service.SysLanguageService;
import com.szzt.iot.admin.modules.sys.service.SysRoleMenuService;
import com.szzt.iot.common.constant.Constant;
import com.szzt.iot.common.exception.ErrorCode;
import com.szzt.iot.common.exception.RobotException;
import com.szzt.iot.common.service.impl.BaseServiceImpl;
import com.szzt.iot.common.utils.ConvertUtils;
import com.szzt.iot.common.utils.HttpContextUtils;
import com.szzt.iot.common.utils.TreeUtils;
import com.szzt.iot.admin.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysLanguageService sysLanguageService;

	@Override
	public SysMenuDTO get(Long id) {
		SysMenuEntity entity = baseDao.getById(id, HttpContextUtils.getLanguage());

		SysMenuDTO dto = ConvertUtils.sourceToTarget(entity, SysMenuDTO.class);

		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysMenuDTO dto) {
		SysMenuEntity entity = ConvertUtils.sourceToTarget(dto, SysMenuEntity.class);

		//保存菜单
		insert(entity);
		saveLanguage(entity.getId(), "name", entity.getName());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysMenuDTO dto) {
		SysMenuEntity entity = ConvertUtils.sourceToTarget(dto, SysMenuEntity.class);

		//上级菜单不能为自身
		if(entity.getId().equals(entity.getPid())){
			throw new RobotException(ErrorCode.SUPERIOR_MENU_ERROR);
		}

		//更新菜单
		updateById(entity);
		saveLanguage(entity.getId(), "name", entity.getName());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		//删除菜单
		deleteById(id);

		//删除菜单国际化
		sysLanguageService.deleteLanguage("sys_menu", id);

		//删除角色菜单关系
		sysRoleMenuService.deleteByMenuId(id);
	}

	@Override
	public List<SysMenuDTO> getAllMenuList(Integer type) {
		List<SysMenuEntity> menuList = baseDao.getMenuList(type, HttpContextUtils.getLanguage());

		List<SysMenuDTO> dtoList = ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);

		return TreeUtils.build(dtoList, Constant.MENU_ROOT);
	}

	@Override
	public List<SysMenuDTO> getUserMenuList(UserDetail user, Integer type) {
		List<SysMenuEntity> menuList;

		//系统管理员，拥有最高权限
		if(user.getSuperAdmin() == SuperAdminEnum.YES.value()){
			menuList = baseDao.getMenuList(type, HttpContextUtils.getLanguage());
		}else {
			menuList = baseDao.getUserMenuList(user.getId(), type, HttpContextUtils.getLanguage());
		}

		List<SysMenuDTO> dtoList = ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);

		return TreeUtils.build(dtoList);
	}

	@Override
	public List<SysMenuDTO> getListPid(Long pid) {
		List<SysMenuEntity> menuList = baseDao.getListPid(pid);

		return ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);
	}

	private void saveLanguage(Long tableId, String fieldName, String fieldValue){
		sysLanguageService.saveOrUpdate("sys_menu", tableId, fieldName, fieldValue, HttpContextUtils.getLanguage());
	}

}
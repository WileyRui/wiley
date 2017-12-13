package com.wiley.shiro.settings.kernel.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.wiley.shiro.settings.kernel.entity.SysUser;

@Mapper
public interface SysUserMapper {
	SysUser findByName(Map<String,Object> userName);
}

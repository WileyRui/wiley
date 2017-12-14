package com.wiley.shiro.settings.kernel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.wiley.shiro.settings.kernel.entity.SysUser;

/**
 * 用户表数据操作接口
 * @author wiley
 *
 */
@Mapper
public interface SysUserMapper {
	
	@Select("SELECT * FROM USER WHERE name = #{name}")
	SysUser findByName(String name);
}

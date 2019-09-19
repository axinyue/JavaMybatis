package com.ax.mysqllearn.Mapper;

import org.apache.ibatis.annotations.Select;

import com.ax.mysqllearn.dao.Zone;

public interface ZoneMapper {
	
	@Select("select id,zoneName from mt_zone_info where id = #{id}")
	Zone selectOne(int id);
}

package com.Amos.Dao;

import com.Amos.entity.Zone;

import java.util.List;

public class ZoneDao extends BaseDao{

    public List<Zone> findZones() {
        String sql = "select * from bbs_zone order by isDef asc";

        return  this.findList(sql, Zone.class);
    }
}

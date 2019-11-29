package com.szzt.iot.transfer.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szzt.iot.common.service.impl.CrudServiceImpl;
import com.szzt.iot.transfer.db.dao.SysConfigDao;
import com.szzt.iot.transfer.db.dto.SysConfigDTO;
import com.szzt.iot.transfer.db.entity.SysConfigEntity;
import com.szzt.iot.transfer.db.service.SysConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统配置
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-11-29
 */
@Service
public class SysConfigServiceImpl extends CrudServiceImpl<SysConfigDao, SysConfigEntity, SysConfigDTO> implements SysConfigService {

    @Override
    public QueryWrapper<SysConfigEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<SysConfigEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public Map getAllConfig() {
        Map resultMap = new HashMap<String, String>();
        List<SysConfigEntity> list = baseDao.selectList(null);
        for (SysConfigEntity sysConfigEntity : list) {
            resultMap.put(sysConfigEntity.getParamKey(), sysConfigEntity.getParamValue());
        }
        return resultMap;
    }
}
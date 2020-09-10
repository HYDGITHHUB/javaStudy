package com.lhf.service;

import com.lhf.entity.Vaccines;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lhf.mapper.VaccinesMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhf
 * @since 2020-09-10
 */
public interface VaccinesService extends IService<Vaccines> {
    public boolean save(Vaccines vaccines);
}

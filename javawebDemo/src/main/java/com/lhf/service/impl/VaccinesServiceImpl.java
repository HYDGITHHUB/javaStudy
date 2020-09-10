package com.lhf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhf.entity.Shams;
import com.lhf.entity.Vaccines;
import com.lhf.mapper.ShamsMapper;
import com.lhf.mapper.VaccinesMapper;
import com.lhf.service.VaccinesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhf
 * @since 2020-09-10
 */
@Service
public class VaccinesServiceImpl extends ServiceImpl<VaccinesMapper, Vaccines> implements VaccinesService {

    @Autowired
    private VaccinesMapper vaccinesMapper;

    @Autowired
    private ShamsMapper shamsMapper;

    @Override
    public boolean save(Vaccines vaccines) {
        QueryWrapper shamsWrapper = new QueryWrapper();
        shamsWrapper.eq("name",vaccines.getName());
        shamsWrapper.eq("owner",vaccines.getOwner());
        shamsWrapper.eq("batch",vaccines.getBatch());
        Shams shams = shamsMapper.selectOne(shamsWrapper);
        // 如果不为空，则是假疫苗
        if (shams != null) return false;

        //判断库存中是否有货
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("name",vaccines.getName());
        wrapper.eq("owner",vaccines.getOwner());
        wrapper.eq("batch",vaccines.getBatch());
        Vaccines vaccines1 = vaccinesMapper.selectOne(wrapper);

        //当不是假疫苗之后，库存中没有
        if (vaccines1 == null) {
            int insert = vaccinesMapper.insert(vaccines);
            if (insert != 0) {
                return true;
            } else {
                return false;
            }
        } else {
            //不是假疫苗，但是库存中有货
            vaccines1.setNum(vaccines.getNum() + vaccines1.getNum());
            vaccinesMapper.updateById(vaccines1);
            return true;
        }
    }
}

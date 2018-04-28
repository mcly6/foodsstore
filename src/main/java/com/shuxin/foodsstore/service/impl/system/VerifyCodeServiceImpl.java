package com.shuxin.foodsstore.service.impl.system;

import com.shuxin.foodsstore.commons.enums.*;
import com.shuxin.foodsstore.commons.utils.DateUtil;
import com.shuxin.foodsstore.commons.utils.IDUtils;
import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.entity.system.SysVerifyCodeEntity;
import com.shuxin.foodsstore.repository.system.SysVerifycodeEntityRepository;
import com.shuxin.foodsstore.service.system.VerifyCodeService;
import com.shuxin.foodsstore.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SysVerifycodeEntityRepository codeRepository;

    /**   
     * @Title: 保存
     * @Param   
     * @Return   
     */ 
    @Override
    public SysVerifyCodeEntity save(SysVerifyCodeEntity syscode) {

        SysVerifyCodeEntity result = codeRepository.findByPhoneAndType(syscode.getPhone(), syscode.getType());

        if (result == null) { //新建
            return  codeRepository.save(syscode);
        } else {
            syscode.setId(result.getId());
            //将新的内容填充到
            BeanUtils.copyProperties(syscode,result);

            return codeRepository.save(result);
        }

    }
    /**   
     * @Title: 验证验证码
     * @Param   
     * @Return   
     */ 
    @Override
    public boolean checkVerifyCode(String phone, String code, Integer type, Integer effectMinute) {

        List resultList = em.createQuery("SELECT" +
                " COUNT(c)" +
                "FROM SysVerifycodeEntity c" +
                " WHERE c.phone= :phone" +
                " AND c.type= :type" +
                " AND c.code= :code" +
                " AND c.isuse=0" +
                "AND c.endTime > :nowData ").setParameter("phone", "15863157845").setParameter("type", "11")
                .setParameter("code", "112233").setParameter("nowData",new Date()).getResultList();

        if (resultList==null || resultList.isEmpty()){
            return false;
        }else{
            if (0 == (int) resultList.get(0)) {
                return false;
            }
                return  true;

        }
    }
    
    /**   
     * @Title: 修改状态为已用
     * @Param   
     * @Return   
     */ 
    @Override
    public SysVerifyCodeEntity updateCodeUse(String phone, Integer type) {

        SysVerifyCodeEntity entity = codeRepository.findByPhoneAndType(phone, type);
        entity.setIsuse(1);
        return codeRepository.save(entity);
    }

    @Override
    public SysVerifyCodeEntity findByPhone(String phone) {
        return codeRepository.findByPhone(phone);
    }



    @Override
    /**   
     * @Title: sendVerifyCode 发送短信
     * @Param [phone, digits, typeCode, mins] 电话,验证码长度,类型,时长
     * @Return com.shuxin.foodsstore.vo.ResultVO  
     */ 
    public ResultVO sendVerifyCode(String phone, Integer digits ,Integer type ,Integer mins) {

        //数据库查询是否有保存数据
        SysVerifyCodeEntity result = codeRepository.findByPhoneAndType(phone, type);

        SysVerifyCodeEntity sysVerifycodeEntity  = new SysVerifyCodeEntity(phone
                , IDUtils.getValidateCode(digits)
                , SelectEnum.NOT.getCode()
                , type
                , mins);

        SysVerifyCodeEntity save = null;

        if (result == null) { //未发送过短信
            //生成新的验证码对象
             save = save(sysVerifycodeEntity);

        } else { //发送过短信
            Date endTime = result.getEndTime();


            int i = DateUtil.compare_date(endTime.toString(), (new Date()).toString(), DateUtil.parsePatterns[1]);

            if (i > 0) { //未过期 不可发送
                return ResultVOUtils.error(400, ResultEnum.SYSVERIFYCODE_NOT_OUT.getMessage());

            } else { //过期,重新发送

                 save = save(sysVerifycodeEntity);


            }

        }

        if (save == null) {
            return ResultVOUtils.error(400, ResultEnum.SYSVERIFYCODE_CREATE_FAIL.getMessage());
        }

        /** TODO(调用阿里大于发送) .*/
        return  ResultVOUtils.success();


    }
}

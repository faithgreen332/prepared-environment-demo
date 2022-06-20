package com.funpay.management.service;

import com.funpay.common.sign.RsaUtils;
import com.funpay.common.utils.KeyHolder;
import com.funpay.management.mapper.TbRsaDao;
import com.funpay.model.dto.TbRsa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Leeko
 * @date 2022/2/26
 **/
@Service
@Slf4j
public class RsaService {

    @Autowired
    TbRsaDao rsaDao;

    public int saveOne() {
        TbRsa tbRsa = new TbRsa();
        tbRsa.setFilePath("D:\\mxico\\key\\key.properties");
        tbRsa.setSignVersion(1L);

        String version = KeyHolder.KEY_MAP.get(KeyHolder.KEY_SYS_RSA_EN_KEY_VERSION);
        tbRsa.setSignSecretKey(RsaUtils.signFromStr(KeyHolder.KEY_MAP.get(KeyHolder.KEY_SYS_RSA_SECRETKEY_PREFIX + version),
                RsaUtils.getPrivateKey(KeyHolder.KEY_MAP.get(KeyHolder.KEY_SYS_RSA_EN_PRI_PREFIX + version))));
        return rsaDao.insertSelective(tbRsa);
    }
}

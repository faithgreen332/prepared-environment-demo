package com.funpay.demo.controller;

import com.funpay.demo.dao.TMerchantDao;
import com.funpay.demo.dto.TMerchant;
import com.funpay.exception.pojo.response.QR;
import com.funpay.exception.pojo.response.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Leeko
 * @date 2022/3/9
 **/
@RestController
@RequestMapping("merchant")
@RequiredArgsConstructor
public class MerchantController {

    final TMerchantDao merchantDao;

    @PostMapping
    public R<Void> merchantBatch() {
        TMerchant merchant;
        for (int i = 0; i < 100; i++) {
            merchant = new TMerchant();
            merchant.setName("faith" + i);
            merchant.setDescription("测试" + i);
            merchant.setAbbr("f" + i);
            merchant.setPhone("5897451268" + i);
            merchant.setDeveloperMail("faithgreen@16" + i + ".com");
            merchant.setApplicationId("app" + i);
            merchant.setFinanceMail("finance@12" + i + ".com");
            merchant.setOfficePhone("0855145876" + i);

            // 实际使用不要在循环里 insert
            merchantDao.insertSelective(merchant);
        }
        return new R<>();
    }

    @GetMapping("/{pageIndex}/{pageSize}")
    public QR<TMerchant> merchantBatch(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize) {
        // 这个 PageHelper 屌啊，注意它只拦截这行代码之后的第一个select，注意第一个参数时从1开始的，不是传统的0
        PageHelper.startPage(pageIndex, pageSize);
        List<TMerchant> all = merchantDao.findAll();
        PageInfo<TMerchant> tMerchantPageInfo = new PageInfo<>(all);

        return new QR<>(tMerchantPageInfo);
    }
}

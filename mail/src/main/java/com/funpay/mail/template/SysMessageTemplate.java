package com.funpay.mail.template;

/**
 * 系统相关的邮件模板内容,我们邮件发的不频繁，就单个写死内容得了，不用抽象了
 *
 * @author Leeko
 * @date 2022/3/3
 **/
public class SysMessageTemplate {

    public static String sysErrorMessage(String errorMessage) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div>大佬啊，服务出错啦，快去救救她</div></br>")
                .append("<div>出错原因：" + errorMessage + "</div></br>")
                .append("相应的数据也已经入库，快去补救吧");
        return sb.toString();
    }
}

package com.funpay.usercenter.service;

import com.funpay.common.constants.Constant;
import com.funpay.common.enums.BalanceOperResult;
import com.funpay.common.enums.CallStatus;
import com.funpay.common.enums.TransferStatusCode;
import com.funpay.model.request.TransferReq;
import com.funpay.model.response.FunpayResult;
import com.funpay.model.response.TransferRes;
import com.funpay.redis.config.FunRedisClient;
import com.funpay.redis.config.RedisLock;
import com.funpay.usercenter.mapper.TBalanceTranslogDao;
import com.funpay.usercenter.mapper.TRequestTransferDao;
import com.funpay.usercenter.mapper.TUcBussinessDao;
import com.funpay.usercenter.mapper.TbBankInfoPhDao;
import com.funpay.usercenter.model.TBusinessCharge;
import com.funpay.usercenter.model.TRequestTransfer;
import com.funpay.usercenter.model.TUcBussiness;
import com.funpay.usercenter.model.TbBankInfoPh;
import com.funpay.usercenter.utils.FeeUtils;
import com.funpay.usercenter.utils.NumberContants;
import com.funpay.usercenter.utils.TransUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * @author Leeko
 * @date 2022/2/15
 **/
@Service
@Slf4j
public class TransferService {


    @Autowired
    TbBankInfoPhDao tbBankInfoPhDao;

    @Autowired
    FunRedisClient funRedisClient;

    @Autowired
    BalanceService balanceService;

    @Autowired
    TRequestTransferDao tRequestTransferDao;

    @Autowired
    TUcBussinessDao tUcBussinessDao;

    @Autowired
    TBalanceTranslogDao tBalanceTranslogDao;

//    public FunpayResult transfer(TransferReq transferReq) {
//
//        // 检查银行卡号
//        TbBankInfoPh tbBankInfoPh = tbBankInfoPhDao.selectByPrimaryKey(transferReq.getBankNo());
//        if (tbBankInfoPh == null) {
//            return FunpayResult.resultNullData(TransferResEnum.CARD_ILLEGAL.getCode(), TransferResEnum.CARD_ILLEGAL.getMsg());
//        }
//
//        String lockKey = RedisPrefix.TRANSFER_ORDER_KEY + transferReq.getMerchantID();
//        RedisLock redisLock = new RedisLock(funRedisClient.getJedisCluster(), lockKey, NumberContants.LOCK_FOR_3_MINUTES, NumberContants.LOCK_FOR_3_MINUTES);
//        try {
//            if (redisLock.lock()) {
//                // 检查是否订单重复提交
//                if (tRequestTransferDao.checkTransferOrderNo(transferReq.getOrderNo(), transferReq.getMerchantID()) > 0) {
//                    return FunpayResult.resultNullData(TransferResEnum.DUPLICATE_ERROR.getCode(), TransferResEnum.DUPLICATE_ERROR.getMsg());
//                }
//
//                // 校验业务状态
//                if (!verifyBusinessStatus(transferReq.getBusinessID())) {
//                    return FunpayResult.resultNullData(TransferResEnum.BUSINESS_STATE_ERROR.getCode(), TransferResEnum.BUSINESS_STATE_ERROR.getMsg());
//                }
//
//                // TODO 获取业务计费，菲律宾的是用的 V5pay 这种第三方接银行的，墨西哥的是直接接银行，这里是找到支持这个 businessCode 的银行对接
//                TBusinessCharge businessCharge = getBusinessCharge();
//                // 如果找不到支持这个交易的对接银行，返回
//                if (businessCharge == null) {
//                    return FunpayResult.resultNullData(TransferResEnum.REJECTED_ERROR.getCode(), TransferResEnum.REJECTED_ERROR.getMsg());
//                }
//
//                // 写库
//                TransferRes tRequestTransfer = insertIntoTRequestTransfer(transferReq, businessCharge, tbBankInfoPh);
//
//                return FunpayResult.result(CallStatus.SUCCESS.getCode(), CallStatus.SUCCESS.getValue(), tRequestTransfer);
//            }
//        } catch (Exception e) {
//            log.error("打款业务，uc 创建 t_request_transfer 数据时异常", e);
//            e.printStackTrace();
//        } finally {
//            redisLock.unlock();
//        }
//        return FunpayResult.resultNullData(CallStatus.INNER_ERROR.getCode(), CallStatus.INNER_ERROR.getValue());
//    }

    /**
     * 1.最简单的方式直接写死银行
     * 2.推荐这里抽离出一个中间层来，方便以后对接任何银行或者第三方
     * TODO
     *
     * @return
     */
    private TBusinessCharge getBusinessCharge() {
        return null;
    }

    /**
     * 生成一条 t_request_transfer 记录，入库
     *
     * @param transferReq
     * @param businessCharge
     * @param tbBankInfoPh
     */
//    private TransferRes insertIntoTRequestTransfer(TransferReq transferReq, TBusinessCharge businessCharge, TbBankInfoPh tbBankInfoPh) {
//        // TODO 这里是菲律宾的规则，墨西哥再看看怎么设计，使用 businessCode 生成订单号
//        String tradeNo = TransUtil.getTransIdPHP(businessCharge.getBusinessCode(), serverConfig.getId());
//
//        // TODO 计算打款费用，墨西哥的规则待定
//        log.info("阶梯计价规则:{}", businessCharge.getTieredFeeRules());
//
//        BigDecimal fee = FeeUtils.calculationFee(String.valueOf(businessCharge.getTieredFeeRules()), Long.getLong(transferReq.getAmount()));
//
//        String vatRate = "0.12";
//        vatRate = funRedisClient.get("VAT_RATE");
//        long vat = fee.multiply(new BigDecimal(vatRate)).longValue();
//        long freeze = fee.longValue() + Long.getLong(transferReq.getAmount()) + vat;
//
//        log.info("订单号:{} 实际冻结金额:{} 打款金额:{} 手续费:{} 增值税{}", tradeNo, freeze, transferReq.getAmount(), fee, vat);
//
//        // TODO
////        TRequestTransfer requestTransfer = new TRequestTransfer(merchantId, businessId, recordTime, freeze,
////                amount, vat,
////                currency, orderNo, tradeNo, returnUrl, businessCharge.getBusinessCode(), bankNo.toString(),
////                Integer.toString(bankBranch), accNo, accName, memo,
////                TransferCode.TRANSACTION_RUN.getCode(), TransferCode.TRANSACTION_RUN.getValue(),
////                version, bankLocation, accountType, clientId, phoneNumber, idNo
////        );
//
//        tRequestTransferDao.insert(new TRequestTransfer());
//
//        TransferRes transferRespVo = new TransferRes();
//        transferRespVo.setTradeNo(tradeNo);
//        transferRespVo.setBankNoCode(tbBankInfoPh.getBankCode());
//        transferRespVo.setProductType(tbBankInfoPh.getProductType());
//        transferRespVo.setBusinessCode(businessCharge.getBusinessCode());
//        return transferRespVo;
//    }

    private boolean verifyBusinessStatus(int businessID) {
        TUcBussiness tUcBussiness = tUcBussinessDao.selectByPrimaryKey(businessID);
        // 业务不存在
        if (tUcBussiness == null) {
            return false;
        }
        // 业务存在，则检查业务状态是否发生变更
//        return verifyBusinessModified(tUcBussiness);
        return true;
    }

    /**
     * 先从 redis 里取业务状态，如果有就校验，没有的话再从数据库取出来校验
     * 校验的逻辑：
     * 数据库存了状态 status 和服务类型 serviceType 和用他们经过 rsa 加密的结果
     * 从数据库取出来 status 和 serviceType 签名验签，用 rsa 加密结果验签，
     * 验签成功说明没有被改动，否则修改过了
     *
     * @param tUcBussiness tUcBusiness
     * @return 是否校验成功
     */
//    private boolean verifyBusinessModified(TUcBussiness tUcBussiness) {
//        String redisKey = RedisPrefix.BUSINESS_STATUS + tUcBussiness.getId();
//        String status = funRedisClient.get(redisKey);
//        try {
//            // redis 里没缓存，从数据库取
//            if (StringUtils.isEmpty(status)) {
//                SysDataEdt instance = SysDataEdt.getInstance();
//                RSAPrivateKey privateKey = RsaUtils.getPrivateKey(instance.getEncryptMap(instance.getRsaSignKeyMapVersion()));
//
//                // 缓存没有使用加密 在数据库中获取
//                RSAPublicKey publicKey = RsaUtils.getPublicKey(instance.getDecryptMap(tUcBussiness.getRsaSignVersion()));
//                String updateSign = "" + tUcBussiness.getId() + tUcBussiness.getMerchantId() + tUcBussiness.getStatus() + tUcBussiness.getServiceType();
////                String encrypt = RsaUtils.signWithPrivateKey(updateSign.getBytes(StandardCharsets.UTF_8), privateKey);
//                // 先对原数据进行验签，验签成功后更新更新
//                if (RsaUtils.verifyWithPublicKey(encrypt.getBytes(StandardCharsets.UTF_8), publicKey, tUcBussiness.getRsaSignData())) {
//                    log.info("同步到业务状态到redis缓存成功 id:{}", tUcBussiness.getId());
//                    funRedisClient.set(RedisPrefix.BUSINESS_STATUS + tUcBussiness.getId(), tUcBussiness.getStatus());
//                }
//
//                status = tUcBussiness.getStatus();
//            }
//            // 判断业务状态是否可用
//            switch (BusinessStatus.getBusinessStatus(Integer.parseInt(status))) {
//                case RUN:
//                case NOT_TO_BE_SETTLED:
//                    return true;
//                case NOT_TO_BE_USED:
//                case PERMANENTLY_BAN:
//                case APPLYING:
//                case REJECT:
//                default:
//                    return false;
//            }
//
//        } catch (Exception e) {
//            log.error("校验失败, userKey(id):" + tUcBussiness.getId(), e);
//            e.printStackTrace();
//        }
//        return false;
//    }
    public FunpayResult freeze(TransferReq transferReq) {

        // 1.查询看交易记录是否存在
        // 原来的代码时用 merchantId，businessId，orderNo 去查的，没有好的索引，现在改成唯一索引的 tradeNo
        TRequestTransfer tRequestTransfer = tRequestTransferDao.selectByTradeNo(transferReq.getTradeNo());
        if (tRequestTransfer == null) {
            return FunpayResult.resultNullData(TransferResEnum.TRADE_ILLEGAL.getCode(), TransferResEnum.TRADE_ILLEGAL.getMsg());
        }

        String lockKey = RedisPrefix.TRANSFER_ORDER_KEY + transferReq.getMerchantID();
        RedisLock redisLock = new RedisLock(funRedisClient.getJedisCluster(), lockKey, NumberContants.LOCK_FOR_3_MINUTES, NumberContants.LOCK_FOR_3_MINUTES);
        try {
            // TODO 只抢一次锁？原代码这里只抢一次锁，抢不到就改 t_request_transfer 里的记录为失败，难道要手工去处理？why ？
            if (redisLock.lock()) {
                // 首先判断是否达到当日打款金额限制
                String dayLimitKey = RedisPrefix.PAYMENT_DAY_LIMIT + transferReq.getMerchantID()
                        + Constant.UNDERSCORE + transferReq.getAccountNo();
                if (funRedisClient.exist(dayLimitKey)) {
                    // 打款限额定为五千万
                    if (Long.getLong(transferReq.getAmount()) > NumberContants.TRANSFER_DAY_LIMIT) {
                        log.info("当前资金限制:" + dayLimitKey + ", 资金:" + funRedisClient.get(dayLimitKey));
                        return FunpayResult.resultNullData(TransferResEnum.DAILY_LIMIT.getCode(), TransferResEnum.DAILY_LIMIT.getMsg());
                    }
                }

                // 2.进行冻结
                int freezeResultCode = balanceService.freezeAmount(transferReq.getMerchantID(), Long.getLong(transferReq.getAmount()));

                switch (BalanceOperResult.getBalanceOperResult(freezeResultCode)) {
                    case INSUFFICIENT_BALANCE:
                        log.info("Retry --- balance is insufficient.");
                        return FunpayResult.resultNullData(TransferResEnum.INSUFFICIENT_BALANCE.getCode(), TransferResEnum.INSUFFICIENT_BALANCE.getMsg());
                    case UPDATE_FAIL:
                    case FREEZE_FAIL:
                        log.info("Failed to freeze balance, balance update fail");
                        return FunpayResult.resultNullData(TransferResEnum.POCKET_STATUS_ILLEGAL.getCode(), TransferResEnum.POCKET_STATUS_ILLEGAL.getMsg());
                    case UNKNOWN_ERROR:
                        return FunpayResult.resultNullData(CallStatus.INNER_ERROR.getCode(), CallStatus.INNER_ERROR.getValue());
                    default:
                        break;
                }

                // 3.更新当日打款金额限制
                updateDayLimit(transferReq, dayLimitKey);

                // 4.改变 t_request_transfer 中该条记录的状态，由 begin 变成 pending
                tRequestTransferDao.updateTransferStatus(TransferStatusCode.PENDING.getCode(), TransferStatusCode.PENDING.getValue(), tRequestTransfer.getId());

                // 5.返回
                return FunpayResult.resultNullData(CallStatus.SUCCESS.getCode(), CallStatus.SUCCESS.getValue());
            } else {
                //TODO 如果抢不到锁，应该要重新 acquire 一段时间吧？
            }

        } catch (Exception e) {
            log.error("uc 冻结余额失败，", e);
            e.printStackTrace();
        } finally {
            redisLock.unlock();
        }

        // 6.原来这里是记录日志，据说用不着了，先不写了吧
//        BalanceTranslog balanceTranslog = new BalanceTranslog(merchantId, BalanceOperation.FREEZE.getCode(), freeze, StatusCode.SUCCESS_FREEZE.getCode(), tradeNo);
//        balanceTranslogMapper.insert(balanceTranslog);
        return FunpayResult.resultNullData(CallStatus.INNER_ERROR.getCode(), CallStatus.INNER_ERROR.getValue());
    }

    private void updateDayLimit(TransferReq transferReq, String dayLimitKey) {
        String s;
        Long t;
        long dayBalanceLimit = StringUtils.isEmpty(s = funRedisClient.get(dayLimitKey)) ? 0L : Long.getLong(s);
        dayBalanceLimit += Long.getLong(transferReq.getAmount());
        long existedExpired = StringUtils.isEmpty(t = funRedisClient.getttl(dayLimitKey)) ? 0L : t;
        String expire = String.valueOf(existedExpired == 0 ? NumberContants.SEC_IN_DAY : existedExpired);

        funRedisClient.setWithExpireTime(dayLimitKey, Integer.parseInt(expire), String.valueOf(dayBalanceLimit));
        log.info("资金限制:" + dayLimitKey + ", 资金:" + dayBalanceLimit);
    }

    interface RedisPrefix {

        /**
         * 打款日限制
         */
        String PAYMENT_DAY_LIMIT = "payment_day_limit:";
        /**
         * business  业务状态
         */
        String BUSINESS_STATUS = "BUSINESS_STATUS:";
        /**
         * uc 创建打款订单
         */
        String TRANSFER_ORDER_KEY = "transfer_order_key:";
    }

    enum TransferResEnum {

        /**
         * 账户余额不足
         */
        INSUFFICIENT_BALANCE(6001, "the balance is insufficient."),

        /**
         * pocket 状态不对
         */
        POCKET_STATUS_ILLEGAL(50013, "the pocket status is illegal."),

        /**
         * 当日打款上限
         */
        DAILY_LIMIT(50004, "over daily limit."),

        /**
         * 交易请求记录不存在
         */
        TRADE_ILLEGAL(50008, "the trade request record does not exist."),

        /**
         * 卡号不存在
         */
        CARD_ILLEGAL(50009, "the bank code does not exist."),
        /**
         * 订单号重复即交易重复
         */
        DUPLICATE_ERROR(60005, "the transaction is duplicated."),
        /**
         * 业务校验不通过,业务不存在，或者业务状态发生了变更导致不可用
         */
        BUSINESS_STATE_ERROR(50002, "business state error."),

        /**
         * 请求被拒绝, 用户已经被添加入黑名单或不在白名单内
         */
        REJECTED_ERROR(50006, "the request is rejected."),
        SUCCESS(10000, "the request is succeed."),
        /**
         * 未知错误
         */
        INNER_ERROR(50000, "unknown error."),
        ;

        private int code;
        private String msg;

        TransferResEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}

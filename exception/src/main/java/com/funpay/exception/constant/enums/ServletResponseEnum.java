package com.funpay.exception.constant.enums;

import com.funpay.exception.assertion.CommonExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

/**
 * 处理接入的请求进入 controller 层之前的异常
 *
 * @author Leeko
 * @date 2022/3/9
 **/
@Getter
@AllArgsConstructor
public enum ServletResponseEnum implements CommonExceptionAssert {

    /**
     * 见名知义，处理 MethodArgumentNotValidException
     */
    MethodArgumentNotValidException(HttpServletResponse.SC_BAD_REQUEST, "参数校验错误"),
    /**
     * 见名知义，处理 MethodArgumentTypeMismatchException
     */
    MethodArgumentTypeMismatchException(HttpServletResponse.SC_BAD_REQUEST, "参数类型错误"),
    /**
     * 见名知义，处理 MissingServletRequestPartException
     */
    MissingServletRequestPartException(HttpServletResponse.SC_BAD_REQUEST, ""),
    /**
     * 见名知义，处理 MissingPathVariableException
     */
    MissingPathVariableException(HttpServletResponse.SC_BAD_REQUEST, "未检测到路径参数"),
    /**
     * 见名知义，处理 BindException
     */
    BindException(HttpServletResponse.SC_BAD_REQUEST, "参数绑定错误"),
    /**
     * 见名知义，处理 MissingServletRequestParameterException
     */
    MissingServletRequestParameterException(HttpServletResponse.SC_BAD_REQUEST, "缺少请求参数"),
    /**
     * 见名知义，处理 TypeMismatchException
     */
    TypeMismatchException(HttpServletResponse.SC_BAD_REQUEST, "参数类型匹配失败"),
    /**
     * 见名知义，处理 ServletRequestBindingException
     */
    ServletRequestBindingException(HttpServletResponse.SC_BAD_REQUEST, ""),
    /**
     * 见名知义，处理 HttpMessageNotReadableException
     */
    HttpMessageNotReadableException(HttpServletResponse.SC_BAD_REQUEST, "请求体携带的 json 串反序列化成 pojo 的过程中失败"),
    /**
     * 见名知义，处理 NoHandlerFoundException
     */
    NoHandlerFoundException(HttpServletResponse.SC_NOT_FOUND, "没有匹配到控制器异常"),
    /**
     * 见名知义，处理 NoSuchRequestHandlingMethodException
     */
    NoSuchRequestHandlingMethodException(HttpServletResponse.SC_NOT_FOUND, "没有匹配到控制器异常"),
    /**
     * 见名知义，处理 HttpRequestMethodNotSupportedException
     */
    HttpRequestMethodNotSupportedException(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "请求方法不匹配异常"),
    /**
     * 见名知义，处理 HttpMediaTypeNotAcceptableException
     */
    HttpMediaTypeNotAcceptableException(HttpServletResponse.SC_NOT_ACCEPTABLE, "请求头的 content-type 与控制器不匹配异常"),
    /**
     * 见名知义，处理 HttpMediaTypeNotSupportedException
     */
    HttpMediaTypeNotSupportedException(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "请求头的 content-type 与控制器不匹配异常"),
    /**
     * 见名知义，处理 ConversionNotSupportedException
     */
    ConversionNotSupportedException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ""),
    /**
     * 见名知义，处理 HttpMessageNotWritableException
     */
    HttpMessageNotWritableException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "pojo 序列化 json 时异常"),
    /**
     * 见名知义，处理 AsyncRequestTimeoutException
     */
    AsyncRequestTimeoutException(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "");

    /**
     * 返回码，目前与 http 的 code 相同
     */
    private int code;

    /**
     * 返回信息，目前直接读取异常的message
     */
    private String message;

}

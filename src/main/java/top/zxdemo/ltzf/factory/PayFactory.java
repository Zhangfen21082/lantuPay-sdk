package top.zxdemo.ltzf.factory;

import top.zxdemo.ltzf.payments.app.AppPayService;
import top.zxdemo.ltzf.payments.h5.H5PayService;
import top.zxdemo.ltzf.payments.jsapi.JSPayService;
import top.zxdemo.ltzf.payments.jump_h5.JumpH5PayService;
import top.zxdemo.ltzf.payments.nativepay.NativePayService;

public interface PayFactory {

    NativePayService nativePayService();

    H5PayService h5PayService();

    AppPayService appPayService();

    JSPayService jsPayService();

    JumpH5PayService jumpH5PayService();

}

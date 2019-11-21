package com.tgcity.base.fragment;

import com.bumptech.glide.Glide;
import com.tgcity.base.utils.LogUtils;

/**
 * @author TGCity
 * 本层主要监听系统内存并作出相应动作，与BaseMemoryActivity功能类似
 * 参考地址：https://mp.weixin.qq.com/s?__biz=MzIxNjc0ODExMA==&mid=2247484311&idx=1&sn=1fe0416bed4137dd45c6e9c153bb14f4&chksm=97851ab6a0f293a0cde28ff6d1091b2232e1758e9845a05549d01c62f412def742985d642630&scene=21#wechat_redirect
 */

public abstract class BaseMemoryFragment extends BaseLazyLoadFragment {

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(getContext()).clearMemory();
        clearMemory();
    }


    /**
     * 内存已经过低了
     */
    public void clearMemory() {
        //TODO 强烈建议开始清理垃圾
        LogUtils.d("当前模块页面 "+getClass()+" 强烈建议开始清理垃圾");
    }
}

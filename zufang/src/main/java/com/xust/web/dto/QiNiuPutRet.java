package com.xust.web.dto;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 18:54 2019/4/27
 * @modified By:
 */
public  final class QiNiuPutRet {

    public String key;
    public String hash;
    public String bucket;
    public int width;
    public int height;

    @Override
    public String toString() {
        return "QiNiuPutRet{" +
                "key='" + key + '\'' +
                ", hash='" + hash + '\'' +
                ", bucket='" + bucket + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}

package com.sdys.appgame.utils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class XMLEncodeModel {

    private Map<String, String> heads = new HashMap<String, String>();//XML报文头
    private Map<String, String> roots = new HashMap<String, String>();//XML报文数据

    /**
     * 往XML模型添加报文头
     *
     * @param key   数据名
     * @param value 数据值
     * @return
     */
    public String setHeadParameter(String key, String value) {
        if (heads == null) {
            heads = new HashMap<String, String>();
        }
        return heads.put(key, value);
    }

    /**
     * 往XML模型添加报文数据
     *
     * @param key   数据名
     * @param value 数据值
     * @return
     */
    public String setRootParameter(String key, String value) {
        if (roots == null) {
            roots = new HashMap<String, String>();
        }
        return roots.put(key, value);
    }

    public String getHeadParameter(String key) {
        return heads != null ? heads.get(key) : null;
    }

    public String getRootParameter(String key) {
        return roots != null ? roots.get(key) : null;
    }


    /**
     * 产生模型对应的XML数据
     *
     * @param charset 编码
     * @return
     */
    public String toSendData(Charset charset) {
        StringBuilder builder = new StringBuilder();
        builder.append("<xml>\n");
        if (heads != null) {
            for (Entry<String, String> keyVal : heads.entrySet()) {
                if (keyVal != null) {
                    builder.append("<").append(keyVal.getKey()).append(">");
                    builder.append(keyVal.getValue() != null ? keyVal.getValue() : "");
                    builder.append("</").append(keyVal.getKey()).append(">\n");
                }
            }
        }
        builder.append("</xml>");
        return builder.toString();
    }


    public static void main(String[] args) {
        XMLEncodeModel model = new XMLEncodeModel();
        model.setHeadParameter("appid", "wx2421b1c4370ec43b");
        model.setHeadParameter("mch_id", "10000100");
        model.setHeadParameter("nonce_str", "6cefdb308e1e2e8aabd48cf79e546a02");
        model.setHeadParameter("out_refund_no", "1415701182");
        model.setHeadParameter("out_trade_no", "1415757673");
        model.setHeadParameter("refund_fee", "1");
        model.setHeadParameter("total_fee", "1");
        model.setHeadParameter("transaction_id", "");
        model.setHeadParameter("sign", "FE56DD4AA85C0EECA82C35595A69E153");
        String xmlString = model.toSendData(Charset.forName("GBK"));
        System.out.println(xmlString);
    }
}
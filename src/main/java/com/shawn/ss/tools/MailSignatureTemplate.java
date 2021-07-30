package com.shawn.ss.tools;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailSignatureTemplate {
    static String signatureData = "";
    static Map<String, String> userInfo = new HashMap<String, String>() {
        {
            this.put("id", "092845090934ace19fd4dd920");
            this.put("userName", "熊猫");
            this.put("userTitle", "高级UI设计师");
            this.put("userImg", "https://mimg.qiye.163.com/p/js6/lib/img/noface.gif");
            this.put("position", "杭州网易竹书信息技术有限公司北京分公司灵犀事业部设计组");
            this.put("email", "songzibo@office.163.com");
            this.put("mobile", "15828646276");
            this.put("location", "北京市海淀区西北旺东路10号院中关村软件园二期西区7号");
        }
    };
    static List<String> additionalInfo = new ArrayList<String>() {
        {
            this.add("这是一条添加的自定义备注信息");
            this.add("这是另一条添加的比较长的自定义备注信息这是另一条添加的比较长的自定义备注信息");
        }
    };

    public static void main(String[] args) {
        STGroup group=new STGroupDir("/Users/shishengb18701shishengcorp.netease.com/Documents/project/otherProject/arts/src/main/resources/");
        ST sig = group.getInstanceOf("tmplConfig");
        userInfo.forEach(sig::add);
        sig.add("additionalInfo",additionalInfo);
        sig.add("useVendorInfo","true");
        final String render = sig.render();
        System.out.println(render);
    }
}

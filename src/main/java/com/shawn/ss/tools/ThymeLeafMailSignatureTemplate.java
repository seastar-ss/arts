package com.shawn.ss.tools;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.util.Locale;


public class ThymeLeafMailSignatureTemplate {
    static class HtmlLayoutObj {
        private boolean needHtml;
        private Integer signType;
        private Long signId;
        private boolean showAppVipTag;
        private String detailUrl;
        private String name;
        private String title;
        private String company;
        private String email;
        private String mobile;
        private String location;
        private UserAddItem[] userAddItem;
        private String profilePhoto;

        public HtmlLayoutObj(Integer signType, boolean showAppVipTag, String detailUrl, String name, String title, String company, String email, String mobile, String location, UserAddItem[] userAddItem, String profilePhoto) {
            this.signType = signType;
            this.showAppVipTag = showAppVipTag;
            this.detailUrl = detailUrl;
            this.name = name;
            this.title = title;
            this.company = company;
            this.email = email;
            this.mobile = mobile;
            this.location = location;
            this.userAddItem = userAddItem;
            this.profilePhoto = profilePhoto;
        }

        public HtmlLayoutObj() {
        }

        public Integer getSignType() {
            return signType;
        }

        public HtmlLayoutObj setSignType(Integer signType) {
            this.signType = signType;
            return this;
        }

        public boolean isShowAppVipTag() {
            return showAppVipTag;
        }

        public HtmlLayoutObj setShowAppVipTag(boolean showAppVipTag) {
            this.showAppVipTag = showAppVipTag;
            return this;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public HtmlLayoutObj setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
            return this;
        }

        public String getName() {
            return name;
        }

        public HtmlLayoutObj setName(String name) {
            this.name = name;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public HtmlLayoutObj setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getCompany() {
            return company;
        }

        public HtmlLayoutObj setCompany(String company) {
            this.company = company;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public HtmlLayoutObj setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getMobile() {
            return mobile;
        }

        public HtmlLayoutObj setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public String getLocation() {
            return location;
        }

        public HtmlLayoutObj setLocation(String location) {
            this.location = location;
            return this;
        }

        public UserAddItem[] getUserAddItem() {
            return userAddItem;
        }

        public HtmlLayoutObj setUserAddItem(UserAddItem[] userAddItem) {
            this.userAddItem = userAddItem;
            return this;
        }

        public String getProfilePhoto() {
            return profilePhoto;
        }

        public HtmlLayoutObj setProfilePhoto(String profilePhoto) {
            this.profilePhoto = profilePhoto;
            return this;
        }

        public Long getSignId() {
            return signId;
        }

        public HtmlLayoutObj setSignId(Long signId) {
            this.signId = signId;
            return this;
        }

        public boolean isNeedHtml() {
            return needHtml;
        }

        public HtmlLayoutObj setNeedHtml(boolean needHtml) {
            this.needHtml = needHtml;
            return this;
        }
    }

    static class UserAddItem {
        String item;

        public UserAddItem(String item) {
            this.item = item;
        }

        public String getItem() {
            return item;
        }

        public UserAddItem setItem(String item) {
            this.item = item;
            return this;
        }
    }

    public static void main(String[] args) {
        HtmlLayoutObj data = new HtmlLayoutObj()
                .setNeedHtml(true)
                .setDetailUrl("http://su-desktop-web.cowork.netease.com/static_html/signature.html?id=843166956887261")
                .setProfilePhoto("https://nos.netease.com/qiyeimage/head/3993514503636077/mail80x80.jpg?timestamp=1619598965185")
                .setName("王一博")
                .setTitle("高级UI设计师")
//                .setTitle(null)
                .setCompany("杭州网易竹书信息技术有限公司北京分公司灵犀事业部设计组")
//                .setCompany(null)
                .setEmail("songzibo@office.163.com")
//                .setMobile(null)
                .setLocation("北京市海淀区西北旺东路10号院中关村软件园二期西区7号")
//                .setLocation(null)
                .setSignId(1002020100123L)
                .setShowAppVipTag(false)
                .setUserAddItem(new UserAddItem[]{
                        new UserAddItem("这是一条添加的自定义备注信息"),
                        new UserAddItem("这是另一条添加的比较长的自定义备注信息这是另一条添加的比较长的自定义备注信息"),
                        new UserAddItem("object Test English <+Message_<>"),
                });
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setTemplateMode("HTML5");
        resolver.setSuffix(".html");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        for (int i = 1; i <= 3; ++i) {
            for (int j = 0; j < 2; ++j) {
                data.setSignType(i);
                data.setShowAppVipTag(j == 0);
                Context context = new Context(Locale.CHINESE);
                context.setVariable("data", data);
                final String contentMessage = templateEngine.process("signature_template", context);
                stringToFile(contentMessage, "/Users/shishengb18701shishengcorp.netease.com/Documents/project/workProject/sirius-desktop/packages/web-ui/static_html/test/template-" + i + j + "html.html");
                context = new Context(Locale.CHINESE);
                context.setVariable("data", data);
                final String contentMessageDiv = templateEngine.process("signature_template_without_html_tag", context);
                System.out.println("done for " + i + "-" + j);
                stringToFile(contentMessage, "/Users/shishengb18701shishengcorp.netease.com/Documents/project/workProject/sirius-desktop/packages/web-ui/static_html/test/template-" + i + j + "div.html");
            }
        }
    }

    public static void stringToFile(String content, String fileName) {
        try {
            FileWriter wr = new FileWriter(fileName);
            wr.write(content);
            wr.flush();
            wr.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

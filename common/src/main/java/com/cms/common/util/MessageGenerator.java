package com.cms.common.util;

import com.cms.common.entity.SheetCatalog;

/**
 * @author Creams
 */
public class MessageGenerator {

    public static final String MEMBERSHIP_TITLE = "您的社团职位有了新变化";

    public static final String VERIFY_TITLE = "您的社团有新的事务审核";

    public static final String NEWS_TITLE = "您所在的社团有新的公告";


    public static String membershipContent(String communityName, String url) {
        return "尊敬的" + communityName + "成员:\n" + "您的社团职位有了新变化，详情前往 " + url + " 查看\n";
    }

    public static String applyTitle(SheetCatalog catalog) {
        return "您的" + catalog.getDesc() + "申请有了新进展";
    }

    public static String applyContent(SheetCatalog catalog, String result, String projectName, String url) {
        StringBuilder builder = new StringBuilder();

        builder.append("尊敬的社团之家用户:\n")
                .append("您在关于")
                .append(projectName)
                .append("的")
                .append(catalog.getDesc())
                .append("申请已经审核完毕。")
                .append("具体结果为:")
                .append(result)
                .append("。")
                .append("详情前往")
                .append(url)
                .append("查看");

        return builder.toString();
    }

    public static String verifyContent(String communityName) {
        return "尊敬的" + communityName + "成员:\n您所在的社团有新的申请事务，详情请前往我的社团中查看";
    }

    public static String newsContent(String communityName) {
        return "尊敬的" + communityName + "成员:\n您所在的社团有新的公告，详情请在我的社团中查看";
    }



}

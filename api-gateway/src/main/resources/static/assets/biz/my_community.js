function showUserInfo() {
    var userInfo = getUser();
    $("#username").text(userInfo.realName);
}

function showMyCommunity() {
    var userInfo = getUser();
    $.ajax({
        url: "web/community/myCommunity",
        dataType: "json",
        type: "get",
        data: {
            userId: userInfo.userId
        },
        success: function (res) {
            if (res.code === 0) {
                var num = 0;
                $.each(res.data, function (idx, val) {
                    str = "<tr>\n" +
                        "\t<td> <h3><a href='community_manage.html'>"+ val.name +" </a></h3>" +
                        "<h4>职务: "+val.roleName+"         加入时间: "+dateFormat(val.createDate)+"</h4>" +
                        "</td>\n" +
                        "\t</tr>";
                    $("#myCommunityList").append(str);
                    num++;
                })
            }
        }

    });
}



showUserInfo();
showMyCommunity();

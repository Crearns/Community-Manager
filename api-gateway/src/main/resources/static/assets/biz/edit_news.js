var userInfo;

function showUserInfo() {
    userInfo = getUser();
    $("#username").text(userInfo.realName);
}

function submitNews() {
    const urlParams = new URLSearchParams(window.location.search);
    var communityId = urlParams.get('id');
    if (communityId == null) location.href="error.html";



    var title = $("#news_title").val();
    var content = $("#news_content").val();

    if (title.length == 0 || content.length == 0) {
        $("#info").text("请输入完整信息后提交");
        return;
    }

    if (content.length >= 1800) {
        $("#info").text("公告长度超过限制，请修改后重试");
        return;
    }

    if (!confirm("是否确认提交")) {
        return;
    }

    $.ajax({
        url: "web/community/news",
        type: "post",
        datatype: "json",
        data: {
            communityId: communityId,
            userId: userInfo.id,
            visible: $("#visible").val(),
            title: title,
            content: content
        },
        success: function (res) {
            if (res.code === 0) {
                alert("公告提交成功");
                location.href = "community_manage.html?id=" + communityId
            }
        }, error: function (err) {
            $("#info").text("提交公告时出错，请通知管理员")
        }
    })
}


showUserInfo();
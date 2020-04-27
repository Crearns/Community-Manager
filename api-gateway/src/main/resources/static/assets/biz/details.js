function showUserInfo() {
    userInfo = getUser();
    $("#username").text(userInfo.realName);
}


var userInfo;
var apply = 0;


function showDetails() {
    const urlParams = new URLSearchParams(window.location.search);
    var communityId = urlParams.get('id');
    if (communityId === null) {
        location.href = "/error.html"
    }

    $.ajax({
        url: "web/community/communityDetails",
        data: {
            communityId: communityId
        },
        dataType: "json",
        type:"get",
        success: function (res) {
            if (res.code === 0) {
                $("#community_id").val(res.data.id);
                $("#community_name").val(res.data.name);
                $("#community_catalog").val(res.data.catalogName);
                $("#community_create").val(res.data.gmtCreate);
                $("#community_num").val(res.data.historyNum);
                $("#community_num_now").val(res.data.memberCount);
                $("#community_president").val(res.data.president);
                $("#community_description").val(res.data.description);
            } else if (res.code === 2){
                location.href = "/error.html"
            }
        },
        error: function (err) {
            alert(JSON.stringify(err))
        }
    })
}

function showWindow() {
    const urlParams = new URLSearchParams(window.location.search);
    var communityId = urlParams.get('id');
    if (communityId == null) location.href="error.html";
    $.ajax({
        url: "web/community/newsWindow",
        type: "get",
        dataType: "json",
        data: {
            communityId: communityId,
            visible: 1
        },
        success: function (res) {
            if (res.code === 0) {
                $.each(res.data, function (idx, val) {
                    detailsJSON = b64Encode(JSON.stringify(val));
                    str = "<tr>\n" +
                        "<td>" +
                        "<h3><a href='newsDetails.html?content="+detailsJSON+"'>"+val.title+"</a></h3>" +
                        "<h4>发表时间： "+dateFormat(val.gmtCreate)+" 最后编辑时间： "+dateFormat(val.gmtModified)+"</h4>\n" +
                        "<h4>作者："+val.author+"</h4>\n" +
                        "</td>" +
                        "<tr/>";

                    $("#windowList").append(str)
                })
            }
        }, error: function (err) {
            alert(JSON.stringify(err));
        }
    })
}

function showApply() {
    if (apply === 1) return;

    apply = 1;
    $("#edit-profile").append("" +
        "<div class='control-group'>\n" +
        "<label class='control-label' for='apply_content'>社团申请说明:</label>\n" +
        "<div class='controls'>\n" +
        "<textarea id='apply_content' style='height: 265px; width: 960px;resize:none'></textarea>\n" +
        "</div>\n" +
        "</div>")
    
    $("#control").append("" +
        "<a class='btn btn-success' onclick='submit()'>提交申请</a>" +
        "<span id='info' style='color: red'></span>")

}

function submit() {
    const urlParams = new URLSearchParams(window.location.search);
    var communityId = urlParams.get('id');
    if (communityId == null) location.href="error.html";

    var applyContent = $("#apply_content").val();
    if (applyContent.length == 0) {
        $("#info").text("输入信息不能为空");
        return;
    }

    if (applyContent.length >= 1500) {
        $("#info").text("输入信息长度超过限制，请修改后重试");
        return;
    }
    $("#info").text("");
    if (!confirm("提交后将会由社团人员进行审核，确认提交吗?")) {
        return;
    }
    $.ajax({
        url: "web/community/communityParticipation",
        dataType: "json",
        type: "post",
        data: {
            communityId: communityId,
            userId: userInfo.id,
            content: applyContent
        },
        success: function (res) {
            if (res.code === 0) {
                alert("申请成功，请耐心等待审核");
                location.href="details.html?id=" + communityId;
            } else {
                $("#info").text(res.msg);
            }
        }
    })
}


showUserInfo();
showDetails();
showWindow();
var userInfo;

function showUserInfo() {
    userInfo = getUser();
    $("#username").text(userInfo.realName);
}


function showCommunityInfo() {
    const urlParams = new URLSearchParams(window.location.search);
    var communityId = urlParams.get('id');
    if (communityId == null) location.href="error.html";
    var role;
    $.ajax({
        url: "web/community/memberShip",
        data:{
            communityId: communityId,
            userId: userInfo.id
        },
        async: false,
        dataType: "json",
        type: "get",
        success: function (res) {
            if (res.code === 3){
                location.href="error.html"
            }
            role = res.data
        }, error: function (err) {
            alert(JSON.stringify(err));
            location.href="error.html"
        }
    });

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
                $("#community_name_title").text(res.data.name);
                $("#community_catalog").val(res.data.catalogName);
                $("#community_create").val(dateFormat(res.data.gmtCreate));
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

function saveDesc() {
    const urlParams = new URLSearchParams(window.location.search);
    var communityId = urlParams.get('id');
    if (communityId == null) location.href="error.html";
    var role = -1;
    $.ajax({
        url: "web/community/memberShip",
        data:{
            communityId: communityId,
            userId: userInfo.id
        },
        async: false,
        dataType: "json",
        type: "get",
        success: function (res) {
            if (res.code === 3){
                location.href="error.html"
            }
            role = res.data
        }, error: function (err) {
            alert(JSON.stringify(err));
            location.href="error.html"
        }
    });

    if (role === -1) {
        location.href="error.html"
    }

    if (role === 3) {
        $("#info").text("您无权修改此信息");
        return;
    }

    var description = $("#community_description").val();
    if (description.length > 800) {
        $("#info").text("社团介绍不能超过800字");
        return;
    }

    if (description.length === 0) {
        $("#info").text("社团介绍不能为空");
        return;
    }

    $("#info").text("");
    if (!confirm("确定保存吗?")) {
        return;
    }

    $.ajax({
        url: "web/community/communityDescription",
        type: "put",
        dataType: "json",
        async: false,
        data: {
            communityId: communityId,
            description: description
        },
        success: function (res) {
            if (res.code === 0) {
                alert("信息已保存")
            }
        } ,error: function (err) {
            $("#info").text("信息修改失败，请通知管理员")
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
            userId: userInfo.id,
            visible: 0
        },
        success: function (res) {
            if (res.code === 0) {
                $.each(res.data, function (idx, val) {
                    detailsJSON = b64Encode(JSON.stringify(val));
                    str = "<tr>\n" +
                        "<td>\n" +
                        "<h3><a href='newsDetails.html?content="+detailsJSON+"'>"+val.title+"</a></h3>\n" +
                        "<h4>发表时间： "+dateFormat(val.gmtCreate)+" 最后编辑时间： "+dateFormat(val.gmtModified)+"</h4>\n" +
                        "<h4>作者："+val.author+"</h4>\n" +
                        "</td>\n" +
                        "</tr>";

                    $("#windowList").append(str)
                })
            }
        }, error: function (err) {
            alert(JSON.stringify(err));
        }
    })
}

function edit() {
    const urlParams = new URLSearchParams(window.location.search);
    var communityId = urlParams.get('id');
    if (communityId == null) location.href="error.html";

    location.href = "edit_news.html?id=" + communityId
}

function showApply() {
    const urlParams = new URLSearchParams(window.location.search);
    var communityId = urlParams.get('id');
    if (communityId == null) location.href="error.html";

    $.ajax({
        url: "web/worksheet/communityVerifyList",
        dataType: "json",
        type: "get",
        data: {
            communityId: communityId
        },
        success: function (res) {
            if (res.code === 0) {
                $.each(res.data,  function (idx, val) {
                    contentJson = b64Encode(JSON.stringify(val));
                    str = "<tr>\n" +
                        "<td><a href='applyDetails.html?content="+contentJson+"'>"+val.title+"</a></td>\n" +
                        "<td>"+dateFormat(val.create)+"</td>\n" +
                        "<td>"+dateFormat(val.modified)+"</td>\n" +
                        "<td>"+val.submitName+"</td>\n" +
                        "<td><a class='btn' onclick='agree("+val.id+", "+val.catalog+")'>同意</a> <a class='btn btn-danger' onclick='disagree("+val.id+", "+val.catalog+")'>拒绝</a></td>\n" +
                        "</tr>";
                    $("#applyTable").append(str)
                })
            }
        }, error: function (err) {
        }
    })
}

function agree(id, catalog) {
    if (!confirm("此操作无法撤回，确认同意吗？")) {
        return;
    }




}


function disagree(id, catalog) {
    var name = prompt("请输入拒绝原因");

    if (!confirm("此操作无法撤回，确认拒绝吗？")) {
        return;
    }
}



showUserInfo();
showCommunityInfo();
showWindow();
showApply();
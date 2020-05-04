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
    });

    if (role === 1 || role === 2) {
        showApply();
    }
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
                    jsonString = JSON.stringify(val).replace("'", "`")
                        .replace("\"", "&quot;");
                    str = "<tr>\n" +
                        "<td>\n" +
                        "<h3><a onclick='showNewsDetails("+jsonString+")'>"+val.title+"</a></h3>\n" +
                        "<h4>发表时间： "+dateFormat(val.gmtCreate)+" 最后编辑时间： "+dateFormat(val.gmtModified)+"</h4>\n" +
                        "<h4>作者："+val.author+"</h4>\n" +
                        "<h4><a style='color: #e33e33' onclick='deleteNew("+val.id+", this)'>删除</a></h4>" +
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

function deleteNew(id, obj) {
    if (!confirm("确定删除本公告吗")) {
        return;
    }

    $.ajax({
        url: "/web/community/news/",
        type: "put",
        datatype: "json",
        data: {
            newsId: id,
            userId: userInfo.id
        },
        success: function (res) {
            if (res.code === 0) {
                alert("该公告已删除");
                var index = $(obj).parents("tr").index();
                $(obj).parents("tr").remove();
            } else {
                alert(res.msg);
            }
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
                        "<td><a class='btn' onclick='agree("+val.id+", this)'>同意</a> <a class='btn btn-danger' onclick='disagree("+val.id+", this)'>拒绝</a></td>\n" +
                        "</tr>";
                    $("#applyTable").append(str)
                })
            }
        }, error: function (err) {
        }
    })
}

function agree(id, obj) {
    if (!confirm("此操作无法撤回，确认同意吗？")) {
        return;
    }

    $.ajax({
        url: "web/office/worksheet",
        dataType: "json",
        type: "put",
        data: {
            worksheetId: id,
            auditId: userInfo.id,
            agree: 1
        },
        success: function (res) {
            if (res.code === 0) {
                alert("审核完成");
                var index = $(obj).parents("tr").index();
                $(obj).parents("tr").remove();
            } else {
                alert("审核过程中出现错误："+res.msg);
                location.reload();
            }
        },error: function (err) {
            alert("出现未知错误，请通知管理员");
        }
    })
    
}


function disagree(id, obj) {
    var reason = prompt("请输入拒绝原因");

    if (reason == null || reason.length === 0) return;

    if (!confirm("此操作无法撤回，确认拒绝吗？")) {
        return;
    }

    $.ajax({
        url: "web/office/worksheet",
        dataType: "json",
        type: "put",
        data: {
            worksheetId: id,
            auditId: userInfo.id,
            agree: 0,
            reason: reason
        },
        success: function (res) {
            if (res.code === 0) {
                alert("审核完成");
                var index = $(obj).parents("tr").index();
                $(obj).parents("tr").remove();
            } else {
                alert("审核过程中出现错误："+res.msg);
                location.reload();
            }
        },error: function (err) {
            alert("出现未知错误，请通知管理员");
        }
    })
}

function showMember() {
    const urlParams = new URLSearchParams(window.location.search);
    var communityId = urlParams.get('id');
    if (communityId == null) location.href="error.html";

    $.ajax({
        url: "web/community/communityMember",
        dataType: "json",
        type: "get",
        data: {
            communityId: communityId
        },
        success: function (res) {
            if (res.code === 0) {
                $.each(res.data, function (idx, val) {
                    str = "<tr>\n" +
                        "<td>"+val.userNum+"</td>\n" +
                        "<td>"+val.realName+"</td>\n" +
                        "<td>"+val.grade+"</td>\n" +
                        "<td>"+val.college+"</td>\n" +
                        "<td>"+val.major+ val.classNum +"班</td>\n" +
                        "<td>"+val.roleName+"</td>\n" +
                        "<td><li class='dropdown'><a class='dropdown-toggle' data-toggle='dropdown'><b class='caret'></b></a>\n" +
                        "<ul class='dropdown-menu'>\n" +
                        "<li><a onclick='roleChange("+val.userNum+", 2)'>设为部长</a></li>\n" +
                        "<li><a onclick='roleChange("+val.userNum+", 3)'>设为社员</a></li>\n" +
                        "<li><a onclick='roleChange("+val.userNum+", 1)'>设为社长</a></li>\n" +
                        "<li><a onclick='roleChange("+val.userNum+", 4)' style='color: red'>移出社团</a></li>\n" +
                        "</ul>\n" +
                        "</li></td>\n" +
                        "</tr>";
                    $("#memberTable").append(str);
                })
            }
        }, error: function (err) {
            alert(JSON.stringify(err))
        }
    })
}

function roleChange(userNum, role) {
    const urlParams = new URLSearchParams(window.location.search);
    var communityId = urlParams.get('id');

    if (!confirm("此操作不能撤回，确认执行吗?")) {
        return;
    }

    $.ajax({
        url: "web/community/roleChange",
        dataType: "json",
        type: "put",
        data: {
            executeId: userInfo.id,
            userNum: userNum,
            communityId: communityId,
            roleId: role
        },
        success: function (res) {
            if (res.code === 0) {
                alert("操作成功");
                location.reload();
            } else {
                alert(res.msg);
            }
        }, error: function (err) {
        }
    })
}

function quit() {
    if (!confirm("退出社团操作无法撤回，加入社团需要重新申请，确定要继续吗？")) {
        return;
    }
    const urlParams = new URLSearchParams(window.location.search);
    var communityId = urlParams.get('id');

    $.ajax({
        url: "/web/community/quit",
        type: "put",
        datatype: "json",
        async: false,
        data: {
            communityId: communityId,
            userId: userInfo.id
        },
        success: function (res) {
            if (res.code === 0) {
                alert(res.data)
            } else {
                alert(res.msg);
            }
            location.href="my_community.html";
        },
        error: function (err) {

        }
    })
}

function logoutCommunity() {
    if (!confirm("注销社团操作无法撤回，确定要继续吗？")) {
        return;
    }
    const urlParams = new URLSearchParams(window.location.search);
    var communityId = urlParams.get('id');

    $.ajax({
        url:"web/community/community",
        datatype: "json",
        type: "put",
        data:{
            communityId: communityId,
            userId: userInfo.id
        },
        success: function (res) {
            if (res.code === 0) {
                alert("此社团已注销");
                location.href = "my_community.html"
            } else {
                alert(res.msg);
            }
        }

    })

}

showUserInfo();
showCommunityInfo();
showWindow();
showMember();
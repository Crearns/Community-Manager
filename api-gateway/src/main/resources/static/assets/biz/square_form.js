function showUserInfo() {
    var userInfo = getUser();
    $("#username").text(userInfo.realName);
}



function showCatalog() {
    $.ajax({
        type: "get",
        dataType: "json",
        url: "web/community/catalogList",
        success: function (res) {
            if (res.code === 0) {
                $.each(res.data, function (idx, val) {
                    str = "<option id='"+val.id+"'>"+val.catalogName+"</option>";
                    $("#community_catalog").append(str)
                })
            } else {
                location.href = "/error.html"
            }
        }, error: function (err) {
            location.href = "/error.html"
        }
    })
}


function submit_apply() {
    var userInfo = getUser();
    if (validate() && confirm("提交后将给大连海事大学社团联合会进行审核，确认提交吗?")) {
        $.ajax({
            type: "post",
            dataType: "json",
            url: "web/community/communityFound",
            data: {
                name: $("#community_name").val(),
                id: userInfo.id,
                catalog: $("#community_catalog option:selected").attr("id"),
                description: $("#community_description").val()
            },
            success: function (res) {
                if (res.code === 0) {
                    alert("社团申请成功，目前正在社联审核中，请耐心等待")
                    location.href = "/square.html"
                } else {
                    location.href = "/error.html"
                }
            }, error: function (err) {
                alert(JSON.stringify(err))
            }
        })
    }

}


function validate() {
    var name = $("#community_name").val();
    var catalogId = $("#community_catalog").val();
    var desc = $("#community_description").val();

    if (desc.length > 500) {
        $("#info").text("社团介绍请在500字以内");
        return false;
    }

    if (name == "" || catalogId == "" || desc == "") {
        $("#info").text("请输入完整信息");
        return false;
    } else {
        $("#info").text("");
        return true;
    }



}

showUserInfo();
showCatalog();
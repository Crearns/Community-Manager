function showUserInfo() {
    var userInfo = getUser();
    $("#username").text(userInfo.realName);


    $("#setting_username").val(userInfo.userId);
    $("#setting_userNum").val(userInfo.userNum);
    $("#setting_realName").val(userInfo.realName);
    $("#setting_class").val(userInfo.college + userInfo.grade + userInfo.major + userInfo.classNum + "班");
    $("#setting_phone").val(userInfo.phone);
    $("#setting_email").val(userInfo.email);
    $("#setting_nickname").val(userInfo.nickname);

}

function saveInfo() {
    if (!confirm("确认保存吗？")) {
        return;
    }

    var userInfo = getUser();

    var phone = $("#setting_phone").val();
    var email = $("#setting_email").val();
    var nickname = $("#setting_nickname").val();

    $.ajax({
        url: "web/user/info",
        datatype: "json",
        type: "PUT",
        data: {
            userId: userInfo.id,
            phone: phone,
            email: email,
            nickname: nickname
        },
        success: function (res) {
            if (res.code === 0) {
                alert("信息已保存");
            }
        }, error: function (err) {
            alert("出现未知错误，请通知管理员");
        }
    })
}

function passwordChange() {
    if (!confirm("确认提交吗？")) {
        return;
    }
    var passwordOld = $("#password_old").val();
    var passwordNew = $("#password_new").val();
    var passwordConfirm = $("#password_confirm").val();

    if (passwordOld === null || passwordNew === null || passwordConfirm === null) {
        $("#info").text("请输入完整信息");
        return;
    }

    if (passwordNew !== passwordConfirm) {
        $("#info").text("两次密码输入不一致");
        return;
    }

    $("#info").text("");

    var userInfo = getUser();

    $.ajax({
        url: "web/user/password",
        type: "put",
        datatype: "json",
        data: {
            userId: userInfo.id,
            passwordNew: passwordNew,
            passwordOld: passwordOld
        },
        success: function (res) {
            if (res.code === 0) {
                $("#info").text("");
                alert("修改成功，下次登录后会生效");
            } else {
                $("#info").text(res.msg);
            }
        }, error: function (err) {
            alert("出现未知错误，请通知管理员");
        }
    })

}

showUserInfo();
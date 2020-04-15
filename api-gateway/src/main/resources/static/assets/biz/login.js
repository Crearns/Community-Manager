function login(obj) {
    $(obj).attr("disabled", true);

    var username = $.trim($('#username').val());
    var password = $.trim($('#password').val());
    if (username == "" || password == "") {
        $("#info").html('用户名或者密码不能为空');
        $(obj).attr("disabled", false);
    } else {
        $.ajax({
            type : 'post',
            url : domainName + '/sys/login',
            timeout : 7000, //超时时间设置，单位毫秒
            data : {
                username: username,
                password: password
            },
            success : function(data) {
                //将access_token和refresh_token写入本地
                localStorage.setItem("access_token", data.access_token);
                localStorage.setItem("refresh_token", data.refresh_token);
                location.href = 'main.html';
            },
            error : function(xhr, textStatus, errorThrown) {
                $(obj).attr("disabled", false);
                if(textStatus == 'timeout') {
                    $("#info").html("登陆超时,请重试");
                    return;
                }
                var msg = xhr.responseText;
                if(msg == undefined){
                    $("#info").html("请重试");
                } else {
                    var response = JSON.parse(msg);
                    var message = response.error_description;
                    if(message == undefined){
                        message = response.message;
                    }
                    $("#info").html(message);
                }
            }
        });

    }
}
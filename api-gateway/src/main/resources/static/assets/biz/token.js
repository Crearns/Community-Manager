$.ajaxSetup({
    cache : false,
    url: "uaa/user/current",
    headers : {
        "Authorization" : "Bearer " + localStorage.getItem("access_token")
    },
    error : function(xhr, textStatus, errorThrown) {
        var status = xhr.status; // http status
        var msg = xhr.responseText;
        var message = "";
        if(msg != undefined && msg != ""){
            console.log(msg)
            var response = JSON.parse(msg);
            var exception = response.exception;
            if(exception){
                message = exception;
            }else{
                message = response.message;
            }
        }

        var flag = typeof(layer)=="undefined";

        if (status == 400) {
            if (flag) {
                alert(message);
            } else {
                layer.msg(message);
            }
        } else if (status == 401) {
            console.log('access_token过期');
            localStorage.removeItem("access_token");
            localStorage.removeItem("refresh_token");
            location.href = loginPage;
        } else if (status == 403) {
            message = "未授权";
            if (flag) {
                alert(message);
            } else {
                layer.msg(message);
            }
        } else if (status == 500) {
            message = '系统错误：' +  message + '，请刷新页面，或者联系管理员';
            if (flag) {
                alert(message);
            } else {
                layer.msg(message);
            }
        }
    }
});

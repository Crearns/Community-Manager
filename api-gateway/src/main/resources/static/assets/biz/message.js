var userInfo;

function showUserInfo() {
    userInfo = getUser();
    $("#username").text(userInfo.realName);
}

function showMessage() {
    $.ajax({
        url: "/web/message/message",
        datatype: "json",
        type: "get",
        data: {
            userId: userInfo.id
        },
        success: function (res) {
            if (res.code === 0) {
                $.each(res.data, function (idx, val) {
                    if (val.read === false) {
                        tip = "<span style='position: relative'><i class='tip'></i></span>"
                    } else {
                        tip = ""
                    }
                    str =  "<tr>\n" +
                        "<td><h3>"+tip+"<a href='#'>"+ val.title +" </a></h3>" +
                        "<h4>123</h4>" +
                        "</td>\n" +
                        "</tr>";
                    $("#messageList").append(str);
                });
            }
        }, error: function (err) {

        }
    })
}


function readAll() {
    if (!confirm("确定将所有消息设为已读吗？")) {
        return;
    }

    $.ajax({
        url: "web/message/allReadMark",
        datatype: "json",
        type: "put",
        data: {
            userId: userInfo.id
        },
        success: function (res) {
            if (res.code === 0) {
                location.href = "message.html"
            }
        }
    })
}




showUserInfo();
showMessage();
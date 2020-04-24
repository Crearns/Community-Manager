var userInfo;

function showUserInfo() {
    userInfo = getUser();
    $("#username").text(userInfo.realName);
}


function apply() {
    $.ajax({
        url: "web/worksheet/verifyInfo",
        type: "get",
        dataType: "json",
        data: {
            id: userInfo.id
        },
        success: function (res) {
            if (res.code === 0) {
                $.each(res.data, function (idx, val) {
                    detailsJSON = b64Encode(JSON.stringify(val));
                    if (val.state === "申请失败") style = "color: red";
                    else style = "color: green";
                    str = "<tr>\n" +
                        "<td><a href='applyDetails.html?content="+detailsJSON+"'>"+val.title+"</a></td>\n" +
                        "<td>"+dateFormat(val.create)+"</td>\n" +
                        "<td>"+dateFormat(val.modified)+"</td>\n" +
                        "<td style='"+style+"'>"+val.state+"</td>\n" +
                        "</tr>";

                    $("#verifyTable").append(str)
                })
            }
        }, error: function (err) {
            alert(JSON.stringify(err))
        }
    })
}

function b64Encode(str) {
    return btoa(encodeURIComponent(str));
}

showUserInfo();
apply();
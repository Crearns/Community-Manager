var userInfo;

function showUserInfo() {
    userInfo = getUser();
    $("#username").text(userInfo.realName);
}


function apply() {
    $.ajax({
        url: "web/worksheet/worksheetInfo",
        type: "get",
        dataType: "json",
        data: {
            id: userInfo.id
        },
        success: function (res) {
            if (res.code === 0) {
                $.each(res.data, function (idx, val) {
                    str = "<tr>\n" +
                        "<td><a href='applyDetails.html'>"+val.title+"</a></td>\n" +
                        "<td>"+dateFormat(val.create)+"</td>\n" +
                        "<td>"+dateFormat(val.modified)+"</td>\n" +
                        "<td>"+val.state+"</td>\n" +
                        "</tr>";

                    $("#applyTable").append(str)
                })
            }
        }, error: function (err) {
            alert(JSON.stringify(err))
        }
    })
}


showUserInfo();
apply();
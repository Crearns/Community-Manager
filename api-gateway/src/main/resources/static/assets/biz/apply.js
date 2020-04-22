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
        success: function () {

        }, error: function () {

        }
    })
}



showUserInfo();
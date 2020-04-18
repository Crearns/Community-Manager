function showUserInfo() {
    var userInfo = getUser();
    $("#username").text(userInfo.realName);
}


function showDetails() {
    const urlParams = new URLSearchParams(window.location.search);
    var communityId = urlParams.get('id');
    if (communityId === null) {
        location.href = "/error.html"
    }

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
                $("#community_catalog").val(res.data.catalogName);
                $("#community_create").val(res.data.gmtCreate);
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
showUserInfo();
showDetails();
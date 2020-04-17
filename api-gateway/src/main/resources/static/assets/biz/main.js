function logout(){
    location.href="/logout"
}



function showUserInfo() {
    var userInfo = getUser();
    $("#username").text(userInfo.realName);
    $("#profile_realName").text(userInfo.realName);
    $("#profile_username").text(userInfo.username);

    if (userInfo.email != null) {
        $("#profile_mail").text(userInfo.email);
    }

    if (userInfo.phone != null) {
        $("#profile_phone").text(userInfo.phone);
    }

    if (userInfo.gender === 0) {
        $("#profile_gender").text("男")
    } else if (userInfo.gender === 1) {
        $("#profile_gender").text("女")
    }

    $("#profile_nickname").text(userInfo.nickname);
    $("#major_class").text(userInfo.college + " " + userInfo.major + " " + userInfo.grade + "级" + userInfo.major + userInfo.classNum+"班" )
}

showUserInfo();
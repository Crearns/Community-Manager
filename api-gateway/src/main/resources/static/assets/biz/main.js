function showUserInfo() {
    var userInfo = getUser();
    $("#username").text(userInfo.realName);


//     <h1><span id="profile_username"></span></h1><br>
//     <h4><span id="profile_realName"></span></h4><br>
//     <h4><span id="profile_gender"></span></h4><br>
//     <h4><span id="profile_nickname"></span></h4><br>
//     <h4>大连海事大学</h4><br>
//     <h4><span id="major_class"></span></h4><br>
//     <h4><span id="profile_mail"></span></h4><br>
//     <h4><span id="profile_phone"></span></h4><br>
//     <button class="btn-large">编辑</button>

    var gender;
    if (userInfo.gender === 0)
        gender = '男';
    else
        gender = '女';

    var email;
    if (userInfo.email != null)
        email = userInfo.email;
    else
        email = '';


    $("#profile").append("<h1><span> "+userInfo.username+"</span></h1><br>");
    $("#profile").append("<h4><span><i class='icon-user'/> "+userInfo.realName+"</span></h4><br>");
    $("#profile").append("<h4><span><i class='icon-heart'/> "+ gender +"</span></h4><br>");
    $("#profile").append("<h4><span><i class='icon-paper-clip'/> "+userInfo.nickname+"</span></h4><br>");
    $("#profile").append("<h4><span><i class='icon-pencil'/> 大连海事大学</span></h4><br>");
    $("#profile").append("<h4><span><i class='icon-group'/> " + userInfo.college + "</span></h4><br>");
    $("#profile").append("<h4><span><i class='icon-star'/> "+ userInfo.grade + "级" + userInfo.major + userInfo.classNum+"班" +"</span></h4><br>");
    $("#profile").append("<h4><span><i class='icon-envelope'/> "+ email +"</span></h4><br>");
    $("#profile").append("<a href='setting.html' class='btn span2'><i class='icon-cog'>编辑</a>")


    // $("#profile_realName").text(userInfo.realName);
    // $("#profile_username").text(userInfo.username);
    //
    // if (userInfo.email != null) {
    //     $("#profile_mail").text(userInfo.email);
    // }
    //
    // if (userInfo.phone != null) {
    //     $("#profile_phone").text(userInfo.phone);
    // }
    //
    // if (userInfo.gender === 0) {
    //     $("#profile_gender").text("男")
    // } else if (userInfo.gender === 1) {
    //     $("#profile_gender").text("女")
    // }
    //
    // $("#profile_nickname").text(userInfo.nickname);
    // $("#major_class").text(userInfo.college + " " + userInfo.major + " " + userInfo.grade + "级" + userInfo.major + userInfo.classNum+"班" )
}
showUserInfo();
// function logout(){
//     $.ajax({
//         type : 'post',
//         url : domainName + '/sys/logout',
//         headers: {
//             Authorization: "Bearer "+ localStorage.getItem("access_token")
//         },
//         data: {
//             access_token: localStorage.getItem("access_token")
//         },
//         success : function(data) {
//             localStorage.removeItem("access_token");
//             localStorage.removeItem("refresh_token");
//             location.href = loginPage;
//         }, error: function () {
//             location.href = loginPage;
//         }
//     });
// }
//
//
//
// function showUserInfo() {
//     var userInfo = getUser();
//     $("#username").text(userInfo.realName);
//     $("#profile_realName").text(userInfo.realName);
//     $("#profile_username").text(userInfo.username);
//     $("#profile_mail").text(userInfo.email);
//     $("#profile_phone").text(userInfo.phone)
//     $("#profile_nickname").text(userInfo.nickname);
//     $("#major_class").text(userInfo.college + " " + userInfo.major + " " + userInfo.grade + "级" + userInfo.major + userInfo.classNum+"班" )
// }
//
// function checkToken() {
//     access = localStorage.getItem("access_token");
//     refresh = localStorage.getItem("refresh_token");
//
//     if (access === null || refresh === null) {
//         location.href = loginPage;
//     }
// }
//
// checkToken();
// showUserInfo();
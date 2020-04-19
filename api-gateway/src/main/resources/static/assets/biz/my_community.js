function showUserInfo() {
    var userInfo = getUser();
    $("#username").text(userInfo.realName);
}

function showMyCommunity() {
    $.ajax({
        url: "web/community/myCommunity",
        dataType: "json",
        type: "get",
        success: function (res) {
            if (res.code === 0) {
                var num = 0;
                $.each(res.data, function (idx, val) {
                    str = "<tr>\n" +
                        "\t<td> <h3><a>"+ val.name +" </a></h3>" +
                        "<h4>职务: "+val.roleName+"</h4>" +
                        "<h5>"+val.description+"</h5>" +
                        "</td>\n" +
                        "\t</tr>";
                    $("#myCommunityList").append(str)
                    num++;
                })
            }
        }

    });
}

// <tr>
// <div class="widget">
//     <div class="widget-content">
//     <h3><a> 海风学社 </a></h3>
// <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
// </div> <!-- /widget-content -->
// </div> <!-- /widget -->
// </tr>


showUserInfo();

showMyCommunity()

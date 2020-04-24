function showUserInfo() {
    var userInfo = getUser();
    $("#username").text(userInfo.realName);
}

function showSquareList() {
    const urlParams = new URLSearchParams(window.location.search);
    var page = urlParams.get('page');
    if (page === null) {
        page = 1;
    }
    $.ajax({
        url: "web/community/squareList",
        data: {
            currentPage: page
        },
        dataType: "json",
        type:"get",
        success: function (res) {
            if (res.code === 0) {
                $.each(res.data, function (idx, val) {
                    var president = val.president;
                    if (president === null)
                        president = "无";
                    str = "<tr>\n" +
                        "\t<td> "+ val.id +" </td>\n" +
                        "\t<td> <a href='details.html?id=" + val.id + "'>"+ val.name +"</a> </td>\n" +
                        "\t<td> "+ val.catalogName +" </td>\n" +
                        "\t<td> "+ dateFormat(val.gmtCreate) +" </td>\n" +
                        "\t<td> "+ president +" </td>\n" +
                        "\t<td> "+ val.historyNum +" </td>\n" +
                        "\t</tr>";
                    $("#squareList").append(str);
                });
            }
        },
        error: function (err) {
            // alert(JSON.stringify(err))
        }
    })
}
showUserInfo();
showSquareList();
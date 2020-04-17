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
        url: "/community/squareList",
        data: {
            currentPage: page
        },
        dataType: "json",
        type:"get",
        success: function (res) {
            if (res.code === 0) {
                var num = 0;
                $.each(res.data, function (idx, val) {
                    str = "<tr>\n" +
                        "\t<td> "+ val.id +" </td>\n" +
                        "\t<td> <a>"+ val.name +"</a> </td>\n" +
                        "\t<td> "+ val.catalogName +" </td>\n" +
                        "\t<td> "+ dateFormat(val.gmtCreate) +" </td>\n" +
                        "\t<td> "+ val.president +" </td>\n" +
                        "\t<td> "+ val.historyNum +" </td>\n" +
                        "\t</tr>\n" +
                        "<tr>"
                    $("#squareList").append(str);
                    num++;
                });

                for (let i = num+1; i <= 20; i++) {
                    str = "<tr>\n" +
                        "\t<td> " + " " + " </td>\n" +
                        "\t<td>  </td>\n" +
                        "\t<td>  </td>\n" +
                        "\t<td>  </td>\n" +
                        "\t<td>  </td>\n" +
                        "\t<td>  </td>\n" +
                        "\t</tr>\n" +
                        "<tr>";
                    $("#squareList").append(str);
                }
            }
        },
        error: function (err) {
            alert(JSON.stringify(err))
        }
    })
}
showUserInfo();
showSquareList();
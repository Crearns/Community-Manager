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
                showPageInfo(res.msg, page);
            } else {
                alert(res.msg)
            }
        },
        error: function (err) {
            // alert(JSON.stringify(err))
        }
    })
}

function showPageInfo(page, currentPage) {
    page = parseInt(page);
    currentPage = parseInt(currentPage);

    if (currentPage > 1 && currentPage <= page) {
        pre = "<a href='square.html?page="+(currentPage-1)+"'>上一页</a>"
    } else {
        pre = ""
    }

    if (currentPage >= page || currentPage <= 0) {
        next = ""
    } else {
        next = "<a href='square.html?page="+(currentPage+1)+"'>下一页</a>"
    }

    $("#page-span").append("<a href='square.html'>首页</a>");
    $("#page-span").append(pre+" 第"+currentPage+"页，共"+page+"页 "+next);
    $("#page-span").append("<a href='square.html?page="+page+"'>尾页</a>");
}


showUserInfo();
showSquareList();
function showNewsDetails(obj) {
    obj = JSON.parse(JSON.stringify(obj));
    layer.open({
        type: 0,
        title: '公告详情',
        content: detailTable(obj),
        resize: true,
        scrollbar: true,
        area: ['1145px', '720px'],
        maxmin: true,
        moveOut: true
    });
}

function detailTable(obj) {
    str = "<div class='widget-content'><br>\n" +
        "<form id='community-profile' class='form-horizontal'>\n" +
        "<div class='control-group'>\n" +
        "<label class='control-label' for='news_title'>主题:</label>\n" +
        "<div class='controls'>\n" +
        "<input type='text' class='span9 disabled' id='news_title' value='"+obj.title+"' disabled=''>\n" +
        "</div> <!-- /controls -->\n" +
        "</div> <!-- /control-group -->\n" +
        "\n" +
        "<div class='control-group'>\n" +
        "<label class='control-label' for='news_create'>创建时间:</label>\n" +
        "<div class='controls' >\n" +
        "<input class='span9' id='news_create' value='"+dateFormat(obj.gmtCreate)+"' disabled=''/>\n" +
        "</div> <!-- /controls -->\n" +
        "</div> <!-- /control-group -->\n" +
        "\n" +
        "<div class='control-group'>\n" +
        "<label class='control-label' for='news_modified' disabled=''>最后编辑时间:</label>\n" +
        "<div class='controls' >\n" +
        "<input class='span9' id='news_modified' value='"+dateFormat(obj.gmtModified)+"' disabled=''/>\n" +
        "\n" +
        "</div> <!-- /controls -->\n" +
        "</div> <!-- /control-group -->\n" +
        "\n" +
        "<div class='control-group'>\n" +
        "<label class='control-label' for='news_author' disabled=''>编辑作者:</label>\n" +
        "<div class='controls' >\n" +
        "<input class='span9' id='news_author' value='"+obj.author+"' disabled=''/>\n" +
        "\n" +
        "</div> <!-- /controls -->\n" +
        "</div> <!-- /control-group -->\n" +
        "\n" +
        "<div class='control-group'>\n" +
        "<label class='control-label' for='news_content'>公告内容:</label>\n" +
        "<div class='controls'>\n" +
        "<textarea style='margin: 0px; width: 860px; height: 746px;resize:none;' id='news_content' disabled=''>"+obj.content+"</textarea>\n" +
        "</div> <!-- /controls -->\n" +
        "</div> <!-- /control-group -->\n" +
        "</form>\n" +
        "</div>";

    return str;
}


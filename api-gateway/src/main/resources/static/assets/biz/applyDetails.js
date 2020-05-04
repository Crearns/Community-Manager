function showDetails(obj) {
    obj = JSON.parse(JSON.stringify(obj));
    layer.open({
        type: 0,
        title: '申请详情',
        content: detailTable(obj),
        resize: true,
        scrollbar: true,
        area: ['1145px', '720px'],
        maxmin: true,
        moveOut: true
    });
}

function detailTable(obj) {
    if (obj.auditName == null) {
        obj.auditName = ""
    }

    str = "<div class='widget-content'><br>\n" +
        "<form id='community-profile' class='form-horizontal'>\n" +
        "<div class='control-group'>\n" +
        "<label class='control-label' for='sheet_title'>申请主题:</label>\n" +
        "<div class='controls'>\n" +
        "<input type='text' class='span8 disabled' value='"+obj.title+"' id='sheet_title' disabled=''>\n" +
        "</div> <!-- /controls -->\n" +
        "</div> <!-- /control-group -->\n" +
        "<div class='control-group'>\n" +
        "<label class='control-label' for='sheet_create'>申请时间:</label>\n" +
        "<div class='controls' >\n" +
        "<input class='span8' id='sheet_create' value='"+dateFormat(obj.create)+"' disabled=''/>\n" +
        "</div> <!-- /controls -->\n" +
        "</div> <!-- /control-group -->\n" +
        "<div class='control-group'>\n" +
        "<label class='control-label' for='sheet_modified' disabled=''>更新时间:</label>\n" +
        "<div class='controls' >\n" +
        "<input class='span8' id='sheet_modified' value='"+dateFormat(obj.modified)+"' disabled=''/>\n" +
        "</div> <!-- /controls -->\n" +
        "</div> <!-- /control-group -->\n" +
        "<div class='control-group'>\n" +
        "<label class='control-label' for='sheet_submit' disabled=''>申请人姓名:</label>\n" +
        "<div class='controls' >\n" +
        "<input class='span8' id='sheet_submit' value='"+obj.submitName+"' disabled=''/>\n" +
        "</div> <!-- /controls -->\n" +
        "</div> <!-- /control-group -->\n" +
        "<div class='control-group'>\n" +
        "<label class='control-label' for='sheet_audit' disabled=''>审核人姓名:</label>\n" +
        "<div class='controls' >\n" +
        "<input class='span8' id='sheet_audit' value='"+obj.auditName+"' disabled=''/>\n" +
        "</div> <!-- /controls -->\n" +
        "</div> <!-- /control-group -->\n" +
        "<div class='control-group'>\n" +
        "<label class='control-label' for='sheet_content'>申请单内容:</label>\n" +
        "<div class='controls'>\n" +
        "<textarea style='margin: 0px; width: 760px; height: 346px;resize:none;' id='sheet_content' disabled=''>"+obj.content+"</textarea>\n" +
        "</div> <!-- /controls -->\n" +
        "</div> <!-- /control-group -->\n" +
        "<div class='control-group'>\n" +
        "<label class='control-label' for='sheet_remark'>备注:</label>\n" +
        "<div class='controls'>\n" +
        "<textarea style='margin: 0px; width: 760px; height: 346px;resize:none;' id='sheet_remark' disabled=''>"+obj.remark+"</textarea>\n" +
        "</div> <!-- /controls -->\n" +
        "</div> <!-- /control-group -->\n" +
        "</form>\n" +
        "</div>";

    return str;
}


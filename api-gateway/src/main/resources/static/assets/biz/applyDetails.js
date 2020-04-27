function showUserInfo() {
    userInfo = getUser();
    $("#username").text(userInfo.realName);
}


function showContent() {
    const urlParams = new URLSearchParams(window.location.search);
    var contentB64 = urlParams.get('content');
    if (contentB64 === null) {
        location.href = "/error.html"
    }
    try {
        content = JSON.parse(b64Decode(contentB64));
    } catch(exception) {
        location.href = "/error.html"
    }


    if (content == null) location.href = "/error.html";

    $("#sheet_title").val(content.title);
    $("#sheet_create").val(dateFormat(content.create));
    $("#sheet_modified").val(dateFormat(content.modified));
    $("#sheet_content").text(content.content);
    $("#sheet_submit").val(content.submitName);

    if (content.auditName != null) {
        $("#sheet_audit").val(content.auditName);
    }

    if (content.remark != null) {
        $("#sheet_remark").text(content.remark);
    }
}

function b64Decode(str) {
    return decodeURIComponent(atob(str));
}


showUserInfo();
showContent();
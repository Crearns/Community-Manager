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

    $("#news_title").val(content.title);
    $("#news_create").val(dateFormat(content.gmtCreate));
    $("#news_modified").val(dateFormat(content.gmtModified));
    $("#news_author").val(content.author);
    $("#news_content").text(content.content);
}


showUserInfo();
showContent();
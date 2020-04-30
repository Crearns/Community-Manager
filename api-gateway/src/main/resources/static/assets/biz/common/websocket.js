var websocket = null;
var userInfo = getUser();


if ('WebSocket' in window) {
    websocket = new WebSocket('ws://localhost:9003/message/webSocket/' + userInfo.id);
} else {
    alert('该浏览器不支持websocket!');
}


websocket.onopen = function (event) {
    console.log('Connect');
};


websocket.onclose = function (event) {
    console.log('Disconnected');
};

websocket.onmessage = function (event) {
    message = JSON.parse(event.data);
    if (message.data === 0) {
        $("#tip").css('visibility','hidden');
        $("#messageCount").text("");
    } else {
        $("#tip").css('visibility','visible');
        $("#messageCount").text("("+message.data+")");
    }
};


websocket.onerror = function () {
    alert('Websocket error');
};


window.onbeforeunload = function () {
    websocket.close();
};
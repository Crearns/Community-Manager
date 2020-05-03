function dateFormat(time) {
    var d = new Date(time);
    return d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
}


function dateFormatSec(time) {
    var d = new Date(time);
    return d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
}
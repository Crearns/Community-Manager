function dateFormat(time) {
    var d = new Date(time);
    return d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
}

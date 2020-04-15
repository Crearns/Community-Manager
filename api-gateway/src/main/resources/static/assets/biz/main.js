function logout(){
    $.ajax({
        type : 'post',
        url : domainName + '/sys/logout',
        headers: {
            Authorization: "Bearer "+ localStorage.getItem("access_token")
        },
        data: {
            access_token: localStorage.getItem("access_token")
        },
        success : function(data) {
            localStorage.removeItem("access_token");
            localStorage.removeItem("refresh_token");
            location.href = loginPage;
        }
    });
}
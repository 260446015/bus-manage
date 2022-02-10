function checkPassword() {
    var checkFlag = $("#password").val() === $("#password-check").val();
    if (checkFlag) {
        return true;
    } else {
        alert("两次密码不一致。");
        return false;
    }
}
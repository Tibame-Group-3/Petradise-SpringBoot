


$(document).ready(function () {
    $('#btn_signin')
        .on('click', onClickSignin);
});


function onClickSignin() {
    validateForm();
}

function validateForm() {
    const account = $('#account').val();
    const password = $('#password').val();

    if (!isAccountFormatValid(account)) {
        showAlert("帳號輸入錯誤，請再次檢查");
    }
}

function isAccountFormatValid(account) {

}

function showAlert(message) {
    $('.alert')
        .text(message)
        .removeClass('d-none');
}
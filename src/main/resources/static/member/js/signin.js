$(document).ready(function () {
    $('#btn_signin')
        .on('click', onClickSignin);
});


function onClickSignin(e) {
    e.preventDefault();
    validateForm();
}

function validateForm() {
    const account = $('#account').val().trim();
    const password = $('#password').val().trim();

    if (validateAccount(account) && validatePassword(password)) {
        signin(account, password);
    }
}

function validateAccount(account) {
    if (account === undefined || account === null || account === '') {
        showAlert('請輸入帳號！');
        return false;
    }
    hideAlert();
    return true;
}

function validatePassword(password) {
    if (password === undefined || password === null || password === '') {
        showAlert('請輸入密碼！');
        return false;
    }
    return true;
}

function showAlert(message) {
    $('.alert')
        .text(message)
        .addClass('d-block')
        .removeClass('d-none');
}

function hideAlert() {
    $('.alert')
        .addClass('d-none')
}

function signin(account, password) {
    const data = {
        account: account,
        password: password
    }

    const headers = {
        'content-type': 'application/json'
    }

    fetch('/members/login', {
        body: JSON.stringify(data),
        method: "POST",
        headers: headers
    })
        .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error(response.statusText);
            }
        )
        .then(json => saveMemberID(json.id))
        .catch(error => showAlert('帳號或密碼有誤'));
}

function saveMemberID(id) {
    localStorage.setItem('memberId', id);
    redirectToMainPage();
}

function redirectToMainPage() {
    window.location.href = '/';
}

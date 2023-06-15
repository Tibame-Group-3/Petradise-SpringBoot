(() => {
    $('.navigate-member-center')
        .on('click', onClickMemberCenter);
    $('.navigate-signin')
        .on('click', onClickSignin);
})();

function onClickMemberCenter(e) {
    e.preventDefault();
    navigateToSignup();
}

function onClickSignin(e) {
    e.preventDefault();
    navigateToSignin();
}

function navigateToSignup() {
    location.href = "/member/signup.html";
}

function navigateToSignin() {
    location.href = "/member/signin.html"
}
(() => {
    $('.navigate-member-center')
        .on('click', onClickMemberCenter);
})();

function onClickMemberCenter(e) {
    e.preventDefault();
    location.href = "/member/sign_up.html";
}
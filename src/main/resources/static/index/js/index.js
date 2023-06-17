$(document).ready(function () {
    const signedIn = isSignedIn();
    setupMemberElements(signedIn);
    setupNavigation(signedIn);
    $('.logout').on('click', logout);
})

function setupNavigation(isSignedIn) {
    setupMemberCenterNavigation(isSignedIn);
    setupSigninNavigation();
}

function isSignedIn() {
    const memberId = sessionStorage.getItem('memberId');
    console.log('memberId: ' + memberId);
    return memberId !== undefined && memberId !== null;
}

function setupMemberElements(isSignedIn) {
    if (isSignedIn) {
        $('.navigate-signin')
            .remove();
        return;
    }

    $('.navigate-member-center')
        .remove();
    $('.logout')
        .remove();

}

function setupMemberCenterNavigation(isSignedIn) {
    $('.navigate-member-center')
        .attr('href', '/member/signin.html');
}

function setupSigninNavigation() {
    $('.navigate-signin')
        .attr('href', '/member/signin.html');
}

function logout() {
    // console.log('logging out');
    sessionStorage.removeItem('memberId');
    // console.log(sessionStorage.getItem('memberId'));
    location.reload();
}
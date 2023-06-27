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
    const memberId = localStorage.getItem('memberId');
    return !!memberId; // Double negation turns a truthy or falsy value into actual true or false
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
        .attr('href', '/member/profile.html');
}

function setupSigninNavigation() {
    $('.navigate-signin')
        .attr('href', `/member/signin.html?redirect=${window.location.pathname}`);
}

function logout() {
    // console.log('logging out');
    localStorage.removeItem('memberId');
    // console.log(sessionStorage.getItem('memberId'));
    location.reload();
}
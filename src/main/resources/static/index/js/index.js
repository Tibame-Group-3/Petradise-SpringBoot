$(document).ready(function () {
    setupNavigation();
})

function setupNavigation() {
    setupMemberCenterNavigation();
    setupSigninNavigation();
}

function setupMemberCenterNavigation() {
    $('.navigate-member-center')
        .attr('href', '/member/signin.html');
}

function setupSigninNavigation() {
    $('.navigate-signin')
        .attr('href', '/member/signin.html');
}

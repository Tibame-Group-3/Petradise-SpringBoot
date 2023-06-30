$(document).ready(function () {
    const signedIn = isSignedIn();
    setupMemberElements(signedIn);
    setupNavigation(signedIn);
    $('.logout').on('click', logout);
    setupLostPetNavigation();
})

function setupNavigation(isSignedIn) {
    setupMemberCenterNavigation(isSignedIn);
    setupSigninNavigation();
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

// 遺失
function setupLostPetNavigation() {
	$('.lostPet')
		.attr('href', '/lostPet/lost.html');
}
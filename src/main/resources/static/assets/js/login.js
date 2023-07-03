function guardIsSignedIn() {
    if (isSignedIn() === false) {
        redirectToLogin();
    }
}

function isSignedIn() {
    const memberId = localStorage.getItem('memberId');
    return !!memberId; // Double negation turns a truthy or falsy value into actual true or false
}

function saveMemberId(memberId) {
    localStorage.setItem('memberId', memberId);
}

function getMemberId() {
    return localStorage.getItem('memberId');
}

function redirectToIndex() {
    window.location.href = '/';
}

function redirectToLogin() {
    window.location.href = '/member/signin.html?redirect=' + window.location.pathname;
}

function logout() {
    localStorage.removeItem('memberId');
    redirectToIndex();
}
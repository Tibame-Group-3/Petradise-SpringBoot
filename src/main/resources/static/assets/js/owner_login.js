function guardIsSignedIn() {
    if (isSignedIn() === false) {
        redirectToIndex();
    }
}

function isSignedIn() {
    const ownerId = localStorage.getItem('ownerId');
    return !!ownerId; // Double negation turns a truthy or falsy value into actual true or false
}

function saveOwnerId(ownerId) {
    localStorage.setItem('ownerId', ownerId);
}

function getOwnerId() {
    return localStorage.getItem('ownerId');
}

function redirectToSignin() {
    window.location.href = '/Owner/signin.html?redirect=' + encodeURIComponent(window.location.href);
}

function logout() {
    localStorage.removeItem('ownerId');
    redirectToSignin();
}
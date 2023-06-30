function isSignedIn() {
    const ownerId = localStorage.getItem('ownerId');
    return !!ownerId; // Double negation turns a truthy or falsy value into actual true or false
}

function saveOwner(owner) {
    localStorage.setItem('owner', JSON.stringify(owner));
}

function getOwner() {
    const owner = localStorage.getItem('owner');
    if (!owner) {
        return null;
    }
    return JSON.parse(owner);
}

function getOwnerName() {
    const owner = getOwner();
    if (!owner) {
        return null;
    }
    return owner.hotelName;
}

function getOwnerId() {
    const owner = getOwner();
    if (!owner) {
        return null;
    }
    return owner.hotelId;
}

function redirectToSignin() {
    window.location.href = '/Owner/signin.html?redirect=' + encodeURIComponent(window.location.href);
}

function redirectToIndex() {
    window.location.href = '/';
}

function logout() {
    localStorage.removeItem('ownerId');
    redirectToSignin();
}
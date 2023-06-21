
let member;

document.addEventListener('DOMContentLoaded', function() {
    fetchMember();

});

// Attach a submit event listener to the form to handle form submission
document.getElementById('member-form').addEventListener('submit', function(e) {
    e.preventDefault(); // Prevent form from submitting normally

    // Handle form submission, for example by making an AJAX request to your server
});

function fetchMember(){
    const memberId = localStorage.getItem('memberId');
    if (memberId === null) {
        return;
    }
    const url = `/members/id=${memberId}`;
    fetch(url)
        .then(response => response.json())
        .then(json => member = json)
        .then(() => displayMember(member));
}
function displayMember(member) {
    // Populate form with member info
    document.getElementById('name').value = member.name;
    document.getElementById('account').value = member.account;
    document.getElementById('password').value = member.password;
    // Continue for all fields
}
let member;

document.addEventListener('DOMContentLoaded', function () {
    preventSpaceInput();
    fetchMember();
    document.getElementById('form').addEventListener('submit', handleSubmit);
});

function preventSpaceInput() {
    const inputElements = document.querySelectorAll('input');
    inputElements.forEach(input => {
        input.addEventListener('keypress', function (e) {
            if (e.key === ' ') {
                e.preventDefault();
            }
        });
    });
}

function fetchMember() {
    const memberId = localStorage.getItem('memberId');
    if (memberId === null) {
        redirectToMainPage();
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
    document.getElementById('birthday').value = member.birthday;
    document.getElementById('phone').value = member.phone;
    document.getElementById('email').value = member.email;
    document.getElementById('address').value = member.address;
    // Continue for all fields
}

function redirectToMainPage() {
    window.location.href = "/";
}

function handleSubmit(event) {
    event.preventDefault();
    const name = document.getElementById('name').value;
    const birthday = document.getElementById('birthday').value;
    const phone = document.getElementById('phone').value;
    const email = document.getElementById('email').value;
    const address = document.getElementById('address').value;

    const newMember = member;
    newMember.name = name;
    newMember.birthday = birthday;
    newMember.phone = phone;
    newMember.email = email;
    newMember.address = address;

    Swal.fire({
        title: '確認會員資料',
        html: `
            <div class="row">
                <div class="col-4">姓名</div>
                <div class="col-8 text-left">${name}</div>
            </div>
            <div class="row">
                <div class="col-4">帳號</div>
                <div class="col-8 text-left">${newMember.account}</div>
            </div>
            <div class="row">
                <div class="col-4">生日</div>
                <div class="col-8">${birthday}</div>
            </div>
            <div class="row">
                <div class="col-4">電話</div>
                <div class="col-8">${phone}</div>
            </div>
            <div class="row">
                <div class="col-4">信箱</div>
                <div class="col-8">${email}</div>
            </div>
            <div class="row">
                <div class="col-4">地址</div>
                <div class="col-8">${address}</div>
            </div>
        `,
    }).then((result) => {
        if (result.isConfirmed) {
            updateMember(newMember);
        }
    });

}

function updateMember(newMember) {
    fetch('/members/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newMember)
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error(response.status);
        })
        .then(json => redirectToProfilePage())
        .catch(error => {
            Swal.fire({
                title: '修改失敗',
                icon: 'error',
                confirmButtonText: '確認'
            });
        });
}

function redirectToProfilePage() {
    window.location.href = "/member/profile.html";
}

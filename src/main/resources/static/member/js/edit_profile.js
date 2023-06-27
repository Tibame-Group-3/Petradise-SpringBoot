let member;

const nameInput = document.getElementById('name');
const birthdayInput = document.getElementById('birthday');
const phoneInput = document.getElementById('phone');
const emailInput = document.getElementById('email');
const addressInput = document.getElementById('address');

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
    nameInput.value = member.name;
    birthdayInput.value = member.birthday;
    phoneInput.value = member.phone;
    emailInput.value = member.email;
    addressInput.value = member.address;
    // Continue for all fields
}

function redirectToMainPage() {
    window.location.href = "/";
}

function handleSubmit(event) {
    event.preventDefault();
    if (validateForm()) {
        showConfirmDialog();
    }
}

function showConfirmDialog() {
    const name = nameInput.value;
    const birthday = birthdayInput.value;
    const phone = phoneInput.value;
    const email = emailInput.value;
    const address = addressInput.value;

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

function validateForm() {
    // Initialization
    let isFormValid = true;

    // Input validations
    const validations = [
        [nameInput, '請輸入姓名'],
        [birthdayInput, '請輸入生日'],
        [phoneInput, '請輸入電話號碼'],
        [phoneInput, '請輸入符合格式的台灣電話號碼', /^(\+886\-|\(02\)|09)[0-9\-]{7,10}$/],
        [emailInput, '請輸入電子郵件'],
        [emailInput, '請輸入符合格式的電子郵件', /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/],
        [addressInput, '請輸入地址']
    ];

    for(const [inputElement, errorMessage, regex] of validations) {
        if (!validateInputElement(inputElement, errorMessage, regex)) {
            isFormValid = false;
            inputElement.focus();
            return isFormValid;
        }
    }

    return isFormValid;
}

function validateInputElement(inputElement, errorMessage, regex = null) {
    if (inputElement.value === '' || (regex && !regex.test(inputElement.value))) {
        showInvalidFeedback(inputElement, errorMessage);
        return false;
    } else {
        removeInvalidFeedback(inputElement);
        return true;
    }
}

function showInvalidFeedback(inputElement, errorMessage) {
    inputElement.classList.add('is-invalid');
    const existingFeedbackElement = inputElement.parentNode.querySelector('.invalid-feedback');

    if (existingFeedbackElement) {
        existingFeedbackElement.textContent = errorMessage;
    } else {
        const newFeedbackElement = document.createElement('div');
        newFeedbackElement.classList.add('invalid-feedback');
        newFeedbackElement.textContent = errorMessage;
        inputElement.parentNode.appendChild(newFeedbackElement);
    }
}

function removeInvalidFeedback(inputElement) {
    inputElement.classList.remove('is-invalid');
    const feedbackElement = inputElement.parentNode.querySelector('.invalid-feedback');
    if (feedbackElement) {
        feedbackElement.remove();
    }
}


function redirectToProfilePage() {
    window.location.href = "/member/profile.html";
}

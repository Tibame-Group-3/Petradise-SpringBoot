const formElement = document.getElementById('form');
const nameElement = document.getElementById('name');
const accountElement = document.getElementById('account');
const passwordElement = document.getElementById('password');
const birthdayElement = document.getElementById('birthday');
const phoneElement = document.getElementById('phone');
const emailElement = document.getElementById('email');
const citySelectElement = document.getElementById('city');
const districtSelectElement = document.getElementById('district');
const addressElement = document.getElementById('address');

let districtData;

document.addEventListener('DOMContentLoaded', () => {
    fetchDistrictData();
    setBirthdayLimit();
    preventSpaceInput();
    formElement.addEventListener('submit', handleSubmit);
    citySelectElement.addEventListener('change', handleCityChange);
});

// Event handlers
function handleCityChange() {
    const cityIndex = citySelectElement.value;
    const districtInfo = districtData[cityIndex];
    populateDistrictSelect(districtInfo);
}

function handleSubmit(event) {
    event.preventDefault();
    const isFormValid = validateForm();
    if (isFormValid) {
        submitRegistration();
    }
}

// Utility functions
function setBirthdayLimit() {
    const today = new Date().toISOString().split('T')[0];
    birthdayElement.setAttribute('max', today);
    birthdayElement.setAttribute('min', '1900-01-01');
}

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

function validateForm() {
    // Initialization
    let isFormValid = true;

    // Input validations
    const validations = [
        [nameElement, '請輸入姓名'],
        [accountElement, '請輸入帳號'],
        [passwordElement, '請輸入密碼'],
        [birthdayElement, '請輸入生日'],
        [phoneElement, '請輸入電話號碼'],
        [phoneElement, '請輸入符合格式的台灣電話號碼', /^(\+886\-|\(02\)|09)[0-9\-]{7,10}$/],
        [emailElement, '請輸入電子郵件'],
        [emailElement, '請輸入符合格式的電子郵件', /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/],
        [addressElement, '請輸入地址']
    ];

    for(const [inputElement, errorMessage, regex] of validations) {
        if (!validateInputElement(inputElement, errorMessage, regex)) {
            isFormValid = false;
            inputElement.focus();
            return isFormValid;
        }
    }

    // Select validation
    if (citySelectElement.selectedIndex === -1 || districtSelectElement.selectedIndex === -1) {
        showInvalidFeedback(citySelectElement, 'Please select your city.');
        showInvalidFeedback(districtSelectElement, 'Please select your district.');
        isFormValid = false;
        return isFormValid;
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

function submitRegistration() {
    const headers = new Headers();
    headers.append("Content-Type", "application/json");

    const formData = {
        "name": nameElement.value,
        "account": accountElement.value,
        "password": passwordElement.value,
        "birthday": birthdayElement.value,
        "phone": phoneElement.value,
        "email": emailElement.value,
        "address": citySelectElement.options[citySelectElement.selectedIndex].text
            + districtSelectElement.value
            + addressElement.value
    };

    const requestOptions = {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(formData),
        redirect: 'follow'
    };

    fetch("http://localhost:8080/members/sign-up", requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(result => {
            saveMemberId(result.id);
            redirectToIndex();
        })
        .catch(error => {
            if (error.message.includes('409')) {
                showInvalidFeedback(accountElement, 'This account has been used.');
            }
        });
}

function fetchDistrictData() {
    fetch('/members/districts')
        .then(response => response.json())
        .then(populateCitySelect);
}

function populateCitySelect(data) {
    districtData = data;
    const cityOptions = data.map((cityInfo, index) => `<option value=${index}>${cityInfo.name}</option>`);
    citySelectElement.innerHTML = cityOptions.join('');
    handleCityChange();
}

function populateDistrictSelect(districtInfo) {
    const districtOptions = districtInfo.districts.map(district => `<option value=${district.name}>${district.name}</option>`);
    districtSelectElement.innerHTML = districtOptions.join('');
}

function saveMemberId(memberId) {
    localStorage.setItem('memberId', memberId);
}

function redirectToIndex() {
    window.location.href = '/';
}
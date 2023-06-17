
const form = document.getElementById('form');
const name = document.getElementById('name');
const account = document.getElementById('account');
const password = document.getElementById('password');
const birthday = document.getElementById('birthday');
const phone = document.getElementById('phone');
const email = document.getElementById('email');
const citySelect = document.getElementById('city');
const districtSelect = document.getElementById('district');
const address = document.getElementById('address');
const submit = document.getElementById('submit');

let districtsInfos;

document.addEventListener('DOMContentLoaded', () => {
    fetchDistricts();
    addBirthdayLimit();
    disableSpace();
    form.addEventListener('submit', onClickedSubmit);
    citySelect.addEventListener('change', onCityChange);
})

function onCityChange() {
    const cityIndex = citySelect.value;
    const districtInfo = districtsInfos[cityIndex];
    populateDistrictSelect(districtInfo);
}

function addBirthdayLimit() {
    // Set max to today
    birthday.setAttribute('max', `${new Date().toISOString().split('T')[0]}`);

    birthday.setAttribute('min', '1900-01-01');
}

function disableSpace() {
    let inputs = document.querySelectorAll('input');
    inputs.forEach(input => {
        input.addEventListener('keypress', function(e) {
            if (e.key === ' ') {
                e.preventDefault();
            }
        });
    });
}

function onClickedSubmit(event) {
    event.preventDefault();
    const validated = validateForm();
    if (validated) {
        signUp();
    }
    return false;
}

function validateForm() {
    let isValid = true;

    // Name validation
    if(!validateInput(name, 'Please enter your name.')) {
        isValid = false;
    }


    // Account validation
    if(!validateInput(account, 'Please enter your account.')) {
        isValid = false;
    }


    // Password validation
    if (!validateInput(password, 'Please enter your password.')) {
        isValid = false;
    }

    // Birthday validation
    if (!validateInput(birthday, 'Please enter your birthday.')) {
        isValid = false;
    }

    // Phone number validation
    if(!validateInput(phone, 'Please enter your phone number.')) {
        isValid = false;
    }

    // Email validation
    if (!validateInput(email, 'Please enter your email.')) {
        isValid = false;
    }

    // Simple email format validation
    if (!/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/.test(email.value)) {
        addInvalidFeedback(email, 'Please enter a valid email.');
        isValid = false;
    }

    // Address validation
    if (!validateInput(address, 'Please enter your address.')) {
        isValid = false;
    }

    return isValid;
}

function validateInput(input, message) {
    if (input.value === '') {
        addInvalidFeedback(input,  message);
        return false;
    } else {
        removeInvalidFeedback(input);
        return true;
    }
}

function addInvalidFeedback(input, message) {
    input.classList.add('is-invalid');
    // Check if feedback already exists
    if (input.parentNode.querySelector('.invalid-feedback')) {
        // If feedback exists, just update the message
        input.parentNode.querySelector('.invalid-feedback').textContent = message;
    } else {
        // If feedback doesn't exist, create it
        const invalidFeedback = document.createElement('div');
        invalidFeedback.classList.add('invalid-feedback');
        invalidFeedback.textContent = message;
        input.parentNode.appendChild(invalidFeedback);
    }
}

function removeInvalidFeedback(input) {
    input.classList.remove('is-invalid');
    let feedbackElement = input.parentNode.querySelector('.invalid-feedback');
    if (feedbackElement) {
        feedbackElement.remove();
    }
}

function signUp() {
    const headers = new Headers();
    headers.append("Content-Type", "application/json");

    const body = JSON.stringify({
        "name": name.value,
        "account": account.value,
        "password": password.value,
        "birthday": birthday.value,
        "phone": phone.value,
        "email": email.value,
        "address": address.value
    });

    const requestOptions = {
        method: 'POST',
        headers: headers,
        body: body,
        redirect: 'follow'
    };

    fetch("http://localhost:8080/members/sign-up", requestOptions)
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error("status code: " + response.status);
        })
        .then(result => {
            console.log("註冊成功, 資料: " + JSON.stringify(result));
        })
        .catch(error => console.log('error', error));
}

function fetchDistricts() {
    fetch('/members/districts')
        .then(response => response.json())
        .then(populateAddressSelect);
}

function populateAddressSelect(json) {
    // console.log(JSON.stringify(json));
    districtsInfos = json;
    const cities = json.map(info => info.name);
    // console.log(cities);
    cities.forEach((city, index) => {
        const option = document.createElement('option');
        option.value = index;
        option.textContent = city;
        citySelect.appendChild(option);
    })
    onCityChange();
}

function populateDistrictSelect(districtInfo) {
    districtSelect.innerHTML = '';
    districtInfo.districts.forEach(district => {
        const option = document.createElement('option');
        option.value = district.zip;
        option.textContent = district.name;
        districtSelect.appendChild(option);
    })
}

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
    addRegex();
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

function addRegex() {

}

function onClickedSubmit(event) {
    event.preventDefault();
    const validated = validateForm();
    if (validated) {
        signUp();
    }
}

function validateForm() {
    return true;
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
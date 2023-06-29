
const navigationItems = [
    {
        name: '會員資訊',
        root: '/member/profile.html',
        links: [
            '/member/profile.html',
            '/member/edit_profile.html',
            '/member/change_password.html'
        ]
    },
    {
        name: '我的寵物',
        root: '/member/pets.html',
        links: [
            '/member/pets.html',
            '/member/add_pet.html'
        ]
    },
    {
        name: '我的訂單',
        root: '/member/orders.html',
        links: [
            '/member/orders.html'
        ]
    }
]

let selectedIndex = navigationItems.findIndex(item => item.links.includes(window.location.pathname));

(() => {
    'use strict'
    document.addEventListener('DOMContentLoaded', function () {
        addNavigation();
        addActiveStateListener();
    });

    function addNavigation() {
        const navigation = document.createElement('div');
        navigation.classList.add('sidebar');
        navigation.appendChild(createLogo());
        navigation.innerHTML += navigationItems.map(createNavigationItem).join('');
        document.body.insertAdjacentElement('afterbegin', navigation);
    }

    function createLogo() {
        const logoContainer = document.createElement('a');
        logoContainer.href = '/'; // this will redirect to your index page
        logoContainer.style.display = 'block'; // so it behaves nicely with the image
        logoContainer.style.backgroundColor = 'transparent'; // make background transparent
        logoContainer.style.textDecoration = 'none'; // remove underline

        const logo = document.createElement('img');
        logo.src = '/member/images/logo.svg';
        logo.alt = 'Petradise';
        logo.style.width = '100%'; // adjust as needed
        logo.style.padding = '10px'; // adjust as needed

        logoContainer.appendChild(logo);

        return logoContainer;
    }



    function addActiveStateListener() {
        const navLinks = document.querySelectorAll('.sidebar a');
        navLinks.forEach((link, index) => {
            link.addEventListener('click', function (event) {
                event.preventDefault();
                removeActiveState();
                event.currentTarget.classList.add('active');
                selectedIndex = index;
                window.location.href = event.currentTarget.href; // redirect to the clicked page
            })
        })
    }

    function removeActiveState() {
        document.querySelectorAll('.sidebar a')
            .forEach(link => {
                link.classList.remove('active');
            })
    }

    function createNavigationItem(item, index) {
        const isActive = index === selectedIndex ? 'active' : '';
        return `<a href="${item.root}" class="${isActive}">${item.name}</a>`;
    }
})();

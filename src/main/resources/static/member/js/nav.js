const navigationItems = [
    {
        name: '會員資訊',
        url: '/member/profile.html'
    },
    {
        name: '我的寵物',
        url: '/member/pets.html'
    },
    {
        name: '我的訂單',
        url: '/member/orders.html'
    }
];

let selectedIndex = navigationItems.findIndex(item => window.location.pathname.includes(item.url));;

(() => {
    'use strict'
    document.addEventListener('DOMContentLoaded', function () {
        addNavigation();
        addActiveStateListener();
    });

    function addNavigation() {
        const navigation = document.createElement('div');
        navigation.classList.add('sidebar');
        navigation.innerHTML = navigationItems.map(createNavigationItem).join('');
        document.body.insertAdjacentElement('afterbegin', navigation);
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
        return `<a href="${item.url}" class="${isActive}">${item.name}</a>`;
    }
})();

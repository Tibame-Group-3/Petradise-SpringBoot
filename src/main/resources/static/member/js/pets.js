document.addEventListener('DOMContentLoaded', function () {
    fetchPets();
    displayPets();
    addNewPetButtonListener();
});

function fetchPets() {
    fetch(`/pets/memberId=${getMemberId()}`)
        .then(response => response.json())
        .then(console.log)
}

function displayPets() {
    // Replace this array with your actual data
    const pets = [
        {
            name: 'Charlie',
            image: 'https://example.com/charlie.jpg',
        },
        {
            name: 'Max',
            image: 'https://example.com/max.jpg',
        }
        // Add more pets here...
    ];

    const petsRow = document.getElementById('pets-row');
    pets.forEach(pet => {
        const petCard = `
            <div class="col-md-4">
                <div class="card mb-4">
                    <img src="${pet.image}" class="card-img-top" alt="${pet.name}">
                    <div class="card-body">
                        <h5 class="card-title">${pet.name}</h5>
                    </div>
                </div>
            </div>
        `;
        petsRow.insertAdjacentHTML('beforeend', petCard);
    });
}

function addNewPetButtonListener() {
    document.getElementById('add-pet-button').addEventListener('click', function () {
        window.location.href = '/member/add_pet.html';  // Replace with your actual link
    });
}

function getMemberId() {
    return localStorage.getItem('memberId');
}

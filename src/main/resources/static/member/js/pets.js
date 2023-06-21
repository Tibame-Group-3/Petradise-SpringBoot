document.addEventListener('DOMContentLoaded', function () {
    fetchPets();
    addNewPetButtonListener();
});

function fetchPets() {
    fetch(`/pets/memberId=${getMemberId()}`)
        .then(response => response.json())
        .then(displayPets)
}

function displayPets(pets) {

    const petsRow = document.getElementById('pets-row');
    pets.forEach(pet => {
        let image = 'https://placehold.co/268x180';
        const petPics = pet.petPics;
        if (petPics !== undefined && petPics.length > 0) {
            image = ` data:image/jpeg;charset=utf-8;base64, ${petPics[0].pic}`;
        }
        const petCard =
            `
            <div class="col-md-4">
                <div class="card mb-4">
                    <img src="${image}" class="card-img-top" style="height: 180px; object-fit: cover;" alt="${pet.name}">
                    <div class="card-body">
                        <h5 class="card-title">${pet.name}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">${pet.type}</h6>
                        <p class="card-text">${pet.size}</p>
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

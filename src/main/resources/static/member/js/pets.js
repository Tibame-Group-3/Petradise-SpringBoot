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

    // Add "New Pet" card
    const newPetCard =
        `
    <div class="col-md-4">
        <div class="card mb-4 new-pet-card" onClick="window.location.href='/member/add_pet.html'" style="cursor:pointer;">
            <div class="card-body text-center d-flex flex-column justify-content-center">
                <div id="add-pet-icon" class="add-icon"></div>
                <h3 class="card-title new-pet-title">新增寵物</h3>
            </div>
        </div>
    </div>
    `;
    petsRow.insertAdjacentHTML('afterbegin', newPetCard);

// Display actual pet cards
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
                    <div class="img-container">
                        <img src="${image}" class="card-img-top" alt="${pet.name}">
                    </div>
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

document.addEventListener('DOMContentLoaded', fetchPets);

function fetchPets() {
    fetch(`/pets/memberId=${getMemberId()}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(displayPets)
        .catch(e => console.error('Fetch failed: ', e));
}

function createPetCard(pet) {
    let image = 'https://placehold.co/268x180';
    const petPics = pet.petPics;
    if (petPics !== undefined && petPics.length > 0) {
        image = ` data:image/jpeg;charset=utf-8;base64, ${petPics[0].pic}`;
    }
    return `
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
}

function createNewPetCard() {
    return `
        <div class="col-md-4">
            <div class="card mb-4 new-pet-card" onClick="window.location.href='/member/add_pet.html'" style="cursor:pointer;">
                <div class="card-body text-center d-flex flex-column justify-content-center">
                    <svg class="mx-auto" width="40" height="40" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M12 6C12.5523 6 13 6.44772 13 7V11H17C17.5523 11 18 11.4477 18 12C18 12.5523 17.5523 13 17 13H13V17C13 17.5523 12.5523 18 12 18C11.4477 18 11 17.5523 11 17V13H7C6.44772 13 6 12.5523 6 12C6 11.4477 6.44772 11 7 11H11V7C11 6.44772 11.4477 6 12 6Z" fill="#888888" />
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M5 22C3.34315 22 2 20.6569 2 19V5C2 3.34315 3.34315 2 5 2H19C20.6569 2 22 3.34315 22 5V19C22 20.6569 20.6569 22 19 22H5ZM4 19C4 19.5523 4.44772 20 5 20H19C19.5523 20 20 19.5523 20 19V5C20 4.44772 19.5523 4 19 4H5C4.44772 4 4 4.44772 4 5V19Z" fill="#888888" />
                    </svg>
                    <h3 class="card-title new-pet-title">新增寵物</h3>
                </div>
            </div>
        </div>
    `;
}

function displayPets(pets) {
    const petsRow = document.getElementById('pets-row');

    // Add "New Pet" card
    petsRow.insertAdjacentHTML('afterbegin', createNewPetCard());

    // Display actual pet cards
    pets.forEach(pet => {
        petsRow.insertAdjacentHTML('beforeend', createPetCard(pet));
    });
}

function getMemberId() {
    return localStorage.getItem('memberId');
}

document.addEventListener('DOMContentLoaded', function() {
    setupPetPicPreview();
    setupFormSubmit();
});

function setupPetPicPreview() {
    let petPicsInput = document.querySelector('#petPics');
    let imagePreview = document.querySelector('#imagePreview');

    if (petPicsInput) {
        petPicsInput.addEventListener('change', function(event) {
            let files = event.target.files;
            imagePreview.innerHTML = '';
            Array.from(files).forEach(file => {
                let reader = new FileReader();
                reader.onload = function(e) {
                    let img = document.createElement('img');
                    img.src = e.target.result;
                    img.style.maxWidth = '150px';
                    img.style.height = 'auto';
                    img.style.marginRight = '10px';
                    imagePreview.appendChild(img);
                };
                reader.readAsDataURL(file);
            })
        });
    }
}

function setupFormSubmit() {
    const form = document.querySelector('#form');

    form.addEventListener('submit', function(event) {
        event.preventDefault();
        const formData = new FormData(form);
        const pet = {
            name: formData.get('name'),
            type: formData.get('type'),
            size: formData.get('size'),
            memberId: Number(getMemberId()),
        };
        let petPics = Array.from(formData.getAll('petPics')).map(pic => {
            return new Promise((resolve, reject) => {
                let fileReader = new FileReader();
                fileReader.readAsDataURL(pic);
                fileReader.onload = function(e) {
                    let base64Image = e.target.result.split(',')[1];  // remove MIME type
                    resolve(base64Image);
                };
                fileReader.onerror = function(e) {
                    reject(new Error("File reading failed"));
                };
            });
        });

        Promise.all(petPics)
            .then(results => {
                const data = { pet, pics: results };
                return fetch('/pets/add', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(data),
                });
            })
            .then(response => response.json())
            .then(data => {
                // Handle the response data here
                console.log(data);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });

}

function getMemberId() {
    return localStorage.getItem('memberId');
}


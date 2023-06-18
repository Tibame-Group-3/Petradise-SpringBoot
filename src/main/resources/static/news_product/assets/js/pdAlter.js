// ------------------送出資料------------------
$("#submit").on('click', function (e) {
    e.preventDefault();

    let pdName = $("#pdName").val().trim();
    let pdPetType = $("#pdPetType").val();
    let pdType = $("#pdType").val();
    let pdPrice = $("#pdPrice").val();

    if(pdName.length === 0 || pdPetType === "" || pdType === "" || pdPrice.length === 0) {
        Swal.fire({
            icon: 'error',
            title: '齁...',
            text: '欄位不可以空著唷!',
        });
    } else {
        const currentDate = new Date();
        const year = currentDate.getFullYear();
        const month = String(currentDate.getMonth() + 1).padStart(2, '0');
        const day = String(currentDate.getDate()).padStart(2, '0');
        const formattedDate = `${year}-${month}-${day}`;

        const data = {};
        data.pdName = $("#pdName").val();
        data.pdPetType = $("#pdPetType").val();
        data.pdType = $("#pdType").val();
        data.pdPrice = $("#pdPrice").val();
        data.pdStatus = 0;
        data.pdInfo = $("#pdInfo").val();
        data.pdDate = formattedDate;
        console.log(data);

        axios
            .post("/product/add", data)  // 設定url, object
            .then((res) => {
                console.log(res.data);  // 獲得回傳資料
            })
            .catch((err) => {
                console.error(err);
            });

        location.href = "Product.html";

    }

});
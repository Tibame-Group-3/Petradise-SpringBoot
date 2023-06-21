const data = {};
const shoppingItem = {};
// ------------------拿session資料------------------
$(document).ready(function () {
    const pdId = JSON.parse(sessionStorage.getItem("pdId"));
    // console.log("/product/get/" + pdId)

    axios.get("/product/get/" + pdId)
        .then(function (res) {
            console.log(res.data);
            const base64Img = `data:image/*;base64,${res.data.pdImg}`;
            $("#pdName").val(res.data.pdName);
            $("#pdPetType").val(res.data.pdPetType);
            $("#pdType").val(res.data.pdType);
            $("#pdPrice").val("$ " + res.data.pdPrice);
            $("#pdInfo").val(res.data.pdInfo);
            $("#preview").html(`<img src="${base64Img}" alt="image" style="width: 100%">`);
            data.pdImg = base64Img;
        })
        .catch(err => console.log(err));

})
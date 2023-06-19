// ------------------預覽------------------
$("#pdImg").on("change", function () {         // 監聽input讀取圖片
    // 有選圖片的話
    if (this.files.length > 0) {
        let reader = new FileReader();                                          // 準備讀取檔案
        reader.addEventListener('load', function () {
            // alert("aaa");
            $("#preview").html('<img src="' + reader.result + '" style="width: 100%">'); // 放圖片的src
        });
        reader.readAsDataURL(this.files[0]);                                    // 執行讀取檔案
    } else {
        $("#preview").html('預覽圖');
    }
});

// ------------------拿session資料------------------
$(document).ready(function () {
    const pdId = JSON.parse(sessionStorage.getItem("pdId"));
    // console.log("/news/get/" + newsID)

    axios.get("/product/get/" + pdId)
        .then(function (res) {
            // console.log(res.data);
            // console.log(res.data.newsTitle);
            $("#pdName").val(res.data.pdName);
            $("#pdPetType").val(res.data.pdPetType);
            $("#pdType").val(res.data.pdType);
            $("#pdPrice").val(res.data.pdPrice);
            $("#pdInfo").val(res.data.pdInfo);
        })
        .catch(err => console.log(err));

})

// ------------------修改資料------------------
const pdId = JSON.parse(sessionStorage.getItem("pdId"));
console.log(pdId)

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
        data.pdId = pdId;
        data.pdName = $("#pdName").val();
        data.pdPetType = $("#pdPetType").val();
        data.pdType = $("#pdType").val();
        data.pdPrice = $("#pdPrice").val();
        data.pdStatus = 0;
        data.pdInfo = $("#pdInfo").val();
        data.pdDate = formattedDate;
        console.log(data);

        axios
            .put("/product/update/" + pdId, data)  // 設定url, object
            .then((res) => {
                console.log(res.data);  // 獲得回傳資料
            })
            .catch((err) => {
                console.error(err);
            });

        location.href = "Product.html";
    }
});
// ------------------預覽------------------
$("#newsImg").on("change", function () {         // 監聽input讀取圖片
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

// ------------------送出資料------------------
$("#submit").on('click', function (e) {
    e.preventDefault();

    let newsTitle = $("#newsTitle").val().trim();
    let newsDate = $("#newsDate").val().trim();
    let newsContent = $("#newsContent").val().trim();

    if(newsTitle.length == 0 || newsDate.length == 0 || newsContent.length == 0) {
        Swal.fire({
            icon: 'error',
            title: '齁...',
            text: '欄位不可以空著唷!',
        });
    } else {

        const data = {};
        data.adminId = $("#adminId").val();
        data.newsTitle = $("#newsTitle").val();
        data.newsDate = $("#newsDate").val();
        data.newsContent = $("#newsContent").val();
        // console.log(data);

        axios
            .post("/news/add", data)  // 設定url, object
            .then((res) => {
                console.log(res.data);  // 獲得回傳資料
            })
            .catch((err) => {
                console.error(err);
            });

        location.href = "NewsList.html";

    }

});

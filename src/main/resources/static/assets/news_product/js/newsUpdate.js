// ------------------拿session資料------------------
$(document).ready(function () {
    const newsID = JSON.parse(sessionStorage.getItem("newsID"));
    // console.log("/news/get/" + newsID)

    axios.get("/news/get/" + newsID)
        .then(function (res) {
            // console.log(res.data);
            // console.log(res.data.newsTitle);
            $("#newsTitle").val(res.data.newsTitle);
            $("#newsDate").val(res.data.newsDate);
            $("#newsContent").val(res.data.newsContent);
        })
        .catch(err => console.log(err));

})

// ------------------修改資料------------------
const newsID = JSON.parse(sessionStorage.getItem("newsID"));
console.log(newsID)

$("#submit").on('click', function (e) {
    e.preventDefault();

    const data = {};
    data.newsId = newsID;
    data.adminId = $("#adminId").val();
    data.newsTitle = $("#newsTitle").val();
    data.newsDate = $("#newsDate").val();
    data.newsContent = $("#newsContent").val();

    axios
        .put("/news/update/" + newsID, data)  // 設定url, object
        .then((res) => {
            console.log(res.data);  // 獲得回傳資料
        })
        .catch((err) => {
            console.error(err);
        });

    location.href = "NewsList.html";
});
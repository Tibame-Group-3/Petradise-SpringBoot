// ------------------列出資料------------------
$(document).ready(function () {
    const tableBody = document.getElementById('tableBody');
    axios.get("/news/get/all")
        .then( function (res) {
            console.log(res.data);
            res.data.forEach(element => {
                    const row = `
                            <tr>
                                <td class="newsID" style="color: #a67c52">${element.newsId}</td>
                                <td class="newsTitle" style="color: #a67c52">${element.newsTitle}</td>
                                <td class="newsContent" style="color: #a67c52; display: none">${element.newsContent}</td>
                                <td class="newsDate" style="color: #a67c52">${element.newsDate}</td>
                                <td>
                                    <button id="btn_update" class="btn btn-primary" type="button" style="background: #f1ecd1;border-style: none;color: #a67c52">修改</button>
                                </td>
                            </tr>
                            `
                    tableBody.innerHTML += row;
                })
        })

        .catch(err => console.log(err));

})

// ------------------修改資料------------------
$(document).on('click', "button#btn_update", function (e) {
    // const data = {};
    newsID = $(this).closest("tr").find("td.newsID").text();
    // console.log(newsID);
    // data.newsTitle = $(this).closest("tr").find("td.newsTitle").text();
    // data.newsDate = $(this).closest("tr").find("td.newsDate").text();
    // data.newsContent = $(this).closest("tr").find("td.newsContent").text();

    sessionStorage.setItem("newsID", JSON.stringify(newsID));
    location.href = "NewsUpdate.html";

})
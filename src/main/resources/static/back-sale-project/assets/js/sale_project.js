// 顯示全部活動
$(document).ready(function () {
    const sale_project_table_body = document.getElementById('sale_project_table_body');
    fetch("/saleProject/all")
        .then(res => res.json())
        .then(onReceivedJSON)
        .catch(error => console.log('There woa a problem with the fetch operation', error));
})

function onReceivedJSON(jsonData) {
    console.log(jsonData);
    jsonData.forEach(element => {
        const row = `
            <tr>
                <td style="color: #a67c52;">${element.saleProId}</td>
                <td style="color: #a67c52;">${element.saleProName}</td>
                <td style="color: #a67c52;">?折</td>
                <td style="color: #a67c52;">${element.saleProStart}</td>
                <td style="color: #a67c52;">${element.saleProEnd}</td>


                                        <!-- 編輯活動 -->
                <td class="edit_sale_work">
                    <!-- Button trigger modal -->
                    <button type="button" class="edit_sale" data-bs-toggle="modal"
                        data-bs-target="#editBackdrop">
                        <img src="assets/img/edit_icon.png">
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="editBackdrop" data-bs-backdrop="static"
                        data-bs-keyboard="false" tabindex="-1"
                        aria-labelledby="staticBackdropLabel" aria-hidden="true">

                        <!-- Modal-dialog -->
                        <div
                            class="modal-dialog modal-dialog modal-dialog-centered modal-dialog-scrollable">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="staticBackdropLabel">
                                        編輯活動</h5>
                                    <button type="button" class="btn-close"
                                        data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>


                                <!-- Body -->
                                <div class="modal-body edit_button_body">
                                    <article class="add_sale_project">
                                        <div class="mb-3">
                                            <label for="sale_project_name" class="form-label"
                                                style="color: #a67c52;">活動名稱：</label>
                                            <input type="text" class="form-control"
                                                id="sale_project_name">
                                        </div>
                                        <div class="mb-3">
                                            <label for="sale_product_id" class="form-label"
                                                style="color: #a67c52;">活動商品編號：</label>
                                            <input type="text" class="form-control"
                                                id="sale_product_id">
                                        </div>
                                        <div class="mb-3">
                                            <label for="sale_project_discount" class="form-label"
                                                style="color: #a67c52;">活動折扣數：</label>
                                            <input type="text" class="form-control"
                                                id="sale_project_discount">
                                        </div>
                                        <div class="mb-3">
                                            <label for="sale_project_start" class="form-label"
                                                style="color: #a67c52;">活動起始日期：</label>
                                            <input type="date" class="form-control"
                                                id="sale_project_start">
                                        </div>

                                        <div class="mb-3">
                                            <label for="sale_project_end" class="form-label"
                                                style="color: #a67c52;">活動結束日期：</label>
                                            <input type="date" class="form-control"
                                                id="sale_project_end">
                                        </div>
                                    </article>


                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary"
                                        data-bs-dismiss="modal">取消</button>
                                    <button type="button"
                                        class="btn btn-primary">完成</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>

                <!-- 刪除活動 -->
                <td class="delete_sale_work">
                    <button type="button" class="delete_sale">
                        <img src="assets/img/delete_icon.png">
                    </button>
                </td>
            </tr>
            `
        sale_project_table_body.innerHTML += row;
    });
}


// 新增活動
// type must be the samew 一定要數字型態 +號是轉數字型態，對應DTO表格，傳時間要有毫秒
// const insertData = {
//     'pdNo': +$("#pdNo").val(),
//     'pdName': $("#pdName").val(),
//     'pdPrice': +$("#pdPrice").val(),
//     'pdStock': +$("#pdStock").val(),
//     'pdDescription': $("#pdDescription").val(),
//     'pdStatus': $("#c1").prop('checked'),
//     'pdUpdate': moment($("#pdUpdate").val()).format('YYYY-MM-DD HH:mm:ss')
// }
// console.log(insertData);

// // Send form data as POST request
// fetch('/saleProject/add', {
//     method: 'POST',
//     headers: {
//         'Content-Type': 'application/json' //一定要設定!
//     },
//     body: JSON.stringify(insertData) //轉JOSN字串
// })
//     .then(response => {
//         if (!response.ok) {
//             throw new Error('Network response was not ok');
//         }
//         return response.json();
//     })
//     .then(data => {
//         console.log(data);
//     })
//     .catch(error => {
//         console.error('There was a problem with the fetch operation:', error);
//     });
// });
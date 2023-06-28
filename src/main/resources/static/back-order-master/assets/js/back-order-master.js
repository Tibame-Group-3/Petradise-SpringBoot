$(document).ready(function () {
    const tableBody = document.getElementById('table_body');
    fetch("/order_master/all", {
        method: 'GET'
    })
        .then(response => response.json())
        .then(onReceivedJSON)
        .then(initDataTable)
        .catch(error => console.log('There was a problem with the fetch operation', error));
})

function initDataTable() {
    $("#my_table").DataTable({
        "language": {
            "lengthMenu": "顯示 _MENU_ 筆訂單",
            "zeroRecords": "沒有符合的結果",
            "info": "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
            "infoEmpty": "共 0 項",
            "infoFiltered": "(從 _MAX_ 條資料中過濾)",
            "search": "搜尋:",
            "paginate": {
                "next": ">",
                "previous": "<",
                "first": "第一頁",
                "last": "最後一頁"
            },
            "aria": {
                "sortAscending": ": 升冪排列",
                "sortDescending": ": 降冪排列"
            }
        }
    });
}

function onReceivedJSON(jsonData) {
    console.log(jsonData);
    for (let i of jsonData) {
        const row = `
            <tr>
                <td class="order_id" style="color: #a67c52;">${i.odId}</td>
                <td class="mem_id" style="color: #a67c52;">${i.memId}</td>
                <td class="order_date" style="color: #a67c52;">${i.odDate}</td>
                <td class="price_od" style="color: #a67c52;">${i.priceOd}</td>
                <td class="reci_name" style="color: #a67c52;">${i.reciName}</td>
                <td class="reci_phone" style="color: #a67c52;">${i.reciPhone}</td>
                <td class="order_status" style="color: #a67c52;">${i.odStatus}</td>

                <!-- 查看訂單 -->
                <td class="view_order_work">
                    <!-- Button trigger modal -->
                    <button type="button" class="view_order" data-bs-toggle="modal"
                        data-bs-target="#staticBackdrop">
                        <img src="assets/img/view_icon.png">
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="staticBackdrop"
                        data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                        aria-labelledby="staticBackdropLabel" aria-hidden="true">

                        <!-- Modal-dialog -->
                        <div
                            class="modal-dialog modal-dialog modal-dialog-centered modal-dialog-scrollable">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h3 class="modal-title" id="staticBackdropLabel">
                                        查看訂單詳情</h3>
                                    <button type="button" class="btn-close"
                                        data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>

                                <!-- Light-Box-Body -->
                                <div class="modal-body">

                                    <table class="table" id="modal_table">
                                        <thead>

                                            <tr>
                                                <th scope="col" style="color: #a67c52;">
                                                    訂單編號</th>
                                                <td scope="col">First</th>
                                            </tr>

                                        </thead>
                                        <tbody>

                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    會員姓名</th>
                                                <td>Mark</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    訂單日期</th>
                                                <td>Jacob</td>
                                            </tr>

                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    運費</th>
                                                <td>Larry the Bird</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    原價總金額</th>
                                                <td>Larry the Bird</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    促銷價總金額</th>
                                                <td>Larry the Bird</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    紅利折抵</th>
                                                <td>Larry the Bird</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    實際總金額</th>
                                                <td>Larry the Bird</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    收件人姓名</th>
                                                <td>Larry the Bird</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    收件人電話</th>
                                                <td>Larry the Bird</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    收件人地址</th>
                                                <td>Larry the Bird</td>
                                            </tr>

                                        </tbody>
                                    </table>

                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary"
                                        data-bs-dismiss="modal">取消</button>
                                    <button type="button"
                                        class="btn btn-primary">確定</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>

                <!-- 取消訂單 -->
                <td class="delete_order_work">
                    <button type="button" class="delete_order">
                        <img src="assets/img/delete_icon.png">
                    </button>
                </td>
            </tr>`
            ;
        table_body.innerHTML += row;
    }
}
        // axios.get("/product/get/all")
        //     .then( function (res) {
        //         console.log(res.data);
        //         res.data.forEach(element => {

        //             const base64Img = `data:image/*;base64,${element.pdImg}`;

        //             const row = `
        //                         <tr>
        //                             <td class="pdId" style="color: #a67c52;">${element.pdId}</td>
        //                             <td class="pdId" style="color: #a67c52;">
        //                                 <img class="card-img-top" src="${base64Img}" alt="image"></td>
        //                             <td class="pdType" style="color: #a67c52;">${element.pdType}</td>
        //                             <td class="pdName" style="color: #a67c52;">${element.pdName}</td>
        //                             <td class="pdPrice" style="color: #a67c52;">\$ ${element.pdPrice}</td>
        //                             <td class="pdDate" style="color: #a67c52;">${element.pdDate}</td>
        //                             <td>
        //                                 <button class="btn btn-primary btn_update" type="button"
        //                                     style="background: #f1ecd1;border-style: none;color: #a67c52;">修改</button>
        //                             </td>
        //                         </tr>
        //                         `
        //             tableBody.innerHTML += row;
        //         })
                // $("#myTable").DataTable({   //初始化DataTable
                //     "language": {
                //         "lengthMenu": "顯示 _MENU_ 條",
                //         "zeroRecords": "未找到任何資料",
                //         "info": "顯示頁數 _PAGE_ / _PAGES_",
                //         "infoEmpty": "沒有任何資訊",
                //         "infoFiltered": "(從 _MAX_ 條資料中過濾)",
                //         "search": "搜尋:",
                //         "paginate": {
                //             "first":      "第一頁",
                //             "last":       "最後一頁",
                //             "next":       "»",
                //             "previous":   "«"
                //         }
                //     }
                // });
        //     })
        //     .catch(err => console.log(err));

    // })
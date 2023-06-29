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

function convertStatus(odStatus) {
    let ststus = '';

    switch (odStatus) {
        case '0':
            return status = '待結帳';
            break;
        case '1':
            return ststus = '訂單失效';
            break;
        case '2':
            return status = '訂單成立';
            break;
        case '3':
            return status = '備貨中';
            break;
        case '4':
            return status = '已出貨';
            break;
        case '5':
            return status = '已送達';
            break;
        case '6':
            return status = '訂單完成';
            break;
    }
    
}

function onReceivedJSON(jsonData) {
    console.log(jsonData);
    for (let i of jsonData) {
        const row = `
            <tr>
                <td class="order_id" style="color: #a67c52;">${i.odId}</td>
                <td class="mem_name" style="color: #a67c52;">${i.name}</td>
                <td class="order_date" style="color: #a67c52;">${i.odDate}</td>
                <td class="price_od" style="color: #a67c52;">${i.priceOd}</td>
                <td class="reci_name" style="color: #a67c52;">${i.reciName}</td>
                <td class="reci_phone" style="color: #a67c52;">${i.reciPhone}</td>
                <td class="order_status" style="color: #a67c52;">${convertStatus(i.odStatus)}</td>

                <!-- 查看訂單 -->
                <td class="view_order_work">
                    <!-- Button trigger modal -->
                    <button type="button" class="view_order" data-bs-toggle="modal"
                        data-bs-target="#staticBackdrop">
                        <img src="assets/img/view_icon.png" style="background-color: transparent">
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
                                                <td scope="col">${i.odId}</th>
                                            </tr>

                                        </thead>
                                        <tbody>

                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    會員姓名</th>
                                                <td>${i.name}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    會員姓名</th>
                                                <td>${i.pdName}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    訂單日期</th>
                                                <td>${i.odDate}</td>
                                            </tr>

                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    運費</th>
                                                <td>${i.priceShip}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    實際總金額</th>
                                                <td>${i.priceOd}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    收件人姓名</th>
                                                <td>${i.reciName}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    收件人電話</th>
                                                <td>${i.reciPhone}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    收件人地址</th>
                                                <td>${i.reciAdd}</td>
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
            </tr>`
            ;
        table_body.innerHTML += row;
    }
}

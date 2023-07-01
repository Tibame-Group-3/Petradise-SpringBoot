
$(document).ready(function () {
    findAllOrder();
})

$('.view_order').click(function () {
    const orderId = $(this).closest('tr').find('.order_id').text();
    fetchOrderDetail(orderId);
})

function findAllOrder() {
    const tableBody = document.getElementById('table_body');
    fetch('/order/allOrder', {
        method: 'GET'
    })
        .then(response => response.json())
        .then(onReceivedJSON)
        .then(initDataTable)
        .catch(error => console.error('There was a problem with the fetch operation', error));
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
                <td class="order_status" style="color: #a67c52;">${convertOrderStatus(i.odStatus)}</td>

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
                                                    商品名稱</th>
                                                <td>${i.pdName}</td>
                                            </tr>

                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    數量</th>
                                                <td>${i.pdAmount}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    訂購金額</th>
                                                <td>${i.priceOri}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    運費</th>
                                                <td>${i.priceShip}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    付款總金額</th>
                                                <td>${i.priceOri + i.priceShip}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    訂單日期</th>
                                                <td>${i.odDate}</td>
                                            </tr>

                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    取貨方式</th>
                                                <td>${i.odShip}</td>
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
                                                    收件門市</th>
                                                <td>${i.reciStore}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="color: #a67c52;">
                                                    收件地址</th>
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

function fetchOrderDetail(orderId) {
    fetch(`order/showOrderDetail/id=${orderId()}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error status: ${response.status}`)
            }
            response.json();
        })
        .then(onReceivedOrderDetail)
        .catch(error => console.error('There was a problem with the fetch operation', error));
}

function onReceivedOrderDetail() {

}

function convertOrderStatus(odStatus) {
    switch (odStatus) {
        case '0':
            return '待結帳';
        case '1':
            return '訂單失效';
        case '2':
            return '訂單成立';
        case '3':
            return '備貨中';
        case '4':
            return '已出貨';
        case '5':
            return '已送達';
        case '6':
            return '訂單完成';
    }
}

function convertOrderPayment(odPay) {
    switch (odPay) {
        case '0':
            return '貨到付款';
        case '1':
            return '信用卡結帳';
        case '2':
            return '匯款轉帳';
    }
}

function convertOrderDelivery(odShip) {
    switch (odShip) {
        case '0':
            return '宅配';
        case '1':
            return '711取貨';
        case '2':
            return '全家取貨';
    }
}

function initDataTable() {
    $('#my_table').DataTable({
        // JSON cannot use ''
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
// ------------------購物車項目初始化----------------------------------------------------
let shoppingItem = sessionStorage.getItem("shoppingItem");
if (shoppingItem) {
    shoppingItem = JSON.parse(shoppingItem);
} else {
    shoppingItem = {};
}

// 更新購物車商品數----------------------------------------------------
function updateCartIcon() {
    let totalItems = 0;
    for (const productId in shoppingItem) {
        totalItems += shoppingItem[productId].quantity;
    }
    $(".shopping-cart-total").text(`(${totalItems})`);
}
// 初始化購物車圖示數量-------------------------------------------------
updateCartIcon();


// ------------------Form表單----------------------------------------------------
$(document).ready(function () {
    $('#sel1').change(function () {
        let selectedOption = $(this).val();
        $('.credit-card, .wire-transfers').addClass('-off');

        if (selectedOption === 'credit-card') {
            $('.credit-card').removeClass('-off');

        } else if (selectedOption === 'wire-transfers') {
            $('.wire-transfers').removeClass('-off');
        }
    });

    $('#sel2').change(function () {
        let selectedOption = $(this).val();
        $('.home-delivery, .pickup-711, .pickup-fami').addClass('-off');

        if (selectedOption === 'home-delivery') {
            $('.home-delivery').removeClass('-off');
        } else if (selectedOption === 'pickup-711') {
            $('.pickup-711').removeClass('-off');
        } else if (selectedOption === 'pickup-fami') {
            $('.pickup-fami').removeClass('-off');
        }
    });

});


// ------------------拿session資料----------------------------------------------------
let checkoutItem = sessionStorage.getItem("checkoutItem");
checkoutItem = JSON.parse(checkoutItem);

// ------------------渲染購物車項目----------------------------------------------------
const payBody = document.getElementById('payBody');
const itemsPriceElement = $(".items-price");
const shipPriceElement = $(".ship-price");
const totalPriceElement = $(".tol-price");
let itemsPrice = 0;
let shipPrice = 60;
let totalPrice = 0;

for (const productId in checkoutItem) {
    const item = checkoutItem[productId];
    const base64Img = `data:image/*;base64,${item.image}`;
    const id = item.id;
    const name = item.name;
    const pdType = item.type;
    const pdPetType = item.petType;
    const price = item.price * item.quantity;

    const row = `
        <div class="payment-items" id="${id}">
            <div class="image-box">
                <img src="${base64Img}" style="height: 80px">
            </div>

            <div class="item-about">
                <span class="item-type pdPetType"> ${pdPetType} / </span><span class="item-type pdType">${pdType}</span>
                <h1 class="item-title pdName">${name}</h1>
            </div>

            <div class="item-price pdPrice">\$ ${price}</div>
        </div>
        `;
    payBody.innerHTML += row;

    // 付款總金額----------------------------------------------------
    itemsPrice += item.price * item.quantity;
    totalPrice = itemsPrice + shipPrice;
    itemsPriceElement.text(`+ $ ${itemsPrice}`);
    shipPriceElement.text(`+ $ ${shipPrice}`);
    totalPriceElement.text(`= $ ${totalPrice}`);

}

// ------------------送出Form表單----------------------------------------------------
$('.confirm-payment').click(function () {
    // 檢查表單內容是否填寫完整
    let isFormValid = true;

    // 檢查信用卡表單----------------------------------------------------
    if(!$("#sel1").val()) {
        isFormValid = false;
    } else if ($("#sel1").val() === "credit-card") {
        if (!$('#card-number').val() || !$('.expiration-date input').eq(0).val() || !$('.expiration-date input').eq(1).val() || !$('#cvc').val() || !$('#card-holder').val()) {
            isFormValid = false;
        }
    // 檢查轉帳/匯款表單----------------------------------------------------
    } else if ($("#sel1").val() === "wire-transfers") {
        if (!$('#remitter-name').val() || !$('#last-number').val()) {
            isFormValid = false;
        }
    }

    // 檢查宅配到府表單----------------------------------------------------
    if(!$("#sel2").val()) {
        isFormValid = false;
    } else if ($("#sel2").val() === "home-delivery") {
        if (!$('.home-delivery input.recipient-name').val() || !$('.home-delivery input.recipient-phone').val() || !$('#recipient-address').val()) {
            isFormValid = false;
        }
    // 檢查超商取貨表單----------------------------------------------------
    } else if ($("#sel2").val() === "pickup-711") {
        if (!$('#store-name-711').val() || !$('.recipient-name-711').val() || !$('.recipient-phone-711').val()) {
            isFormValid = false;
        }
    } else if ($("#sel2").val() === "pickup-fami") {
        if (!$('#store-name-fami').val() || !$('.recipient-name-fami').val() || !$('.recipient-phone-fami').val()) {
            isFormValid = false;
        }
    }

    // 如果表單不完整，顯示警示訊息
    if (!isFormValid) {
        Swal.fire({
            icon: 'error',
            title: '齁...',
            text: '欄位不可以空著唷!',
        });
    } else {
        Swal.fire({
            icon: 'success',
            title: '訂購成功了唷！',
            text: '請耐心等待出貨~',
        }).then((result) => {
            if (result.isConfirmed) {
                location.href = "/front-product-list/front-product-list.html";
            }
        });
    }

})
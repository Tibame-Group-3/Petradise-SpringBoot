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
        $('.home-delivery, .pickup').addClass('-off');

        if (selectedOption === 'home-delivery') {
            $('.home-delivery').removeClass('-off');
        } else if (selectedOption === 'pickup') {
            $('.pickup').removeClass('-off');
        }
    });

});


// ------------------拿session資料----------------------------------------------------
let checkoutItem = sessionStorage.getItem("checkoutItem");
checkoutItem = JSON.parse(checkoutItem);

// ------------------渲染購物車項目----------------------------------------------------
const payBody = document.getElementById('payBody');
const totalPriceElement = $(".tol-price");
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
                <span class="item-type pdPetType"> ${pdPetType}</span> / <span class="item-type pdType">${pdType}</span>
                <h1 class="item-title pdName">${name}</h1>
            </div>

            <div class="item-price pdPrice">\$ ${price}</div>
        </div>
        `;
    payBody.innerHTML += row;

    // 付款總金額----------------------------------------------------
    totalPrice += item.price * item.quantity;
    totalPriceElement.text(`$ ${totalPrice}`);

}


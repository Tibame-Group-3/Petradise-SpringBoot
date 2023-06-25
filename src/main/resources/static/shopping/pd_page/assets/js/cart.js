const data = {};
// ------------------拿session資料------------------
$(document).ready(function () {

    let shoppingItem = sessionStorage.getItem("shoppingItem");

    // 列出商品圖
    const base64Img = `data:image/*;base64,${shoppingItem.pdImg}`;
    $("#pdName").text(shoppingItem.pdName);
    $("#pdPetType").text(shoppingItem.pdPetType);
    $("#pdType").text(shoppingItem.pdType);
    $("#pdPrice").text("$ " + shoppingItem.pdPrice);
    $("#pdPrice").text("$ " + shoppingItem.pdPrice);
    $("#preview").html(`<img src="${base64Img}" alt="image" style="width: 100px">`);
    // data.pdImg = base64Img;


    // 更新購物車數
    function updateCartIcon() {
        let totalItems = 0;
        for (const productId in shoppingItem) {
            totalItems += shoppingItem[productId].quantity;
        }
        $(".shopping-cart-total").text(`(${totalItems})`);
    }

    // 初始化購物車圖示數量
    updateCartIcon();
})

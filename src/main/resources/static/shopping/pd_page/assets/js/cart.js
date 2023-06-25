$(document).ready(function () {
    // ------------------拿session資料----------------------------------------------------
    let shoppingItem = sessionStorage.getItem("shoppingItem");
    shoppingItem = JSON.parse(shoppingItem);
    // console.log(shoppingItem);


    // ------------------渲染購物車項目----------------------------------------------------
    let totalItems = 0;
    const cartBody = document.getElementById('cartBody');

    for (const productId in shoppingItem) {
        const item = shoppingItem[productId];
        const base64Img = `data:image/*;base64,${item.image}`;
        const id = item.id;
        const name = item.name;
        const quantity = item.quantity;
        const pdType = item.type;
        const pdPetType = item.petType;
        const price = item.price * item.quantity;

        const row = `
        <div class="cart-items" id="${id}">
            <div class="check">
                <input type="checkbox">
            </div>
            <div id="preview" class="image-box">
                <img src="${base64Img}" style="height: 120px">
            </div>

            <div class="item-about">
                <span id="id" style="display: none">${id}</span>
                <span id="pdPetType"> ${pdPetType}</span> / <span id="pdType">${pdType}</span>
                <h1 id="pdName" class="title">${name}</h1>
            </div>

            <div class="item-counter">
                <div class="item-btn item-decrease">-</div>
                <div id="" class="item-count">${quantity}</div>
                <div class="item-btn item-increase">+</div>

            </div>

            <div class="prices">
                <div id="pdPrice" class="amount">\$ ${price}</div>
                <div class="remove deleteOne"><u>移除</u></div>
            </div>
        </div>
        `;
        cartBody.innerHTML += row;

        // ------------------更新購物車數----------------------------------------------------
        totalItems += item.quantity;
        $(".shopping-cart-total").text(`(${totalItems})`);

    }


    // ------------------點擊 "+" 增加數量----------------------------------------------------
    $(document).on('click', '.item-btn.item-increase', function () {
        const productId = $(this).closest('.cart-items').find('#id').text();
        const item = shoppingItem[productId];

        item.quantity++; // 遞增數量----------------------------------------------------
        updateQuantity(productId, item.quantity); // 更新顯示的數量
        updateCheckoutItem();
    });

    // ------------------點擊 "-" 減少數量----------------------------------------------------
    $(document).on('click', '.item-btn.item-decrease', function () {
        const productId = $(this).closest('.cart-items').find('#id').text();
        const item = shoppingItem[productId];

        item.quantity--; // 遞減數量---------------------------------------------------------
        if (item.quantity < 0) {    // 確保數量不小於 0---------------------------------------
            item.quantity = 0;
        }
        updateQuantity(productId, item.quantity); // 更新顯示的數量---------------------------
        updateCheckoutItem();

    });

    // ------------------更新數量----------------------------------------------------
    function updateQuantity(productId, quantity) {
        const quantityElement = $(`#${productId} .item-count`);
        quantityElement.text(quantity);

        // 更新 shoppingItem 物件中的數量----------------------------------------------------
        shoppingItem[productId].quantity = quantity;

        // 將更新後的 shoppingItem 物件存回 session------------------------------------------
        sessionStorage.setItem("shoppingItem", JSON.stringify(shoppingItem));

        // 更新總數量-----------------------------------------------------------------------
        totalItems = updateTotalItems(shoppingItem);
        $(".shopping-cart-total").text(`(${totalItems})`);

        // 更新價格------------------------------------------------------------------------
        const priceElement = $(`#${productId} #pdPrice`);
        const item = shoppingItem[productId];
        const price = item.price * quantity;
        priceElement.text(`\$ ${price}`);

    }

    function updateTotalItems(shoppingItem) {
        let total = 0;
        for (const productId in shoppingItem) {
            total += shoppingItem[productId].quantity;
        }
        return total;
    }


    // ------------------存要付款項目到session----------------------------------------------------
    const checkoutItem = {};

    // ------------------點擊 checkbox----------------------------------------------------
    $(document).on('click', '.check input[type="checkbox"]', function () {
        const productId = $(this).closest('.cart-items').find('#id').text();
        const isChecked = $(this).prop('checked');

        if (isChecked) {
            // 勾選時將項目存入 checkoutItem------------------------------------------------------
            checkoutItem[productId] = shoppingItem[productId];
        } else {
            // 取消勾選時從 checkoutItem 移除項目--------------------------------------------------
            delete checkoutItem[productId];
        }
        updateCheckoutItem();
    });

    // ------------------更新 checkoutItem 物件--------------------------------------------------
    function updateCheckoutItem() {
        sessionStorage.setItem("checkoutItem", JSON.stringify(checkoutItem));

        const itemsElement = $(".items");
        const totalAmountElement = $(".total-amount");

        let itemsCount = 0;
        let totalAmount = 0;

        for (const productId in checkoutItem) {
            const item = checkoutItem[productId];
            itemsCount += item.quantity;
            totalAmount += item.price * item.quantity;
        }

        itemsElement.text(`${itemsCount} 品項`);
        totalAmountElement.text(`$ ${totalAmount}`);
    }


    // ------------------移除項目----------------------------------------------------
    // 移除全部----------------------------------------------------
    $(document).on("click", ".deleteAll", function () {
        Swal.fire({
            title: "確定要清空購物車品項嗎？",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#f1ecd1",
            cancelButtonColor: "#f8c544",
            confirmButtonText: "是，請清空",
            cancelButtonText: "否，請保留",
        }).then((result) => {
            if (result.isConfirmed) {
                $(".cart-items").fadeOut(1000, function () {
                    $(this).remove();
                });
                // 清空session的 shoppingItem 和 checkoutItem
                sessionStorage.removeItem("shoppingItem");
                sessionStorage.removeItem("checkoutItem");

                // 更新購物車品項----------------------------------------------------
                $(".shopping-cart-total").text(`(0)`);
            }
        });
    });

    // 移除單項----------------------------------------------------
    $(document).on("click", ".deleteOne", function () {
        const productId = $(this).closest('.cart-items').find('#id').text();
        Swal.fire({
            title: "確定要移除這個品項嗎？",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#f1ecd1",
            cancelButtonColor: "#f8c544",
            confirmButtonText: "是，請移除",
            cancelButtonText: "否，請保留",
        }).then((result) => {
            if (result.isConfirmed) {
                $(this).closest(".cart-items").fadeOut(1000, function () {
                    $(this).remove();
                })
                // 從session的 shoppingItem 清掉他----------------------------------
                const shoppingItem = JSON.parse(sessionStorage.getItem("shoppingItem"));
                delete shoppingItem[productId];
                sessionStorage.setItem("shoppingItem", JSON.stringify(shoppingItem));

                // 更新購物車品項----------------------------------------------------
                totalItems = updateTotalItems(shoppingItem);
                $(".shopping-cart-total").text(`(${totalItems})`);
            }
        });
    });


    // 繼續選購----------------------------------------------------
    $(".button-back").on("click", function () {
        location.href = "/front-product-list/front-product-list.html";
    })


});
document.querySelector(".jsFilter").addEventListener("click", function () {
  document.querySelector(".filter-menu").classList.toggle("active");
});

document.querySelector(".grid").addEventListener("click", function () {
  document.querySelector(".list").classList.remove("active");
  document.querySelector(".grid").classList.add("active");
  document.querySelector(".products-area-wrapper").classList.add("gridView");
  document
    .querySelector(".products-area-wrapper")
    .classList.remove("tableView");
});

document.querySelector(".list").addEventListener("click", function () {
  document.querySelector(".list").classList.add("active");
  document.querySelector(".grid").classList.remove("active");
  document.querySelector(".products-area-wrapper").classList.remove("gridView");
  document.querySelector(".products-area-wrapper").classList.add("tableView");
});

// var modeSwitch = document.querySelector('.mode-switch');
// modeSwitch.addEventListener('click', function () {
//   document.documentElement.classList.toggle('light');
//   modeSwitch.classList.toggle('active');
// });


// ------------------列出資料------------------
$(document).ready(function () {
  const tableBody = document.getElementById('tableBody');
  axios.get("/product/get/all")
      .then( function (res) {
        console.log(res.data);
        res.data.forEach(element => {

          const base64Img = `data:image/*;base64,${element.pdImg}`;
			
			console.log(base64Img);
          const row = `
                            <div class="products-row">
                            <div class="product-cell image">
                                <img src="${base64Img}" alt="image">
                            </div>
                            <div class="product-cell pdId" style="display: none">${element.pdId}</div>
                            <div class="product-cell name"><span class="cell-label"></span>${element.pdName}</div>
                            <div class="product-cell category"><span class="cell-label">商品類別：</span>${element.pdType}</div>
                            <div class="product-cell price"><span class="cell-label">售價：</span>\$ ${element.pdPrice}</div>
                            </div>
                            `

          tableBody.innerHTML += row;
        })
      })
      .catch(err => console.log(err));

})

// ------------------查看商品------------------
$(document).on('click', "#tableBody .products-row", function () {
    let pdId = $(this).find(".pdId").text();
    console.log(pdId);

    sessionStorage.setItem("pdId", pdId);
    location.href = "/shopping/pd_page/PdPage.html";

})

// ------------------購物車項目初始化------------------
let shoppingItem = sessionStorage.getItem("shoppingItem");
if (shoppingItem) {
    shoppingItem = JSON.parse(shoppingItem);
} else {
    shoppingItem = {};
}
// 更新購物車商品數
function updateCartIcon() {
    let totalItems = 0;
    for (const productId in shoppingItem) {
        totalItems += shoppingItem[productId].quantity;
    }
    $(".shopping-cart-total").text(`(${totalItems})`);
}
// 初始化購物車圖示數量
updateCartIcon();

// ------------------結帳------------------
$(".cart").on('click', function () {
    location.href = "/shopping/pd_page/Cart.html";
});
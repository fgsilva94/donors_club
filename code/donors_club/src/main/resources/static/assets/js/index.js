import { getElement, setStorageItem, getStorageItem } from "./utils.js";

const adsField = getElement("#ads");
const name = getElement(".name");
let categoriesComboBox = $("#categories-combobox")
let citiesComboBox = $("#cities-combobox")
let ads;
let categories
let districts

window.addEventListener("DOMContentLoaded", async () => {
    name.innerHTML = ` ${getStorageItem("userName") ? getStorageItem("userName") : ""}`;

    try {
        ads = await $.ajax({
            url: `/api/ads`,
            method: "get",
            dataType: "json",
        });
    } catch (error) { }

    // adsField.innerHTML = ads
    //     .map((ad) => {
    //         return `<a href="./adPost.html">
    //       <article class="ad" data-id="${ad.id}">
    //         <img
    //           src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/DOG-HUSKY_23JUL00.jpg/220px-DOG-HUSKY_23JUL00.jpg"
    //           alt="test img"
    //         />
    //         <span>${ad.title}</span>
    //       </article>
    //     </a>`;
    //     })
    //     .join("");

    let htmlAds = ''
    for (const ad of ads) {
        htmlAds += `<div class="col ad mb-5" data-id="${ad.id}">
                        <div class="card shadow-sm">
                            <a href="./adPost.html">
                                    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/DOG-HUSKY_23JUL00.jpg/220px-DOG-HUSKY_23JUL00.jpg"
                                        alt="test img"/>
                                    <div class="card-body">
                                        <p class="card-text">${ad.title}</p>
                                        <div class="d-flex justify-content-between align-items-center">
                                            <small class="text-muted">${ad.city}</small>
                                            <small class="text-muted">${ad.pubDate}</small>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>`
    }
    adsField.innerHTML = htmlAds

    try {
        categories = await $.ajax({
            url: `/api/categories`,
            method: "get",
            dataType: "json",
        });
    } catch (error) {
        console.log(error)
    }

    let htmlCategories = ''
    for (const category of categories) {
        let htmlSubcategories = `<ul class="submenu dropdown-menu">`
        for (const subcategory of category.subcategories) {
            htmlSubcategories += `<li><a class="dropdown-item" href="">${subcategory.name}</a></li>`
        }
        htmlSubcategories += `</ul>`

        htmlCategories += `<li><a class="dropdown-item dropdown-toggle" href="">${category.name}</a>${htmlSubcategories}</li>`
    }
    categoriesComboBox.html(htmlCategories)

    try {
        districts = await $.ajax({
            url: `/api/districts`,
            method: "get",
            dataType: "json",
        });
    } catch (error) {
        console.log(error)
    }

    let htmlDistricts = ''
    for (const district of districts) {
        let htmlCities = `<ul class="submenu dropdown-menu">`
        for (const city of district.cities) {
            htmlCities += `<li><a class="dropdown-item" href="">${city.name}</a></li>`
        }
        htmlCities += `</ul>`

        htmlDistricts += `<li class="dropdown-item dropdown-toggle" href="">${district.name}</a>${htmlCities}</li>`
    }
    citiesComboBox.html(htmlDistricts)
});

adsField.addEventListener("click", function (e) {
    let id;
    if (e.target.parentElement.dataset.id) {
        id = e.target.parentElement.dataset.id;
        setStorageItem("adId", id);
    } else if (e.target.dataset.id) {
        id = e.target.dataset.id;
        setStorageItem("adId", id);
    }
});

$(document).ready(function () {
    $(document).on('click', '.dropdown-menu', function (e) {
        e.stopPropagation();
    });
    if ($(window).width() < 992) {
        $('.dropdown-menu a').click(function (e) {
            e.preventDefault();
            if ($(this).next('.submenu').length) {
                $(this).next('.submenu').toggle();
            }
            $('.dropdown').on('hide.bs.dropdown', function () {
                $(this).find('.submenu').hide();
            })
        });
    }
});
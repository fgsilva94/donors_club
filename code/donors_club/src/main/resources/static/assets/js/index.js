import {
    getElement,
    getElements,
    setStorageItem,
    getStorageItem,
    removeStorageItem,
} from "./utils.js";

const adsField = getElement("#ads");
const name = getElement(".name");
const logout = getElement(".logout");
const categoriesComboBox = $("#category-combobox");
const citiesComboBox = $("#city-combobox");
const textSearch = getElement("#text-search")
const category = getElement("#category")
const city = getElement("#city")
let ads;
let categories;
let districts;
let categoryList
let cityList



window.addEventListener("DOMContentLoaded", async () => {
    name.innerHTML = ` ${getStorageItem("userName") ? getStorageItem("userName") : ""
        }`;

    try {
        ads = await $.ajax({
            url: `/api/ads`,
            method: "get",
            dataType: "json",
        });
    } catch (error) { }

    buildAdPosts()

    try {
        categories = await $.ajax({
            url: `/api/categories`,
            method: "get",
            dataType: "json",
        });
    } catch (error) {
        console.log(error);
    }

    let htmlCategories = "";
    for (const category of categories) {
        let htmlSubcategories = `<ul class="submenu dropdown-menu">`;
        for (const subcategory of category.subcategories) {
            htmlSubcategories += `<li><a class="dropdown-item">${subcategory.name}</a></li>`;
        }
        htmlSubcategories += `</ul>`;

        htmlCategories += `<li><a class="dropdown-item dropdown-toggle">${category.name}</a>${htmlSubcategories}</li>`;
    }
    categoriesComboBox.html(htmlCategories);

    try {
        districts = await $.ajax({
            url: `/api/districts`,
            method: "get",
            dataType: "json",
        });
    } catch (error) {
        console.log(error);
    }

    let htmlDistricts = "";
    for (const district of districts) {
        let htmlCities = `<ul class="submenu dropdown-menu">`;
        for (const city of district.cities) {
            htmlCities += `<li><a class="dropdown-item">${city.name}</a></li>`;
        }
        htmlCities += `</ul>`;

        htmlDistricts += `<li class="dropdown-item dropdown-toggle">${district.name}</a>${htmlCities}</li>`;
    }
    citiesComboBox.html(htmlDistricts);

    categoryList = $("#category-combobox .submenu .dropdown-item")
    cityList = $("#city-combobox .submenu .dropdown-item")
    categoryList.on('click', e => {
        $("#btn-category").attr("aria-expanded","false");
        $("#btn-category").text(e.target.textContent)
        category.value = e.target.textContent
    })
    cityList.on('click', e => {
        $("#btn-city").attr("aria-expanded","false");
        $("#btn-city").text(e.target.textContent)
        city.value = e.target.textContent
    })
});

adsField.addEventListener("click", function (e) {
    let id;

    if (e.target.parentElement.parentElement.dataset.id) {
        id = e.target.parentElement.parentElement.dataset.id;
        setStorageItem("adId", id);
    } else if (e.target.parentElement.parentElement.parentElement.dataset.id) {
        id = e.target.parentElement.parentElement.parentElement.dataset.id;
        setStorageItem("adId", id);
    } else if (
        e.target.parentElement.parentElement.parentElement.parentElement.dataset.id
    ) {
        id =
            e.target.parentElement.parentElement.parentElement.parentElement.dataset
                .id;
        setStorageItem("adId", id);
    } else if (
        e.target.parentElement.parentElement.parentElement.parentElement
            .parentElement.dataset.id
    ) {
        id =
            e.target.parentElement.parentElement.parentElement.parentElement
                .parentElement.dataset.id;
        setStorageItem("adId", id);
    }
});

logout.addEventListener("click", function (e) {
    e.preventDefault();

    removeStorageItem("adId");
    removeStorageItem("userId");
    removeStorageItem("userName");

    location.replace("./index.html");
});

$('#search').on('click', async () => {
    try {
        ads = await $.ajax({
            url: `/api/ads/search?title=${textSearch.value}&category=${category.value}&city=${city.value}`,
            method: "get",
            dataType: "json",
        });
    } catch (error) {
        console.log(error)
    }
    buildAdPosts()
})

function buildAdPosts() {
    let htmlAds = "";
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
                        </div>`;
    }
    adsField.innerHTML = htmlAds;
}
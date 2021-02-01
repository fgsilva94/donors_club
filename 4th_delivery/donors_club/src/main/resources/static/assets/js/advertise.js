import { getElement, getElements, getStorageItem } from "./utils.js";

const title = getElement("#title");
const description = getElement("#description");
const category = getElement("#dropdown-cat");
const submitBtn = getElement("#submit");
const result = getElement(".result");
const btnDropdown = getElement("#btn-category");
const paramCat = getElement("#param-cat");

window.addEventListener("DOMContentLoaded", async () => {
    try {
        let categories = await $.ajax({
            url: `/api/categories`,
            method: "get",
            dataType: "json",
        });

        category.innerHTML = categories
            .map((cat) => {
                let subcategories = cat.subcategories.map(sub => {
                    return `<li><a class="dropdown-item" data-id="${sub.id}">${sub.name}</a></li>`
                }).join("");

                return `<li>
                            <a class="dropdown-item dropdown-toggle">${cat.name}</a>
                            <ul class="submenu dropdown-menu">
                                ${subcategories}
                            </ul>
                        </li>`;
            })
            .join("");

        $("#dropdown-cat .dropdown-item").on('click', e => {
            console.log(e.target.getAttribute('data-id'))
            btnDropdown.setAttribute("aria-expanded", "false");
            btnDropdown.innerText = e.target.textContent
            paramCat.value = e.target.getAttribute('data-id')
        })

    } catch (error) {
        console.log(error)
    }
});

submitBtn.addEventListener("click", async (e) => {
    e.preventDefault();

    try {
        let newAd = {
            title: title.value,
            description: description.value,
            category: {
                id: paramCat.value,
            },
            publicationDate: new Date(),
            owner: {
                id: getStorageItem("userId"),
            },
        };

        console.log(newAd);

        if (
            title.value.trim().length !== 0 &&
            description.value.trim().length !== 0
        ) {
            let ad = await $.ajax({
                url: `/api/ads`,
                method: "post",
                dataType: "json",
                data: JSON.stringify(newAd),
                contentType: "application/json",
            });

            location.replace("./index.html");
        } else {
            result.innerHTML = `title and description must not be empty`;
        }
    } catch (error) {
        result.innerHTML = `Error creating new ad`;
    }
});
